package springcloud.learn.wsc.servicehi.utils;

import com.longfor.gf.lc.h5.endpoint.pojo.Message;

import java.lang.reflect.Field;

/**
 * @author zhangjianbing
 * time 2019/5/24
 */
public class MessageTemplateUtil {

    private MessageTemplateUtil(){}

    /**
     * 消息模板填充
     *
     * @param template 模板
     * @param message  消息对象
     * @return
     */
    public static String fillTemplate(String template, Message message) {
        String replace = "";
        Class<Message> clasz = Message.class;
        Field[] fields = clasz.getDeclaredFields();
        for (Field field : fields) {
            String oldChar = "#" + field.getName() + "#";
            if (template.contains(oldChar)) {
                String newChar = getFieldValueByFieldName(field.getName(), message) + "";
                replace = template.replace(oldChar, newChar);
                template = replace;
            }
        }
        return replace;
    }

    public static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (Exception e) {
            return null;
        }
    }

}
