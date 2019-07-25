package springcloud.learn.wsc.core.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangshuaichao
 * @ClassName: Mask
 * @Decription TOO
 * @Date 2019/7/24 17:51
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mask {

    /**
     * 脱敏数据类型, 非Customer时, 将忽略 refixNoMaskLen 和 suffixNoMaskLen 和 maskStr
     *
     * @return
     */
    SensitiveType sensitiveType() default SensitiveType.CUSTOMER;

    /**
     * 前置不需要打码的长度
     *
     * @return
     */
    int prefixNoMaskLen() default 0;

    /**
     * 后置不需要打码的长度
     *
     * @return
     */
    int suffixNoMaskLen() default 0;

    /**
     * 用什么打码
     *
     * @return
     */
    String maskStr() default "*";

}

