package com.longfor.gf.lc.account.controller;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiTransferService;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.manager.TransferManager;
import com.longfor.gf.lc.account.req.TransferBatchReq;
import com.longfor.gf.lc.account.req.TransferReq;
import com.longfor.gf.lc.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author guoguangxiao
 * @date 2019/5/24 14:41:13
 */
@Slf4j
@RestController
public class TransferController implements ApiTransferService {

    @Resource
    private TransferManager transferManager;

    @Resource
    private AccountService accountService;

    @Override
    public BaseResponse<TransferDto> transfer(@RequestBody(required = false) TransferReq req) {
        return transferManager.transfer(req);
    }

    @Override
    public BaseResponse<TransferDto> prepare(@RequestBody(required = false) TransferReq req) {
        return transferManager.prepare(req);
    }

    @Override
    public BaseResponse<TransferDto> confirm(@RequestBody(required = false) TransferReq req) {
        return transferManager.confirm(req);
    }

    @Override
    public BaseResponse<TransferDto> cancel(@RequestBody(required = false) TransferReq req) {
        return transferManager.cancel(req);
    }

    @Override
    public BaseResponse<TransferDto> batchTransfer(@RequestBody(required = false) TransferBatchReq req) {
        return transferManager.batchTransfer(req);
    }

    @Override
    public boolean checkBalanceEnough(@RequestParam(value = "accNo") String accNo, @RequestParam(value = "amount") String amount) {
        log.info("[RequestParameter] - [TransferController] - method:[checkBalanceEnough] - req:[{}-{}]", accNo, amount);
        long curTime = System.currentTimeMillis();
        boolean result;
        try {
            AccountDto account = accountService.queryAccount(accNo, accNo.substring(0,2));
            BigDecimal balanceAmt = account.getBalanceAmt();
            result = balanceAmt.compareTo(new BigDecimal(amount))>=0;
        } catch (ServiceException e) {
            log.error("[Exception] - [TransferController] - method:[checkBalanceEnough] - req:[{}-{}]", accNo, amount ,e);
            result = false;
        }
        log.info("[ResponseParameter] - [TransferController] - method:[checkBalanceEnough] - req:[{}-{}] - result:[{}] - duration:[{}]",
                accNo, amount, result, System.currentTimeMillis()-curTime);
        return result;
    }

    @Override
    public BaseResponse refund(@PathVariable("batchNo") String batchNo){
        return transferManager.refund(batchNo);
    }
}
