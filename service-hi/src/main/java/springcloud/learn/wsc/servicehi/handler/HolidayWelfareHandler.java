package springcloud.learn.wsc.servicehi.handler;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.jobs.api.ApiWelfareService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 许鹏
 * @date 2019/3/25
 */
@JobHandler(value = "holidayWelfareHandler")
@Component
@Slf4j
public class HolidayWelfareHandler extends IJobHandler {
    @Resource
    private ApiWelfareService apiWelfareService;

    /**
     * @description 福利发放定时任务
     * @param param 1.生日 2.入职周年 3.端午节 4.中秋节 5.三八妇女节
     * @return Boolean
     * @author xupeng13
     * @date 2019/5/29 14:30
     */
    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("holidayWelfareHandler begin. job execute param:{}", param);
        log.info("节日福利定时任务执行开始。参数：{}", param);

        BaseResponse baseResponse = apiWelfareService.grantWelfareJob(Integer.valueOf(param));
        if (baseResponse.getCode().equals(BaseResponse.DEFAULT_CODE)) {
            log.info("节日福利定时任务执行成功。参数：{}。返回值：{}", param, baseResponse.toString());
        }else {
            XxlJobLogger.log("holidayWelfareHandler error. job execute param:{}. BaseResponse:{}", param, baseResponse.toString());
            log.error("节日福利定时任务执行失败。参数：{}。返回值：{}", param, baseResponse.toString());
        }
        XxlJobLogger.log("holidayWelfareHandler completed. job execute param:{}", param);
        log.info("节日福利定时任务执行结束。参数：{}", param);
        return SUCCESS;
    }
}
