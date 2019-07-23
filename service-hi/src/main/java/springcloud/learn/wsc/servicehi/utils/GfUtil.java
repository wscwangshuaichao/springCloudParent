package springcloud.learn.wsc.servicehi.utils;

/**
 * @author zhangjianbing
 * time 2019/5/31
 */
public class GfUtil {

    private GfUtil() {
    }

    /**
     * 特殊字符串的拼接
     *
     * @param strArr      字符串数组
     * @param placeholder 分隔符
     * @return
     */
    public static String splicingStr(String[] strArr, String placeholder) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArr) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(placeholder);
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();

    }

}
