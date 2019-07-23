package springcloud.learn.wsc.servicehi.handler;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.gat.echange.api.dto.GatDto;
import com.longfor.gf.lc.gat.echange.api.service.ApiGatSyncEmployeeService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xupeng13
 * @description: TODO
 * @version: v1.0
 * @date 2019/6/13 9:37
 */
@JobHandler(value = "gatSynchronizationHandler")
@Component
@Slf4j
public class GatSynchronizationHandler extends IJobHandler {

    @Resource
    private ApiGatSyncEmployeeService apiGatSyncEmployeeService;

    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("gatSynchronizationHandler begin. job execute param:{}", param);
        log.info("关爱通人员同步定时任务执行开始。参数：{}", param);
        BaseResponse<GatDto> baseResponse = apiGatSyncEmployeeService.syncGatEmployee();
        if (!baseResponse.getCode().equals(BaseResponse.DEFAULT_CODE)) {
            XxlJobLogger.log("gatSynchronizationHandler error. job execute param:{}. BaseResponse:{}", param, baseResponse.toString());
            log.error("关爱通人员同步定时任务执行失败。参数：{}。返回值：{}", param, baseResponse.toString());
        }
        XxlJobLogger.log("gatSynchronizationHandler completed. job execute param:{}", param);
        log.info("关爱通人员同步定时任务执行结束。参数：{}", param);
        return SUCCESS;
    }

}

