package springcloud.learn.wsc.core.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangshuaichao
 * @ClassName: DesensitizedUtils
 * @Decription 敏感字符串遮罩
 * @Date 2019/7/25 16:35
 **/
public class DesensitizedUtils {

    private DesensitizedUtils() {
    }

    /**
     * 对字符串按指定类型脱敏
     *
     * @param origin        原始字符串
     * @param sensitiveType 脱敏类型
     * @return 脱敏后的字符串
     */
    public static String maskValue(String origin, SensitiveType sensitiveType) {
        Preconditions.checkNotNull(sensitiveType, "sensitive type should not be null.");
        switch (sensitiveType) {
            case CHINESE_NAME:
                return maskChineseName(origin);
            case ID_CARD:
                return maskIdCardNum(origin);
            case FIXED_PHONE:
                return maskFixedPhone(origin);
            case MOBILE_PHONE:
                return maskMobilePhone(origin);
            case ADDRESS:
                return maskAddress(origin);
            case EMAIL:
                return maskEmail(origin);
            case BANK_CARD:
                return maskBankCard(origin);
            case PASSWORD:
                return maskPassword(origin);
            default:
                throw new IllegalArgumentException("unknow sensitive type " + sensitiveType);
        }
    }

    /**
     * 对字符串进行脱敏操作
     *
     * @param origin          原始字符串
     * @param prefixNoMaskLen 左侧需要保留几位明文字段
     * @param suffixNoMaskLen 右侧需要保留几位明文字段
     * @param maskStr         用于遮罩的字符串, 如'*'
     * @return 脱敏后的字符串
     */
    public static String maskValue(String origin, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr) {
        if (origin == null) {
            return origin;
        }

        int length = origin.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < prefixNoMaskLen) {
                sb.append(origin.charAt(i));
            } else if (i > (length - suffixNoMaskLen - 1)) {
                sb.append(origin.charAt(i));
            } else {
                sb.append(maskStr);
            }
        }
        return sb.toString();
    }

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号，比如：李**
     * @return 脱敏后的字符串
     */
    public static String maskChineseName(String fullName) {
        if (fullName == null) {
            return fullName;
        }

        if (fullName.length() > 2) {
            return maskValue(fullName, 1, 1, "*");
        } else {
            return maskValue(fullName, 1, 0, "*");
        }
    }

    /**
     * 【身份证号】显示前六位, 四位，其他隐藏。共计18位或者15位，比如：340304*******1234
     * @return 脱敏后的字符串
     */
    public static String maskIdCardNum(String id) {
        return maskValue(id, 6, 4, "*");
    }

    /**
     * 【固定电话】后四位，其他隐藏，比如 ****1234
     * @return 脱敏后的字符串
     */
    public static String maskFixedPhone(String num) {
        return maskValue(num, 0, 4, "*");
    }

    /**
     * 【手机号码】前三位，后四位，其他隐藏，比如135****6810
     * @return 脱敏后的字符串
     */
    public static String maskMobilePhone(String num) {
        return maskValue(num, 3, 4, "*");
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     * @return 脱敏后的字符串
     */
    public static String maskAddress(String address) {
        return maskValue(address, 6, 0, "*");
    }

    /**
     * 【电子邮箱 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com
     * @return 脱敏后的字符串
     */
    public static String maskEmail(String email) {
        if (email == null) {
            return email;
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            String temp = maskValue(email.substring(0, index), 1, 0, "*");
            if (temp == null) {
                return email;
            }
            return temp.concat(email.substring(index));
        }
    }

    /**
     * 【银行卡号】前六位，后四位，其他用星号隐藏每位1个星号，比如：622260**********1234>
     * @return 脱敏后的字符串
     */
    public static String maskBankCard(String cardNum) {
        return maskValue(cardNum, 6, 4, "*");
    }

    /**
     * 【密码】密码的全部字符都用*代替，比如：******
     * @return 脱敏后的字符串
     */
    public static String maskPassword(String password) {
        if (password == null) {
            return null;
        }
        return "******";
    }

}
