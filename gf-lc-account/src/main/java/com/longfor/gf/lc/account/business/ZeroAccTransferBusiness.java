package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.GatGrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountInfoReq;
import com.longfor.gf.lc.account.req.TransactionReq;
import com.longfor.gf.lc.account.service.AccountService;
import com.longfor.gf.lc.account.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author guoguangxiao
 * @date 2019/5/24 11:19:23
 */
@Service
public class ZeroAccTransferBusiness extends AbstractTransferBusiness {

    @Resource
    private AccountService accountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    protected BaseResponse dealTransData(TransactionReq req, boolean confirmFlag) throws ServiceException {
        BaseResponse<TransferDto> response = ResultUtils.getSuccess();
        //获取转出账户信息
        String outAcctType = baseBusiness.getAccType(req.getAccOut());
        AccountDto outAcc = getAccOutInfo(req);
        //积分兑换
        if(Constants.INTEGRAL_TYPE.equals(req.getBusinessType())){
            GatGrAccount gatGrAccount = accountService.saveGatAccount(req.getAccOut(),outAcc.getPersonAd(), req.getJobNo());
            req.setAccIn(gatGrAccount.getAccNo());
        }
        //保存
        TransactionDto transactionDto = accountService.saveTransaction(req);
        if (transactionDto == null) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        //修改转出账户余额
        int num = updateAccAmt(req, outAcctType, outAcc);
        if (num <= 0) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        response.setData(MyBeanUtils.copyProperties(transactionDto, TransferDto.class));
        return ResultUtils.getResponse(response, CodeEnum.BizCode.SUCCESS);
    }

    private int updateAccAmt(TransactionReq req, String accType, AccountDto accountDto) throws ServiceException {
        AccountInfoReq accountReq = new AccountInfoReq();
        accountReq.setAccType(accType);
        accountReq.setAccNo(req.getAccOut());
        accountReq.setBalanceAmt(accountDto.getBalanceAmt().subtract(new BigDecimal(req.getAmount())));
        return accountService.updateBalanceOrFrozen(accountReq);
    }

}
