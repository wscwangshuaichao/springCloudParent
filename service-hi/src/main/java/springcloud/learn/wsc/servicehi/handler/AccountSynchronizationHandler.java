package springcloud.learn.wsc.servicehi.handler;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.op.endpoint.service.ApiOpAccountService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 许鹏
 * @date 2019/3/25
 */
@JobHandler(value = "accountSynchronizationHandler")
@Component
@Slf4j
public class AccountSynchronizationHandler extends IJobHandler {
    @Autowired
    private ApiOpAccountService apiOpAccountService;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("accountSynchronizationHandler begin. job execute param:{}", param);
        log.info("龙币账号同步定时任务执行开始。参数：{}", param);

        // 调用个人账号同步服务
        BaseResponse grBaseResponse = apiOpAccountService.grAccountBatchCreOrUpt();
        if (!grBaseResponse.getCode().equals(BaseResponse.DEFAULT_CODE)) {
            XxlJobLogger.log("accountSynchronizationHandler error. job execute param:{}. BaseResponse:{}", param, grBaseResponse.toString());
            log.error("龙币个人账号同步定时任务执行失败。参数：{}。返回值：{}", param, grBaseResponse.toString());
        }

        // 调用激励账号同步服务
        BaseResponse jlBaseResponse =apiOpAccountService.jlAccountBatchCreOrUpt();
        if (!jlBaseResponse.getCode().equals(BaseResponse.DEFAULT_CODE)) {
            XxlJobLogger.log("accountSynchronizationHandler error. job execute param:{}. BaseResponse:{}", param, jlBaseResponse.toString());
            log.error("龙币激励账号同步定时任务执行失败。参数：{}。返回值：{}", param, jlBaseResponse.toString());
        }

        XxlJobLogger.log("accountSynchronizationHandler completed. job execute param:{}", param);
        log.info("龙币账号同步定时任务执行开始。参数：{}", param);
        return SUCCESS;
    }
}

