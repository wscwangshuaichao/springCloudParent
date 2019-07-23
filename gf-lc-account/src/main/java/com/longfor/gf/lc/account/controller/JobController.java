package com.longfor.gf.lc.account.controller;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiJobService;
import com.longfor.gf.lc.account.job.RedPacketRefundJobService;
import com.longfor.gf.lc.account.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guoguangxiao
 * @date 2019/7/4 14:21:16
 */
@Slf4j
@RestController
public class JobController implements ApiJobService {

    @Resource
    private RedPacketRefundJobService redPacketRefundJobService;

    @Override
    public BaseResponse<String> redPacketExpiredRefund() {
        BaseResponse<String> baseResponse = ResultUtils.getSuccess();
        try {
            baseResponse.setData(redPacketRefundJobService.expiredRefund(null));
        } catch (Exception e) {
            baseResponse = ResultUtils.getException(e);
        }
        return baseResponse;
    }
}
