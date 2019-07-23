package springcloud.learn.wsc.servicehi.utils;

/**
 * @author guoguangxiao
 * @date 2019/5/15 18:53:21
 */
public class TypeEnum {

    public enum Account {

        PERSONAL("GR", "grAccountBusiness", "个人账户"),
        EMBRAVE("JL", "jlAccountBusiness", "激励账户"),
        BUSINESS("YW", "ywAccountBusiness", "业务账户"),
        PRODUCT("CP", "cpAccountBusiness", "产品账户"),
        GAT("GAT", "gatAccountBusiness", "关爱通账户"),
        OTHER("QT", "qtAccountBusiness", "其他账户");

        private String key;
        private String value;
        private String name;

        Account(String key, String value, String name) {
            this.key = key;
            this.value = value;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getValue(String key) {
            for (Account account : values()) {
                if (account.getKey().equals(key)) {
                    return account.getValue();
                }
            }
            return null;
        }

        public static String getName(String key) {
            for (Account account : values()) {
                if (account.getKey().equals(key)) {
                    return account.getName();
                }
            }
            return null;
        }
    }

    public enum Transfer {

        BALANCE_ACC_TRANSFER(Constants.TRANSFER_BALANCE, "balanceAccTransferBusiness", "余额账户转账"),
        FLOW_ACC_TRANSFER(Constants.TRANSFER_FLOW, "flowAccTransferBusiness", "流水账户转账"),
        ZERO_ACC_TRANSFER(Constants.TRANSFER_ZERO, "zeroAccTransferBusiness", "业务账户转账"),
        ;

        private String key;
        private String value;
        private String name;

        Transfer(String key, String value, String name) {
            this.key = key;
            this.value = value;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getValue(String key) {
            for (Transfer transfer : values()) {
                if (transfer.getKey().equals(key)) {
                    return transfer.getValue();
                }
            }
            return null;
        }

    }

    public enum TransStatus {

        WAITING("0", "未处理"),
        FINISHED("1", "完成"),
        FAILURE("2", "失败"),
        PROCESSING("3", "处理中"),
        CLOSED("4", "关闭"),
        CANCEL("5", "取消"),

        ;

        private String code;
        private String msg;

        TransStatus(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsgByCode(String code) {
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return t.msg;
                }
            }
            return null;
        }
        public static boolean isExist(String code){
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
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

    public enum TransType {

        TRANSFER("1", "转账"),
        FREEZE("2", "冻结"),
        UNFREEZE("3", "解冻")
        ;
        
        private String code;
        private String msg;

        TransType(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsgByCode(String code) {
            for (TransStatus t : TransStatus.values()) {
                if (t.getCode().equals(code)) {
                    return t.msg;
                }
            }
            return null;
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
