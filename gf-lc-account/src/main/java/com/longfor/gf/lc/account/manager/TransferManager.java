package com.longfor.gf.lc.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.Paramater;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.TransferBatchReq;
import com.longfor.gf.lc.account.req.TransferReq;
import com.longfor.gf.lc.account.service.ParamaterService;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.Constants;
import com.longfor.gf.lc.account.util.ResultUtils;
import com.longfor.gf.lc.account.util.TypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author guoguangxiao
 * @date 2019/5/24 10:36:56
 */
@Slf4j
@Service
public class TransferManager {

    @Value("${transfer.business.type.balance}")
    private String balanceTransferBusinessTypes;
    @Value("${transfer.business.type.flow}")
    private String flowTransferBusinessTypes;
    @Value("${transfer.business.type.zero}")
    private String zeroTransferBusinessTypes;

    @Resource
    Map<String, TransferBizPlugin> transferBizPluginMap;

    @Resource
    private ParamaterService paramaterService;

    /**
     * Method: 转账统一输出接口
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 14:04
     * @param
     * @return
     */
    public BaseResponse<TransferDto> transfer(TransferReq req) {
        log.info("[RequestParameter] - [TransferManager] - method:[transfer] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getBusinessType())).transfer(req);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[transfer] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[transfer] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[transfer] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }


    private String getPluginCode(String businessType) throws ServiceException {
        String transferBizKey = null;
        if(isFlowTransfer(businessType)){
            transferBizKey = Constants.TRANSFER_FLOW;
        }else if(isZeroTransfer(businessType)){
            transferBizKey = Constants.TRANSFER_ZERO;
        }else if(isBalanceTransfer(businessType)){
            transferBizKey = Constants.TRANSFER_BALANCE;
        }
        //校验业务类型是否存在，防止未知业务调用
        if(StringUtils.isNotEmpty(businessType)){
            Paramater paramater = paramaterService.selectOneByParamValue(businessType);
            if(paramater==null || paramater.getParamId()==null){
                throw new ServiceException(CodeEnum.BizCode.BUSINESS_TYPE_NOT_SUPPORT);
            }
        }
        String pluginCode = TypeEnum.Transfer.getValue(transferBizKey);
        if(StringUtils.isEmpty(pluginCode)){
            throw new ServiceException(CodeEnum.BizCode.BUSINESS_TYPE_NOT_CONFIG);
        }
        return pluginCode;

    }

    private TransferBizPlugin getAccountPlugin(String pluginCode) throws ServiceException{
        TransferBizPlugin transferBizPlugin = transferBizPluginMap.get(pluginCode);
        if(transferBizPlugin == null){
            throw new ServiceException(CodeEnum.BizCode.BUSINESS_TYPE_NOT_SUPPORT);
        }
        return transferBizPlugin;
    }

    /**
     * Method: 余额账户转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 13:42
     * @param
     * @return
     */
    private boolean isBalanceTransfer(String businessType){
        if(StringUtils.isEmpty(balanceTransferBusinessTypes) || StringUtils.isEmpty(businessType)){
            return false;
        }
        return balanceTransferBusinessTypes.contains(businessType);
    }
    /**
     * Method: 流水账户转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 13:42
     * @param
     * @return
     */
    private boolean isFlowTransfer(String businessType){
        if(StringUtils.isEmpty(flowTransferBusinessTypes) || StringUtils.isEmpty(businessType)){
            return false;
        }
        return flowTransferBusinessTypes.contains(businessType);
    }
    /**
     * Method: 清零账户转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 13:48
     * @param
     * @return
     */
    private boolean isZeroTransfer(String businessType){
        if(StringUtils.isEmpty(zeroTransferBusinessTypes) || StringUtils.isEmpty(businessType)){
            return false;
        }
        return zeroTransferBusinessTypes.contains(businessType);
    }

    /**
     * Method: 预转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:53
     * @param
     * @return
     */
    public BaseResponse<TransferDto> prepare(TransferReq req) {
        log.info("[RequestParameter] - [TransferManager] - method:[prepare] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getBusinessType())).prepare(req);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[prepare] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[prepare] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[prepare] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }
    /**
     * Method: 转账确认
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:53
     * @param
     * @return
     */
    public BaseResponse<TransferDto> confirm(TransferReq req) {
        log.info("[RequestParameter] - [TransferManager] - method:[confirm] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getBusinessType())).confirm(req);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[confirm] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[confirm] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[confirm] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }
    /**
     * Method: 转账取消
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:55
     * @param
     * @return
     */
    public BaseResponse<TransferDto> cancel(TransferReq req) {
        log.info("[RequestParameter] - [TransferManager] - method:[cancel] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getBusinessType())).cancel(req);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[cancel] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[cancel] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[cancel] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    /**
     * Method: 批量转账
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:54
     * @param
     * @return
     */
    public BaseResponse<TransferDto> batchTransfer(TransferBatchReq req) {
        log.info("[RequestParameter] - [TransferManager] - method:[batchTransfer] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getBusinessType())).batchTransfer(req);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[batchTransfer] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[batchTransfer] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[batchTransfer] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    public BaseResponse refund(String batchNo) {
        log.info("[RequestParameter] - [TransferManager] - method:[refund] - request:[{}]", batchNo);
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            //请求参数校验
            if(StringUtils.isBlank(batchNo)){
                return ResultUtils.getResponse(response, CodeEnum.BizCode.PARAM_NOT_EMPTY);
            }
            response = getAccountPlugin(getPluginCode(Constants.REFUND_REDPACKET_TYPE)).refund(batchNo);
        }catch (ServiceException se){
            log.error("[Exception] - [TransferManager] - method:[refund] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TransferManager] - method:[refund] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TransferManager] - method:[refund] - response:[{}] - duration:[{}]", batchNo ,System.currentTimeMillis()-curTime);
        return response;
    }
}
