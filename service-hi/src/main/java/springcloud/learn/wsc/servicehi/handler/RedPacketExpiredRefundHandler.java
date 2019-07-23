package springcloud.learn.wsc.servicehi.handler;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiJobService;
import com.longfor.gf.lc.account.util.CodeEnum;
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
 * @date 2019/7/4 14:53
 */
@JobHandler(value = "redPacketExpiredRefundHandler")
@Component
@Slf4j
public class RedPacketExpiredRefundHandler  extends IJobHandler {

    @Resource
    private ApiJobService apiJobService;

    /**
     * @param param 无
     * @return Boolean
     * @description 过期红包退款定时任务
     * @author xupeng13
     * @date 2019/5/29 14:30
     */
    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("redPacketExpiredRefundHandler begin. job execute param:{}", param);
        log.info("红包退款定时任务执行开始。参数：{}", param);

        BaseResponse baseResponse = apiJobService.redPacketExpiredRefund();
        if (baseResponse.getCode().equals(CodeEnum.BizCode.SUCCESS.getCode())) {
            log.info("红包退款定时任务执行成功。参数：{}。返回值：{}", param, baseResponse.toString());
        } else {
            XxlJobLogger.log("redPacketExpiredRefundHandler error. job execute param:{}. BaseResponse:{}", param, baseResponse.toString());
            log.error("红包退款定时任务执行失败。参数：{}。返回值：{}", param, baseResponse.toString());
        }
        XxlJobLogger.log("redPacketExpiredRefundHandler completed. job execute param:{}", param);
        log.info("红包退款定时任务执行结束。参数：{}", param);
        return SUCCESS;
    }
}
