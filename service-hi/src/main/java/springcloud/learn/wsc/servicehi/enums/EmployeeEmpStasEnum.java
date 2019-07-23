package springcloud.learn.wsc.servicehi.enums;



public enum EmployeeEmpStasEnum {

    QUIT("I", "离职"),
    NORMAL("A", "在职"),
    ;

    private String code;
    private String msg;

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

    EmployeeEmpStasEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        for (EmployeeEmpStasEnum t : EmployeeEmpStasEnum.values()) {
            if (t.getCode().equals(code)) {
                return t.msg;
            }
        }
        return null;
    }

}
