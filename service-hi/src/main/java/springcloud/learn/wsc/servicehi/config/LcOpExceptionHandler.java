package springcloud.learn.wsc.servicehi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import springcloud.learn.wsc.servicehi.exception.LcOpBusinessException;
import springcloud.learn.wsc.servicehi.utils.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
* @ClassName: LcOpExceptionHandler.java
* @date: 2019/5/22 17:49
* @version V1.0
*/
@ControllerAdvice
@Slf4j
public class LcOpExceptionHandler {

    @ExceptionHandler(value = LcOpBusinessException.class)
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest request, LcOpBusinessException e) {
        //非异常信息
//        log.info("龙币后台LcOpBusinessException异常: ", e);
        return new BaseResponse(e.getStatusCode(), e.getMessage(), null);
    }
}
