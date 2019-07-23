package springcloud.learn.wsc.servicehi.utils;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/4/26 10:18:29
 */
public class MyBeanUtils {

    /**
     * Method: 比对两个对象，返回比对后有属性修改的对象
     * Description:
     * Author guoguangxiao
     * Data 2019/4/26 10:18
     * @param
     * @return
     */
    public static <T> T compareObj(T src, T target) {
        if (src == null || target == null) {
            return null;
        }
        boolean changeFlag = false;
        T retObj = null;
        try {

//            Object srcObj = src.getClass().newInstance();
//            Object targetObj = src.getClass().newInstance();
            retObj = (T) src.getClass().newInstance();
            Class clazz = src.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //Jaococ在使用了反射的代码中，发现类多了一个字段jacocoData
                if(field.isSynthetic()){
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object srcValue = getMethod.invoke(src);
                Object targetValue = getMethod.invoke(target);

//                Type returnType = getMethod.getGenericReturnType();
//                System.out.println("=========="+returnType.getTypeName());

                //避免空指针异常
                String v1 = srcValue == null ? "" : srcValue.toString();
                String v2 = targetValue == null ? null : targetValue.toString();
                if (StringUtils.isNotEmpty(v2) && !v1.equals(v2)) {
                    Method setMethod = pd.getWriteMethod();
                    setMethod.invoke(retObj, targetValue);
                    changeFlag = true;
                }
            }

        } catch (Exception e) {
            changeFlag = false;
            e.printStackTrace();
        }
        if (!changeFlag) {
            return null;
        }
        return retObj;
    }

    /**
     * Method: 对象复制
     * Description:
     * Author guoguangxiao
     * Data 2019/4/28 14:12
     * @param
     * @return
     */
    public static <E,T> List<T> copyProperties(List<E> list, Class<T> targetClazz) {
        List<T> ret = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return ret;
        }
        try{
            for(E item : list){
                T retItem = targetClazz.newInstance();
                BeanUtils.copyProperties(item, retItem);
                ret.add(retItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Method: 对象复制
     * Description:
     * Author guoguangxiao
     * Data 2019/5/5 15:08
     * @param
     * @return
     */
    public static <E,T> T copyProperties(E entity,Class<T> targetClazz) {
        T result = null;
        if(entity == null){
            return result;
        }
        try {
            result =  targetClazz.newInstance();
            BeanUtils.copyProperties(entity, result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    /**
     * Method: 去掉字段前后空格
     * Description:
     * Author guoguangxiao
     * Data 2019/5/7 15:01
     * @param
     * @return
     */
    public static String getTrimValue(String value){
        if(StringUtils.isNotEmpty(value)){
            return value.trim();
        }
        return value;
    }

}
