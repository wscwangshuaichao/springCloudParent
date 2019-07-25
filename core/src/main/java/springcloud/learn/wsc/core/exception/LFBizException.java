package springcloud.learn.wsc.core.exception;

import lombok.Generated;

/**
 * @author wangshuaichao
 * @ClassName: LFBizException
 * @Decription TOO
 * @Date 2019/7/25 16:30
 **/
@Generated
public class LFBizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_MSG = "系统异常，请稍后重试";

    public LFBizException() {
        super(DEFAULT_MSG);
    }

    public LFBizException(String message) {
        super(message);
    }

    public LFBizException(Throwable cause) {
        super(cause);
    }

    public LFBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public LFBizException(String message, Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
