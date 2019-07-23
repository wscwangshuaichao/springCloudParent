package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.response.BaseResponse;
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
 * @date 2019/5/24 11:18:48
 */
@Service
public class BalanceAccTransferBusiness extends AbstractTransferBusiness {

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
        //获取转入账户信息
        String inAcctType = baseBusiness.getAccType(req.getAccIn());
        AccountDto inAcc = getAccountInfo(req.getAccIn());

        //校验红包规则
        checkTransferRulesLimit(req,outAcctType,outAcc.getPersonAd(),inAcctType,inAcc.getPersonAd());

        //保存
        TransactionDto transactionDto = accountService.saveTransaction(req);
        if (transactionDto == null) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        //修改转出账户余额
        int num = updateAccOutAmt(req, outAcctType, outAcc);
        if (num <= 0) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        //转账类型不为群发红包、个人红包 修改转入账户余额
        if(confirmFlag && !Constants.GROUP_REDPACKET_TYPE.equals(req.getBusinessType())
                && !Constants.SINGLE_REDPACKET_TYPE.equals(req.getBusinessType())){
            num = updateAccInAmt(req, inAcctType, inAcc);
            if (num <= 0) {
                throw new ServiceException(CodeEnum.BizCode.FAIL);
            }
        }
        //红包退款，关闭冻结明细
        if(Constants.REFUND_REDPACKET_TYPE.equals(req.getBusinessType())){
            num = accountService.updateTransStatus(req.getBatchNo(), req.getBusinessType());
            if (num <= 0) {
                throw new ServiceException(CodeEnum.BizCode.FAIL);
            }
        }
        response.setData(MyBeanUtils.copyProperties(transactionDto,TransferDto.class));
        return ResultUtils.getResponse(response, CodeEnum.BizCode.SUCCESS);
    }

    private int updateAccOutAmt(TransactionReq req, String accType, AccountDto accountDto) throws ServiceException {
        AccountInfoReq accountReq = new AccountInfoReq();
        accountReq.setAccType(accType);
        accountReq.setAccNo(req.getAccOut());
        boolean flag = Constants.REFUND_REDPACKET_TYPE.equals(req.getBusinessType())
                || Constants.GRAB_REDPACKET_TYPE.equals(req.getBusinessType());
        //转账类型不为红包退款、抢红包，余额减少
        if(!flag) {
            accountReq.setBalanceAmt(accountDto.getBalanceAmt().subtract(new BigDecimal(req.getAmount())));
        }
        //转账类型为群发红包、个人红包
        if(Constants.GROUP_REDPACKET_TYPE.equals(req.getBusinessType())
                ||Constants.SINGLE_REDPACKET_TYPE.equals(req.getBusinessType())){
            accountReq.setFrozenAmt(accountDto.getFrozenAmt().add(new BigDecimal(req.getAmount())));
        }
        //转账类型为红包退款、抢红包 冻结金额减少
        if(flag){
            accountReq.setFrozenAmt(accountDto.getFrozenAmt().subtract(new BigDecimal(req.getAmount())));
        }
        return accountService.updateBalanceOrFrozen(accountReq);
    }

    private int updateAccInAmt(TransactionReq req, String accType, AccountDto accountDto) throws ServiceException {
        AccountInfoReq accountReq = new AccountInfoReq();
        accountReq.setAccType(accType);
        accountReq.setAccNo(req.getAccIn());
        accountReq.setBalanceAmt(accountDto.getBalanceAmt().add(new BigDecimal(req.getAmount())));
        return accountService.updateBalanceOrFrozen(accountReq);
    }

    /**
     * Method: 检验转账规则
     * Description:
     * Author guoguangxiao
     * Data 2019/6/14 11:59
     * @param
     * @return
     */
    private void checkTransferRulesLimit(TransactionReq req,String outAccType,String outAccPersonAd,String inAccType,String inAccPersonAd) throws ServiceException{
        //通用转账规则校验
        checkCommonRulesLimit(outAccType,outAccPersonAd,inAccType,inAccPersonAd);
        //特殊业务转账规则校验
        String businessType = req.getBusinessType();
        if(Constants.SINGLE_REDPACKET_TYPE.equals(businessType) || Constants.GROUP_REDPACKET_TYPE.equals(businessType)){
            checkRedPacketRulesLimit(req);
        }else if(Constants.REWARD_TYPE.equals(businessType)){
            checkRewardRulesLimit(req,inAccType);
        }
    }

    /**
     * Method: 转账通用规则校验
     * Description:
     * Author guoguangxiao
     * Data 2019/6/14 14:40
     * @param
     * @return
     */
    private void checkCommonRulesLimit(String outAccType,String outAccPersonAd,String inAccType,String inAccPersonAd) throws ServiceException{
        //任何情况下同一个人的激励账号都不能转账到个人账户
        if(TypeEnum.Account.EMBRAVE.getKey().equals(outAccType) && TypeEnum.Account.PERSONAL.getKey().equals(inAccType) &&
                outAccPersonAd.equals(inAccPersonAd)) {
            throw new ServiceException(CodeEnum.BizCode.JLACCOUNT_TRANS_ERROR);
        }
        //个人不能给激励业务账户转账
        if(TypeEnum.Account.PERSONAL.getKey().equals(outAccType) &&
                (TypeEnum.Account.EMBRAVE.getKey().equals(inAccType) || TypeEnum.Account.BUSINESS.getKey().equals(inAccType))){
            throw new ServiceException(CodeEnum.BizCode.JLACCOUNT_TRANS_ERROR.getCode(),"个人账户不能给激励(或业务)账户转账");
        }
    }
    /**
     * Method: 发红包规则检验
     * Description:
     * 红包校验规则：
     * 1.群红包：jl到jl(冻结)、gr到gr(冻结)
     * 2.个人红包：gr到gr(非冻结)、jl到gr(非冻结)
     * Author guoguangxiao
     * Data 2019/6/14 13:40
     * @param
     * @return
     */
    private void checkRedPacketRulesLimit(TransactionReq req) throws ServiceException {
        if((Constants.GROUP_REDPACKET_TYPE.equals(req.getBusinessType()) || Constants.SINGLE_REDPACKET_TYPE.equals(req.getBusinessType()))
                && !req.getAccOut().equals(req.getAccIn())){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_TRANS_ERROR.getCode(),"发红包转入转出账户不相等");
        }
    }

    /**
     * Method: 打赏规则检验
     * Description: 打赏规则：gr到gr(非冻结)、jl到gr(非冻结)
     * Author guoguangxiao
     * Data 2019/6/14 14:15
     * @param
     * @return
     */
    private void checkRewardRulesLimit(TransactionReq req, String inAccType) throws ServiceException{
        if(req.getAccOut().equals(req.getAccIn())){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_TRANS_ERROR.getCode(),"不能打赏自己");
        }else if(!TypeEnum.Account.PERSONAL.getKey().equals(inAccType)){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_TRANS_ERROR.getCode(),"只能打赏到个人账户");
        }
    }

}
