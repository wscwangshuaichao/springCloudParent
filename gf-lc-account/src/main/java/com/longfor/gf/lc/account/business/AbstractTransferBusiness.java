package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.Transaction;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.dto.TransferDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.TransferBizPlugin;
import com.longfor.gf.lc.account.req.*;
import com.longfor.gf.lc.account.service.AccountService;
import com.longfor.gf.lc.account.service.TransactionService;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.Constants;
import com.longfor.gf.lc.account.util.ResultUtils;
import com.longfor.gf.lc.account.util.TypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author guoguangxiao
 * @date 2019/5/28 19:36:16
 */
public abstract class AbstractTransferBusiness implements TransferBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private AccountService accountService;
    @Resource
    private BaseBusiness baseBusiness;
    @Resource
    private TransactionService transactionService;

    @Override
    public BaseResponse<TransferDto> transfer(TransferReq req) throws Exception {
        return apiHelper.invokeInTransaction(req.getData(),new Handle<TransactionReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                doTransfer(req.getBusinessType(), handelEntity);
            }
            @Override
            public TransactionReq getRequest() {
                return new TransactionReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "transfer";
            }
        });
    }

    @Override
    public BaseResponse<TransferDto> prepare(TransferReq req) throws Exception {
        return apiHelper.invokeInTransaction(req.getData(),new Handle<TransactionReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                doPrepare(req.getBusinessType(), handelEntity);
            }
            @Override
            public TransactionReq getRequest() {
                return new TransactionReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "prepare";
            }
        });
    }


    @Override
    public BaseResponse<TransferDto> confirm(TransferReq req) throws Exception {
        return apiHelper.invokeInTransaction(req.getData(),new Handle<TransferConfirmReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                doConfirm(req.getBusinessType(), handelEntity);
            }
            @Override
            public TransferConfirmReq getRequest() {
                return new TransferConfirmReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "confirm";
            }
        });
    }

    @Override
    public BaseResponse<TransferDto> cancel(TransferReq req) throws Exception {
        return apiHelper.invokeInTransaction(req.getData(),new Handle<TransferConfirmReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                doCancel(req.getBusinessType(), handelEntity);
            }
            @Override
            public TransferConfirmReq getRequest() {
                return new TransferConfirmReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "cancel";
            }
        });
    }

    @Override
    public BaseResponse<TransferDto> batchTransfer(TransferBatchReq req) throws Exception {
        return apiHelper.invokeInTransaction(req.getData(),new Handle<List<TransferDetailReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                doBatchTransfer(req, handelEntity);
            }
            @Override
            public List<TransferDetailReq> getRequest() {
                return new ArrayList<TransferDetailReq>();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "batchTransfer";
            }
        });
    }

    private void doTransfer(String businessType, HandleEntity handelEntity) throws ServiceException {
        TransactionReq transferReq = (TransactionReq) handelEntity.getRequest();
        baseBusiness.checkBusinessType(businessType, transferReq.getBusinessType());
        String limitRule = transferReq.getAccIn() + transferReq.getAccOut()+ transferReq.getAmount() + transferReq.getOutTransNo();
        baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.TRANSFER, businessType,limitRule,15,1));
        handelEntity.setResponse(dealTransData(transferReq, true));
    }

    protected abstract BaseResponse dealTransData(TransactionReq transferReq, boolean confirmFlag) throws ServiceException;

    private void doPrepare(String businessType, HandleEntity handelEntity) throws ServiceException {
        TransactionReq transferReq = (TransactionReq) handelEntity.getRequest();
        baseBusiness.checkBusinessType(businessType, transferReq.getBusinessType());
        String limitRule = transferReq.getAccIn() + transferReq.getAccOut()+ transferReq.getAmount();
        baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.TRANSFER, businessType,limitRule,30,1));
        handelEntity.setResponse(dealTransData(transferReq, false));
    }
    private void doConfirm(String businessType, HandleEntity handelEntity) throws ServiceException {
        TransferConfirmReq transferReq = (TransferConfirmReq) handelEntity.getRequest();
        //获取交易记录
        Transaction transaction = getTransaction(transferReq.getTransNo(), transferReq.getOutTransNo());
        String limitRule = transferReq.getTransNo() + transferReq.getOutTransNo();
        baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.TRANSFER, businessType,limitRule,5,1));
        int update1 = accountService.updateTransStsByTransNoAndOutNo(transferReq.getTransNo(), transferReq.getOutTransNo(), TypeEnum.TransStatus.FINISHED.getCode());
        if(update1 <= 0){
            throw new ServiceException(CodeEnum.BizCode.TRADING_REPEAT_ERROR);
        }
        //转入账户金额入账
        int update2 = accountService.updateAccBalance(transaction.getAccIn(), transaction.getAccInAmt().toString(), 1);
        if(update2>0){
            handelEntity.setResponse(ResultUtils.getSuccess());
        }else{
            throw new ServiceException(CodeEnum.BizCode.TRADING_ERROR);
        }
    }
    private void doCancel(String businessType, HandleEntity handelEntity) throws ServiceException {
        TransferConfirmReq transferReq = (TransferConfirmReq) handelEntity.getRequest();
        //获取交易记录
        Transaction transaction = getTransaction(transferReq.getTransNo(), transferReq.getOutTransNo());
        //交易记录改为取消
        int update1 = accountService.updateTransStsByTransNoAndOutNo(transferReq.getTransNo(), transferReq.getOutTransNo(), TypeEnum.TransStatus.CANCEL.getCode());
        if(update1 <= 0){
            throw new ServiceException(CodeEnum.BizCode.TRADING_REPEAT_ERROR);
        }
        //转出账户金额返还
        int update2 = accountService.updateAccBalance(transaction.getAccOut(), transaction.getAccOutAmt().toString(), 1);
        if(update2 > 0){
            handelEntity.setResponse(ResultUtils.getSuccess());
        }else{
            throw new ServiceException(CodeEnum.BizCode.TRADING_ERROR);
        }
    }

    protected void doBatchTransfer(TransferBatchReq batchReq, HandleEntity handelEntity) throws ServiceException {
        List<TransferDetailReq> requestList = handelEntity.getRequestList();
        if(CollectionUtils.isEmpty(requestList)){
            handelEntity.setResponse(ResultUtils.getFail());
            return ;
        }
        //转入详情校验
        BigDecimal amountTotal = BigDecimal.ZERO;
        StringBuffer errMsgSb = new StringBuffer();
        for(TransferDetailReq item : requestList){
            try{
                item.check();
            }catch (ServiceException e){
                errMsgSb.append(item.getAccNo()).append(",");
            }
            amountTotal = amountTotal.add(new BigDecimal(item.getAmount()));
        }
        //批量返回结果
        String errMsg = errMsgSb.toString();
        if(StringUtils.isNotEmpty(errMsg)){
            throw new ServiceException(CodeEnum.BizCode.AMOUNT_ERROR.getCode(), errMsg.substring(0,errMsg.length()-1)+"金额超限:10000");
        }
        if(StringUtils.isNotEmpty(batchReq.getTotalAmount()) && (new BigDecimal(batchReq.getTotalAmount()).compareTo(amountTotal)!=0)){
            throw new ServiceException(CodeEnum.BizCode.TRANSFER_AMOUNT_ERROR);
        }
        String limitRule = batchReq.getBatchNo() + batchReq.getAccOut() + batchReq.getTotalAmount() + requestList.get(0).toMd5String();
        baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.BATCHTRANSFER, batchReq.getBusinessType(),limitRule,40,1));
        handelEntity.setResponse(batchDealTransData(batchReq, requestList));
    }

    protected BaseResponse batchDealTransData(TransferBatchReq batchReq, List<TransferDetailReq> transferReqList) throws ServiceException{
        BaseResponse<TransferDto> response = ResultUtils.getSuccess();
        //获取转出账户信息
        AccountDto outAcc = getAccountInfo(batchReq.getAccOut());

        //校验金额
        BigDecimal balance = outAcc.getBalanceAmt();
        if(balance != null && balance.compareTo(new BigDecimal(batchReq.getTotalAmount())) < 0){
            return ResultUtils.getResponse(response, CodeEnum.BizCode.BALANCE_NOT_ENOUGH);
        }

        //批量错误信息提示
        StringBuffer errMsgAccInNotExistSb = new StringBuffer();

        //构建交易流水
        List<TransactionReq> transactionReqList = new ArrayList<>();
        for(TransferDetailReq item : transferReqList){

            AccountDto inAcc = null;
            //激励账户不能向自己的个人账户转账
            try{
                inAcc = getAccountInfo(item.getAccNo());
            }catch (ServiceException e){
                errMsgAccInNotExistSb.append(item.getAccNo()).append(",");
                continue;
            }
            checkTransferRules(outAcc, inAcc, batchReq.getBusinessType());
            TransactionReq transactionReq = new TransactionReq();
            transactionReq.setAccOut(batchReq.getAccOut());
            transactionReq.setAccIn(item.getAccNo());
            transactionReq.setAmount(item.getAmount());
            transactionReq.setBusinessType(batchReq.getBusinessType());
            transactionReq.setBatchNo(batchReq.getBatchNo());
            transactionReq.setRemarks(item.getRemark());
            transactionReqList.add(transactionReq);
        }
        //批量返回结果
        String errMsg = errMsgAccInNotExistSb.toString();
        if(StringUtils.isNotEmpty(errMsg)){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getCode(),  errMsg.substring(0,errMsg.length()-1) + CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        if (transactionService.saveList(transactionReqList) <= 0) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        boolean balanceAccount = isBalanceAccount(batchReq.getAccOut());
        //转出账户账户减金额
        if (balanceAccount && accountService.updateAccBalance(batchReq.getAccOut(), batchReq.getTotalAmount(),0) <= 0) {
            throw new ServiceException(CodeEnum.BizCode.FAIL);
        }
        //转入账户金额增加
        for(TransferDetailReq item : transferReqList){
            if (accountService.updateAccBalance(item.getAccNo(), item.getAmount(),1) <= 0) {
                throw new ServiceException(CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getCode(),CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getMsg()+item.getAccNo());
            }
        }

        return ResultUtils.getSuccess();
    }

    public AccountDto getAccountInfo(String accNo) throws ServiceException{
        String accType = baseBusiness.getAccType(accNo);
        AccountDto account = accountService.queryAccount(accNo, accType);
        if(account == null){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getCode(), CodeEnum.BizCode.ACCOUNT_NOT_EXIST.getMsg() + accNo);
        }
        return account;
    }

    private boolean isBalanceAccount(String accNo){
        if(StringUtils.isEmpty(accNo)){
            return false;
        }
        return accNo.startsWith(TypeEnum.Account.EMBRAVE.getKey()) ||
                accNo.startsWith(TypeEnum.Account.PRODUCT.getKey()) ||
                accNo.startsWith(TypeEnum.Account.PERSONAL.getKey()) ||
                accNo.startsWith(TypeEnum.Account.OTHER.getKey());
    }

    private Transaction getTransaction(String transNo, String outTransNo) throws ServiceException {
        Transaction transaction = transactionService.getTransactionByTransNo(transNo, outTransNo);
        if(transaction == null){
            throw new ServiceException(CodeEnum.BizCode.TRANSACTION_NOT_EXIST);
        }
        return transaction;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse refund(String batchNo) throws Exception {
        return apiHelper.invokeInTransaction(batchNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                 doRefund(batchNo, handelEntity);
            }
            @Override
            public String getRequest() {
                return "refund";
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "refund";
            }
            @Override
            public String getErrorMsg() {
                return "退款失败";
            }
        });
    }

    protected void doRefund(String batchNo, HandleEntity handelEntity) throws ServiceException {
        TradingSearchReq req = new TradingSearchReq();
        req.setBatchNo(batchNo);
        //冻结交易
        req.setTransType(TypeEnum.TransType.FREEZE.getCode());
        TransactionDto freezeTrans = transactionService.queryByTransactionReq(req);
        if(freezeTrans == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        if(!TypeEnum.TransStatus.PROCESSING.getCode().equals(freezeTrans.getStatus())){
            throw new ServiceException(CodeEnum.BizCode.REFUND_ERROR);
        }
        BigDecimal amount = freezeTrans.getAccOutAmt() != null ? freezeTrans.getAccOutAmt() : BigDecimal.ZERO;

        //转账交易(获取已抢红包的金额)
        BigDecimal grabAmount = accountService.selectGrabAmountByAccNo(batchNo, freezeTrans.getAccOut());
        grabAmount = grabAmount != null ? grabAmount : BigDecimal.ZERO;
        // 余额
        BigDecimal balance = amount.subtract(grabAmount);

        String limitRule = freezeTrans.getAccIn() + freezeTrans.getAccOut()+ balance;
        baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.TRANSFER,
                Constants.REFUND_REDPACKET_TYPE,limitRule,15,1));
        /**
         * 已抢红包金额等于发红包总金额，则修改冻结交易记录的交易状态
         * 已抢红包金额小于发红包总金额，则退回剩余金额
         */
        if(balance.compareTo(BigDecimal.ZERO) > 0){
            TransactionReq data = new TransactionReq();
            data.setBatchNo(batchNo);
            data.setAccOut(freezeTrans.getAccOut());
            data.setAccIn(freezeTrans.getAccOut());
            data.setAmount(balance.toString());
            data.setBusinessType(Constants.REFUND_REDPACKET_TYPE);
            data.setRemarks("红包超过24小时退款");
            data.setOutTransNo(UUID.randomUUID().toString().replaceAll("-",""));
            handelEntity.setResponse(dealTransData(data, true));
        }else {
            int num = transactionService.updateStatus(freezeTrans.getKeyId(), TypeEnum.TransStatus.FINISHED.getCode());
            handelEntity.setResponse(ResultUtils.getSuccess());
            if( num <= 0){
                throw new ServiceException(CodeEnum.BizCode.FAIL);
            }
        }
    }

    public AccountDto getAccOutInfo(TransactionReq req) throws ServiceException {
        AccountDto outAcc = getAccountInfo(req.getAccOut());
        BigDecimal balance = outAcc.getBalanceAmt() != null ? outAcc.getBalanceAmt() : BigDecimal.ZERO;
        BigDecimal frozen = outAcc.getFrozenAmt() != null ? outAcc.getFrozenAmt() : BigDecimal.ZERO;
        //判断余额是否足够(除了红包退款)
        if(!Constants.REFUND_REDPACKET_TYPE.equals(req.getBusinessType()) && balance.compareTo(new BigDecimal(req.getAmount())) < 0){
            throw new ServiceException(CodeEnum.BizCode.BALANCE_NOT_ENOUGH);
        }
        //红包退款：判断冻结金额是否大于等于退款金额
        if(Constants.REFUND_REDPACKET_TYPE.equals(req.getBusinessType()) && frozen.compareTo(new BigDecimal(req.getAmount())) < 0){
            throw new ServiceException(CodeEnum.BizCode.BALANCE_NOT_ENOUGH);
        }
        return outAcc;
    }

    private void checkTransferRules(AccountDto outAcc, AccountDto inAcc, String businessType) throws ServiceException{

        //冻结业务外,同一账户不能转账，批量接口不支持冻结
        if(StringUtils.equals(outAcc.getAccNo(),inAcc.getAccNo())){
            throw new ServiceException(CodeEnum.BizCode.JLACCOUNT_TRANS_ERROR.getCode(),outAcc.getAccNo()+"该接口不支持自我账户转账");
        }

        //激励账户不能向自己个人账户转账
        String outAccType = baseBusiness.getAccType(outAcc.getAccNo());
        if(TypeEnum.Account.EMBRAVE.getKey().equals(outAccType)){
            String inAccType = baseBusiness.getAccType(inAcc.getAccNo());
            if(TypeEnum.Account.PERSONAL.getKey().equals(inAccType) && StringUtils.equals(outAcc.getPersonAd(),inAcc.getPersonAd())){
                throw new ServiceException(CodeEnum.BizCode.JLACCOUNT_TRANS_ERROR.getCode(),outAcc.getAccNo()+CodeEnum.BizCode.JLACCOUNT_TRANS_ERROR.getMsg());
            }
        }

    }

}
