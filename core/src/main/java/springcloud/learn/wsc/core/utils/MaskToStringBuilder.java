package springcloud.learn.wsc.core.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author wangshuaichao
 * @ClassName: MaskToStringBuilder
 * @Decription 支持数据脱敏的 ToStringBuilder
 * @Date 2019/7/25 17:24
 **/
public class MaskToStringBuilder extends ReflectionToStringBuilder {
    /**
     * Constructor.
     * <p>
     * This constructor outputs using the default style set with <code>setDefaultStyle</code>.
     * </p>
     *
     * @param object the Object to build a <code>toString</code> for, must not be <code>null</code>
     * @throws IllegalArgumentException if the Object passed in is <code>null</code>
     */
    public MaskToStringBuilder(Object object) {
        super(object);
    }

    /**
     * Constructor.
     * <p>
     * If the style is <code>null</code>, the default style is used.
     * </p>
     *
     * @param object the Object to build a <code>toString</code> for, must not be <code>null</code>
     * @param style  the style of the <code>toString</code> to create, may be <code>null</code>
     * @throws IllegalArgumentException if the Object passed in is <code>null</code>
     */
    public MaskToStringBuilder(Object object, ToStringStyle style) {
        super(object, style);
    }

    /**
     * <p>
     * Appends the fields and values defined by the given object of the given Class.
     * </p>
     *
     * <p>
     * If a cycle is detected as an object is &quot;toString()'ed&quot;, such an object is rendered
     * as if
     * <code>Object.toString()</code> had been called and not implemented by the object.
     * </p>
     *
     * @param clazz The class of object parameter
     */
    @Override
    protected void appendFieldsIn(Class clazz) {
        if (clazz.isArray()) {
            this.reflectionAppendArray(this.getObject());
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        Arrays.stream(fields).forEach(this::appendFieldIn);
    }

    private void appendFieldIn(Field field) {
        String fieldName = field.getName();
        if (!this.accept(field)) {
            return;
        }

        try {
            Object fieldValue = this.getValue(field);
            if (fieldValue == null) {
                return;
            }
            //如果需要打码
            Mask mask = field.getAnnotation(Mask.class);
            if (mask == null || field.getType() != String.class) {
                this.append(fieldName, fieldValue);
            } else {
                this.appendMaskFieldIn(fieldName, (String) fieldValue, mask);
            }

        } catch (IllegalAccessException ex) {
            //this can't happen. Would get a Security exception
            // instead throw a runtime exception in case the impossible happens.
            throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
        }
    }

    private void appendMaskFieldIn(String fieldName, String fieldValue, Mask mask) {
        if (mask.sensitiveType() != null && mask.sensitiveType() != SensitiveType.CUSTOMER) {
            this.append(fieldName, DesensitizedUtils.maskValue(fieldValue, mask.sensitiveType()));
        } else {
            this.append(fieldName, DesensitizedUtils.maskValue(fieldValue, mask.prefixNoMaskLen(), mask.suffixNoMaskLen(), mask.maskStr()));
        }
    }

}
