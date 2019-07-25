package springcloud.learn.wsc.core.constants;

/**
 * @author wangshuaichao
 * @ClassName: LFConstants
 * @Decription TOO
 * @Date 2019/7/25 17:26
 **/
public class LFConstants {
    private LFConstants() {
    }

    //编码格式
    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UNICODE = "Unicode";

    //报文格式
    public static final String MESSAGE_DEFAULT_FORMAT = ".json";

    //请求类型
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String OPTIONS = "OPTIONS";

    //占位符
    public static final String PLACE_HOLDER = "\\{\\?\\}";

    public static final class CACHE {
        private CACHE() {
        }

        /**
         * cache默认名称
         */
        public static final String DEFAULT_CACHE_NAME = "longfor_cache";

        public static final String DEFAULT_CACHE_SPLIT_KEY = "|";

        /**
         * 用于判断租户状态
         */
        public static final String TENANT_ACCESS_KEY = "TENANT_ACCESS_KEY";
        /**
         * 用于判断ProjectToken
         */
        public static final String PROJECT_TOKEN_KEY = "PROJECT_TOKEN_KEY";

    }

    public static final class PERMISSION {
        private PERMISSION() {
        }

        /**
         * 默认权限分隔符
         */
        public static final String DEFAULT_SPLIT = ",";

        public static final String EQ = "=";
        public static final String NOTEQ = "<>";

    }

    public static final class JWT {
        private JWT() {
        }

        public static final String SUB_LOGIN_NAME = "ln";
        public static final String SUB_REAL_NAME = "rn";
        public static final String SUB_TENANT_TYPE = "tt";
        public static final String SUB_TENANT_ID = "ti";
        public static final String SUB_MURPHY_TOKEN = "mt";
        public static final String SUB_TYPE = "st";
        public static final String SUB_SERVER_SINGLE_PROJECT_SECRET = "ps";
        public static final String SUB_SERVER_SINGLE_PROJECT_ID = "pi";

    }

    public static final class CONDITION {
        private CONDITION() {
        }

        public static final String EQ = "eq";
        public static final String NOTEQ = "noteq";
        public static final String GT = "gt";
        public static final String LT = "lt";
        public static final String GTE = "gte";
        public static final String LTE = "lte";
        public static final String BTW = "btw";
        public static final String NOTBTW = "notbtw";
    }
}
