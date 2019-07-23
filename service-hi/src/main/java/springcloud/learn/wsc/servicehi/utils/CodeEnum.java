package springcloud.learn.wsc.servicehi.utils;

/**
 * @author guoguangxiao
 * @date 2019/4/11 15:44:00
 */
public class CodeEnum {

    public enum  BizCode {

        SUCCESS("0000", "成功"),
        FAIL("0001", "失败"),
        EXIST("0002", "已存在"),
        SYS_ERROR("9999", "系统异常"),
        PARAM_NOT_EMPTY("0003", "字段不能为空")
        ;
        private String code;
        private String msg;
        BizCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
