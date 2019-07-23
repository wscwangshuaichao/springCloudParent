package springcloud.learn.wsc.servicehi.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 许鹏
 * @date 2019/3/25
 */
@JobHandler(value = "accountsZeroHandler")
@Component
@Slf4j
public class AccountsZeroHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("accountsZeroHandler begin. job execute param:{}", param);
        log.info("账号余额清零定时任务执行开始。参数：{}", param);


        XxlJobLogger.log("accountsZeroHandler completed. job execute param:{}", param);
        log.info("账号余额清零定时任务执行结束。参数：{}", param);
        return SUCCESS;
    }
}
