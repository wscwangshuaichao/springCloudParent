package springcloud.learn.wsc.servicehi.exception;

import com.longfor.gf.lc.op.endpoint.enums.LcOpEndpointResultCodeEnum;
import lombok.Data;

/**
* @ClassName: LcOpBusinessException.java
* @Description: 龙币后台自定义运行异常
* @author: ChongLi
* @date: 2019/5/22 17:51
* @version V1.0
*/
@Data
public class LcOpBusinessException extends RuntimeException {
    private String statusCode;
    private String statusReason;


    public LcOpBusinessException() {
        this(LcOpEndpointResultCodeEnum.FAILED.getCode(), LcOpEndpointResultCodeEnum.FAILED.getMsg());

    }

    public LcOpBusinessException(String message) {
        this(LcOpEndpointResultCodeEnum.FAILED.getCode(), message);
    }

    public LcOpBusinessException(String statusCode, String statusReason) {
        this.statusCode = statusCode ;
        this.statusReason = statusReason ;
    }
    public LcOpBusinessException(LcOpEndpointResultCodeEnum codeEnum) {
        this.statusCode = codeEnum.getCode() ;
        this.statusReason = codeEnum.getMsg() ;
    }
    

    @Override
    public String getMessage() {
        return statusReason;
    }
}
