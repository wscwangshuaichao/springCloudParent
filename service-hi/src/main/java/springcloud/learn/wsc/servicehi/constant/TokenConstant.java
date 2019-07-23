package springcloud.learn.wsc.servicehi.constant;

/**
 * @author zhangjianbing
 * time 2019/5/22
 */
public class TokenConstant {

    /**
     * 龙信登录token
     */
    public static final String LONGCHAT_LOGIN_TOKEN = "X-LONGCHAT-Token";

    /**
     * 请求成功
     */
    public static final int SUCCESS = 200;
    /**
     * 请求失败
     */
    public static final int FIELD = 500;

    /**
     * token参数为空
     */
    public static final int MISSING_ACCESSTOKEN = 1001;

    /**
     * token已失效
     */
    public static final int EXPIRED_ACCESSTOKEN = 1002;

    /**
     * 服务器内部错误
     */
    public static final int INTERNAL_SERVER_ERROR = 9999;

    /**
     * 默认消息
     */
    public static final String DEFAULT_MESSAGE = "ok";

}
