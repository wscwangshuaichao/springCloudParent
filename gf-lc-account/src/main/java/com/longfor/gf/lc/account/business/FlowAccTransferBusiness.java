package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountInfoReq;
import com.longfor.gf.lc.account.req.TransactionReq;
import com.longfor.gf.lc.account.service.AccountService;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.MyBeanUtils;
import com.longfor.gf.lc.account.util.ResultUtils;
import com.longfor.gf.lc.account.util.TypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author guoguangxiao
 * @date 2019/5/24 11:18:03
 */
@Service
public class FlowAccTransferBusiness extends AbstractTransferBusiness {

    @Resource
    private AccountService accountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    protected BaseResponse<TransferDto> dealTransData(TransactionReq req, boolean confirmFlag) throws ServiceException {
        BaseResponse response = ResultUtils.getSuccess();
        //转出账户
        String outAcctType = baseBusiness.getAccType(req.getAccOut());
        boolean isExist = accountService.isExistAccByAccNo(req.getAccOut(), outAcctType);
        //判断转出账户是否存在
        if(!isExist){
            return ResultUtils.getResponse(response, CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getCode(),
                    CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getMsg() + req.getAccOut());
        }
        //获取转入账户信息
        String inAcctType = baseBusiness.getAccType(req.getAccIn());
        AccountDto inAcc = getAccountInfo(req.getAccIn());
        //业务账户只能给个人账户转账
        if(TypeEnum.Account.BUSINESS.getKey().equals(outAcctType) && !TypeEnum.Account.PERSONAL.getKey().equals(inAcctType)){
            return ResultUtils.getResponse(response, CodeEnum.BizCode.ACCOUNT_TYPE_NOT_SUPPORT);
        }
        //保存
        TransactionDto transactionDto = accountService.saveTransaction(req);
        if (transactionDto == null) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        //修改转入账户余额
        if(confirmFlag){
            int num = updateAccAmt(req, inAcctType, inAcc);
            if (num <= 0) {
                throw new ServiceException(CodeEnum.BizCode.FAIL);
            }
        }

        response.setData(MyBeanUtils.copyProperties(transactionDto, TransferDto.class));
        return ResultUtils.getResponse(response, CodeEnum.BizCode.SUCCESS);
    }

    private int updateAccAmt(TransactionReq req, String accType, AccountDto accountDto) throws ServiceException {
        AccountInfoReq accountReq = new AccountInfoReq();
        accountReq.setAccType(accType);
        accountReq.setAccNo(req.getAccIn());
        accountReq.setBalanceAmt(accountDto.getBalanceAmt().add(new BigDecimal(req.getAmount())));
        return accountService.updateBalanceOrFrozen(accountReq);
    }

}
