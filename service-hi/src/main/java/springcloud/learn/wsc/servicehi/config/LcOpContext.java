package springcloud.learn.wsc.servicehi.config;

import com.google.common.collect.Maps;

import java.util.Map;

/**
* @ClassName: LcOpContext.java
* @date: 2019/5/23 1:46
* @version V1.0
*/
public class LcOpContext {
    public static final ThreadLocal<Map<String,Object>> contextData = new ThreadLocal<>();
    public static final String LC_OP_USER_KEY = "userKey";
    public static final String  REQUEST_WAIT_TIME = "waitTime" ;
    public static final String  LC_OP_LOGIN_KEY_VAL = "lcOpLoginKeyVal" ;

    public static void put(String key, Object value) {
        Map<String, Object> data = contextData.get();
        if(data == null) {
            data = Maps.newHashMap();
            contextData.set(data);
        }
        data.put(key, value);
    }
    public static void remove() {
        contextData.remove();
    }

    public static <T> T getObj(Class<T> t , String key) {
        if(contextData.get() == null){
            return null;
        }
        Object value = contextData.get().get(key);
        if(value == null) {
            return null;
        }
        return (T)value ;
    }

}
