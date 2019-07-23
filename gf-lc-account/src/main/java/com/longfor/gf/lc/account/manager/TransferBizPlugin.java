package com.longfor.gf.lc.account.manager;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.req.TransferBatchReq;
import com.longfor.gf.lc.account.req.TransferReq;

/**
 * @author guoguangxiao
 * @date 2019/5/24 10:36:31
 */
public interface TransferBizPlugin {

    /**
     * Method: 转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 11:33
     * @param
     * @return
     */
    BaseResponse<TransferDto> transfer(TransferReq req) throws Exception;

    /**
     * Method: 预转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/28 17:06
     * @param
     * @return
     */
    BaseResponse<TransferDto> prepare(TransferReq req) throws Exception;

    /**
     * Method: 转账确认
     * Description:
     * Author guoguangxiao
     * Data 2019/5/28 17:07
     * @param
     * @return
     */
    BaseResponse<TransferDto> confirm(TransferReq req) throws Exception;

    /**
     * Method: 转账取消
     * Description:
     * Author guoguangxiao
     * Data 2019/5/28 17:07
     * @param
     * @return
     */
    BaseResponse<TransferDto> cancel(TransferReq req) throws Exception;

    /**
     * Method: 批量转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 11:59
     * @param
     * @return
     */
    BaseResponse<TransferDto> batchTransfer(TransferBatchReq req) throws Exception;

    /**
     * @description 退款
     * @author jiangdan
     * @date 2019/5/30 11:40
     * @param[batchNo]
     * @return com.longfor.gaia.gfs.core.response.BaseResponse
     */
    BaseResponse refund(String batchNo) throws Exception;
}
