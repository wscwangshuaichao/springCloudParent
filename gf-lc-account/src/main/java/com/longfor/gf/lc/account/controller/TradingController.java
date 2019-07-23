package com.longfor.gf.lc.account.controller;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiTradingService;
import com.longfor.gf.lc.account.business.TradingBusiness;
import com.longfor.gf.lc.account.dto.*;
import com.longfor.gf.lc.account.req.ParamaterReq;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TradingController
 * @Author jiangdan
 * @Date 2019/5/22 15:06
 **/
@Slf4j
@RestController
public class TradingController implements ApiTradingService {

    @Resource
    private TradingBusiness tradingBusiness;

    @Override
    public BaseResponse<PageInfo<TransactionDto>> getList(@RequestBody(required = false) TradingSearchReq req) {
        PageInfo<TransactionDto> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(req.getPageNum() > 0 ? req.getPageNum() : 1);
        pageInfo.setPageSize(req.getPageSize() > 0 ? req.getPageSize() : 10);
        return tradingBusiness.queryPage(req, pageInfo);
    }

    @Override
    public BaseResponse<TransactionDto> getDetailByTransNo(@RequestBody(required = false) TradingSearchReq req) {
        return tradingBusiness.getDetailByTransNo(req);
    }

    @Override
    public BaseResponse<AccTradingSummaryDto> getTradingMoney(@RequestBody(required = false) TradingSearchReq req) {
        return tradingBusiness.getTradingMoney(req);
    }

    @Override
    public BaseResponse<List<TransactionDto>> redPacketsByBatchNo(@PathVariable String batchNo) {
        return tradingBusiness.redPacketsByBatchNo(batchNo);
    }

    @Override
    public BaseResponse<TransferDto> getBatchNo() {
        return tradingBusiness.createBatchNo();
    }

    @Override
    public BaseResponse<List<TransactionDto>> redPacketsByAccNo(@PathVariable String accNo) {
        return tradingBusiness.redPacketsByAccNo(accNo);
    }

    @Override
    public BaseResponse unfreeze(@PathVariable Integer key) {
        return tradingBusiness.unfreeze(key);
    }

    @Override
    public BaseResponse<List<ParamaterDto>> getBusinessTypes(@RequestBody(required = false) ParamaterReq paramaterReq){
        return tradingBusiness.getBusinessTypes(paramaterReq);
    }

    @Override
    public BaseResponse<List<AccTransactionDto>> queryH5TransPage(@RequestBody(required = false) TradingSearchReq req) {
        return tradingBusiness.selectH5TransPage(req);
    }

    @Override
    public BaseResponse<PageInfo<AccTransactionDto>> selectH5TransPage(@RequestBody(required = false) TradingSearchReq req) {
        PageInfo<AccTransactionDto> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(req.getPageNum() > 0 ? req.getPageNum() : 1);
        pageInfo.setPageSize(req.getPageSize() > 0 ? req.getPageSize() : 10);
        return tradingBusiness.queryH5TransPage(req, pageInfo);
    }

    @Override
    public BaseResponse<AccTradingSummaryDto> getAmounts(@RequestBody(required = false) TradingSearchReq req){
        return tradingBusiness.getAmounts(req);
    }
}
