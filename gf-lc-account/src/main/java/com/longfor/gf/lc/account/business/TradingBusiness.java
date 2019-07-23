package com.longfor.gf.lc.account.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.Paramater;
import com.longfor.gf.lc.account.dao.entity.example.TransactionExample;
import com.longfor.gf.lc.account.dto.*;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.ParamaterReq;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import com.longfor.gf.lc.account.service.ParamaterService;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.service.TransactionService;
import com.longfor.gf.lc.account.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TradingBusiness
 * @Author jiangdan
 * @Date 2019/5/22 15:10
 **/
@Slf4j
@Component
public class TradingBusiness {

    @Resource
    private TransactionService transactionService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private ParamaterService paramaterService;


    public BaseResponse<PageInfo<TransactionDto>> queryPage(TradingSearchReq req, PageInfo page) {
        log.info("[RequestParameter] - [TradingBusiness] - method:[queryPage] - req:[{}] - page:[{}]", JSON.toJSONString(req), JSON.toJSONString(page));
        long startTime = System.currentTimeMillis();
        BaseResponse<PageInfo<TransactionDto>> response = ResultUtils.getSuccess();
        try{
            PageInfo<TransactionDto> pageInfo = transactionService.queryPage(req, page);
            response.setData(pageInfo);
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[queryPage] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[queryPage] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<TransactionDto> getDetailByTransNo(TradingSearchReq req){
        log.info("[RequestParameter] - [TradingBusiness] - method:[getDetailByTransNo] - req:[{}]", JSON.toJSONString(req));
        long startTime = System.currentTimeMillis();
        BaseResponse<TransactionDto> response = ResultUtils.getSuccess();
        try{
            if(req == null){
                return ResultUtils.getResponse(response,CodeEnum.BizCode.PARAM_NOT_EMPTY);
            }
            TransactionDto dto = transactionService.queryByTransactionReq(req);
            response.setData(dto);
        }catch (ServiceException se){
            log.error("[Exception] - [TradingBusiness] - method:[getDetailByTransNo] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[getDetailByTransNo] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[getDetailByTransNo] - response:[{}] - duration:[{}]",
                JSON.toJSONString(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<AccTradingSummaryDto> getTradingMoney(TradingSearchReq req){
        log.info("[RequestParameter] - [TradingBusiness] - method:[getTradingMoney] - req:[{}]", JSON.toJSONString(req));
        long startTime = System.currentTimeMillis();
        BaseResponse<AccTradingSummaryDto> response = ResultUtils.getSuccess();
        try{
            AccTradingSummaryDto dto = transactionService.queryTradingMoney(req);
            response.setData(dto);
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[getTradingMoney] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[getTradingMoney] - response:[{}] - duration:[{}]",
                JSON.toJSONString(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<List<TransactionDto>> redPacketsByBatchNo(String batchNo){
        log.info("[RequestParameter] - [TradingBusiness] - method:[redPacketsByBatchNo] - req:[{}]", batchNo);
        long startTime = System.currentTimeMillis();
        BaseResponse<List<TransactionDto>> response = ResultUtils.getSuccess();
        try{
            if(StringUtils.isBlank(batchNo)){
                return ResultUtils.getResponse(response, CodeEnum.BizCode.PARAM_NOT_EMPTY);
            }
            TransactionExample transactionExample = new TransactionExample();
            TransactionExample.Criteria criteria = transactionExample.createCriteria();
            criteria.andBatchNoEqualTo(batchNo);
            criteria.andTransTypeEqualTo(TypeEnum.TransType.TRANSFER.getCode());
            List<TransactionDto> list = transactionService.queryTransactionByExample(transactionExample);
            response.setData(list);
            if(CollectionUtils.isEmpty(list)){
                response = ResultUtils.getResponse(response, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (ServiceException se){
            log.error("[Exception] - [TradingBusiness] - method:[redPacketsByBatchNo] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[redPacketsByBatchNo] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[redPacketsByBatchNo] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<TransferDto> createBatchNo(){
        log.info("[RequestParameter] - [TradingBusiness] - method:[createBatchNo]");
        long startTime = System.currentTimeMillis();
        BaseResponse<TransferDto> response = ResultUtils.getSuccess();
        try{
            String batchNo = sequenceService.createBatchNo();
            TransferDto transferDto = new TransferDto();
            transferDto.setBatchNo(batchNo);
            response.setData(transferDto);
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[createBatchNo] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[createBatchNo] - response:[{}] - duration:[{}]",
                JSON.toJSONString(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<List<TransactionDto>> redPacketsByAccNo(String accno){
        log.info("[RequestParameter] - [TradingBusiness] - method:[redPacketsByAccNo] - req:[{}]", accno);
        long startTime = System.currentTimeMillis();
        BaseResponse<List<TransactionDto>> response = ResultUtils.getSuccess();
        try{
            if(StringUtils.isBlank(accno)){
                return ResultUtils.getResponse(response, CodeEnum.BizCode.PARAM_NOT_EMPTY);
            }
            TradingSearchReq tradingSearchReq = new TradingSearchReq();
            tradingSearchReq.setAccNo(accno);
            List<TransactionDto> list = transactionService.redPacketsByAccNo(tradingSearchReq);
            response.setData(list);
            if(CollectionUtils.isEmpty(list)){
                response = ResultUtils.getResponse(response, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[redPacketsByAccNo] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[redPacketsByAccNo] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse unfreeze(Integer keyId){
        log.info("[RequestParameter] - [TradingBusiness] - method:[unfreeze] - req:[{}]", keyId);
        long startTime = System.currentTimeMillis();
        BaseResponse response = ResultUtils.getSuccess();
        try{
            if(keyId == null){
                return ResultUtils.getResponse(response, CodeEnum.BizCode.PARAM_NOT_EMPTY);
            }
            int num = transactionService.updateStatus(keyId, TypeEnum.TransStatus.FINISHED.getCode());
            response = ResultUtils.getSuccess();
            if(num <= 0){
                response = ResultUtils.getFail();
            }
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[unfreeze] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[unfreeze] - response:[{}] - duration:[{}]",
                JSON.toJSONString(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<List<ParamaterDto>> getBusinessTypes(ParamaterReq paramaterReq){
        log.info("[RequestParameter] - [TradingBusiness] - method:[getBusinessTypes] - req:[{}]", JSON.toJSONString(paramaterReq));
        long startTime = System.currentTimeMillis();
        BaseResponse<List<ParamaterDto>> response = ResultUtils.getSuccess();
        try{
            Paramater paramater = null;
            if(paramaterReq != null){
                paramater = MyBeanUtils.copyProperties(paramaterReq, Paramater.class);
            }
            if(paramater == null){
                paramater = new Paramater();
            }
            List<Paramater> list = paramaterService.selectList(paramater);
            response.setData(MyBeanUtils.copyProperties(list, ParamaterDto.class));
            if(CollectionUtils.isEmpty(list)){
                response = ResultUtils.getResponse(response, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[getBusinessTypes] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[getBusinessTypes] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<List<AccTransactionDto>> selectH5TransPage(TradingSearchReq req) {
        log.info("[RequestParameter] - [TradingBusiness] - method:[selectH5TransPage] - req:[{}]", JSON.toJSONString(req));
        long startTime = System.currentTimeMillis();
        BaseResponse<List<AccTransactionDto>> response = ResultUtils.getSuccess();
        try{
            List<AccTransactionDto> list = transactionService.selectH5TransPage(req);
            response.setData(list);
            if(CollectionUtils.isEmpty(list)){
                response = ResultUtils.getResponse(response, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[selectH5TransPage] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[selectH5TransPage] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<PageInfo<AccTransactionDto>> queryH5TransPage(TradingSearchReq req, PageInfo page) {
        log.info("[RequestParameter] - [TradingBusiness] - method:[queryH5TransPage] - req:[{}]", JSON.toJSONString(req));
        long startTime = System.currentTimeMillis();
        BaseResponse<PageInfo<AccTransactionDto>> response = ResultUtils.getSuccess();
        try{
            PageInfo<AccTransactionDto> pageInfo = transactionService.queryH5TransPage(req, page);
            response.setData(pageInfo);
            if(pageInfo == null){
                response = ResultUtils.getResponse(response, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[queryH5TransPage] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[queryH5TransPage] - response:[{}] - duration:[{}]",
                getResponseLog(response), System.currentTimeMillis()-startTime);
        return response;
    }

    public BaseResponse<AccTradingSummaryDto> getAmounts(TradingSearchReq req){
        log.info("[RequestParameter] - [TradingBusiness] - method:[getAmounts] - req:[{}]", JSON.toJSONString(req));
        long startTime = System.currentTimeMillis();
        BaseResponse<AccTradingSummaryDto> response = ResultUtils.getSuccess();
        try{
            AccTradingSummaryDto dto = transactionService.selectTradingMoney(req);
            response.setData(dto);
        }catch (Exception e){
            log.error("[Exception] - [TradingBusiness] - method:[getAmounts] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [TradingBusiness] - method:[getAmounts] - response:[{}] - duration:[{}]",
                JSON.toJSONString(response), System.currentTimeMillis()-startTime);
        return response;
    }

    /**
     * Method: 打印response日志
     * Description:
     * Author guoguangxiao
     * Data 2019/6/17 16:15
     * @param
     * @return
     */
    private String getResponseLog(BaseResponse baseResponse){
        if(baseResponse==null || baseResponse.getData()==null){
            return null;
        }
        String resLog ;
        Object data = baseResponse.getData();
        if(data instanceof PageInfo && ((PageInfo) data).getList()!=null){
            resLog = baseResponse.getCode()+"|"+baseResponse.getMessage()+"|"+((PageInfo) data).getList().size();
        }else if(data instanceof List && CollectionUtils.isNotEmpty((List)data)){
            resLog = baseResponse.getCode()+"|"+baseResponse.getMessage()+"|"+((List) data).size();
        }else{
            resLog = JSONObject.toJSONString(baseResponse);
        }
        return resLog;
    }

}
