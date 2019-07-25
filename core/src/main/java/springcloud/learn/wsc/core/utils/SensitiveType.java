package springcloud.learn.wsc.core.utils;

/**
 * @author wangshuaichao
 * @ClassName: SensitiveType
 * @Decription TOO
 * @Date 2019/7/24 17:53
 **/
public enum SensitiveType {
    /** 自定义 */
    CUSTOMER,
    /** 用户名, 单*昊, 徐* */
    CHINESE_NAME,
    /** 身份证号, 340304********0839 */
    ID_CARD,
    /** 座机号, ****1234 */
    FIXED_PHONE,
    /** 手机号, 186****1234 */
    MOBILE_PHONE,
    /** 地址, 上海市普陀区******** */
    ADDRESS,
    /** 电子邮件, s*****o@longfor.com */
    EMAIL,
    /** 银行卡, 622202************1234 */
    BANK_CARD,
    /** 密码, 永远是 ******, 与长度无关 */
    PASSWORD;
}

