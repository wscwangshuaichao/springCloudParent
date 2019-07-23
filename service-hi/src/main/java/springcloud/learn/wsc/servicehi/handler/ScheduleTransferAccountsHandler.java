package springcloud.learn.wsc.servicehi.handler;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.jobs.api.ApiScheduleService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xupeng
 * @date 2019/3/25
 */
@JobHandler(value = "scheduleTransferAccountsHandler")
@Component
@Slf4j
public class ScheduleTransferAccountsHandler extends IJobHandler {
    @Resource
    private ApiScheduleService apiScheduleService;

    /**
     * @description 预约转账定时任务
     * @param param 1.转账 2.打赏
     * @return Boolean
     * @author xupeng13
     * @date 2019/5/29 14:29
     */
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("scheduleTransferAccountsHandler begin. job execute param:{}", param);
        log.info("预约转账定时任务执行开始。参数：{}", param);

        // 调用预约转账服务
        BaseResponse baseResponse = apiScheduleService.grantScheduleJob(1);
        if (!baseResponse.getCode().equals(BaseResponse.DEFAULT_CODE)) {
            XxlJobLogger.log("scheduleTransferAccountsHandler error. job execute param:{}. BaseResponse:{}", param, baseResponse.toString());
            log.error("预约转账定时任务执行失败。参数：{}。返回值：{}", param, baseResponse.toString());
        }

        XxlJobLogger.log("scheduleTransferAccountsHandler completed.job execute param:{}", param);
        log.info("预约转账定时任务执行结束。参数：{}", param);
        return SUCCESS;
    }
}
