package com.longfor.gf.lc.account.service.impl;

import com.longfor.gf.lc.account.dao.*;
import com.longfor.gf.lc.account.dao.entity.*;
import com.longfor.gf.lc.account.dao.entity.example.TransactionExample;
import com.longfor.gf.lc.account.dao.entity.example.YwAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountInfoReq;
import com.longfor.gf.lc.account.req.TransactionReq;
import com.longfor.gf.lc.account.service.AccountService;
import com.longfor.gf.lc.account.service.RedisLockService;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Author jiangdan
 * @Date 2019/5/24 9:49
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private CpAccountMapper cpAccountMapper;
    @Resource
    private JlAccountMapper jlAccountMapper;
    @Resource
    private GrAccountMapper grAccountMapper;
    @Resource
    private QtAccountMapper qtAccountMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private CbAccountMapper cbAccountMapper;
    @Resource
    private YwAccountMapper ywAccountMapper;
    @Resource
    private GatGrAccountMapper gatGrAccountMapper;
    @Resource
    private RedisLockService redisLockService;

    @Override
    public AccountDto queryAccount(String accNo, String accType) throws ServiceException {
        RequestCheckUtils.checkNotEmpty(accNo,"accNo");
        RequestCheckUtils.checkNotEmpty(accType,"accType");
        AccountDto accountDto = null;
        if(StringUtils.equals(TypeEnum.Account.EMBRAVE.getKey(), accType)){
            accountDto =  accountMapper.selectJlAccByAccNo(accNo);
        }else if(StringUtils.equals(TypeEnum.Account.PRODUCT.getKey(), accType)){
            accountDto =  accountMapper.selectCpAccByAccNo(accNo);
        }else if(StringUtils.equals(TypeEnum.Account.PERSONAL.getKey(), accType)){
            accountDto =  accountMapper.selectGrAccByAccNo(accNo);
        }else if(StringUtils.equals(TypeEnum.Account.OTHER.getKey(), accType)){
            accountDto =  accountMapper.selectQtAccByAccNo(accNo);
        }else if(TypeEnum.Account.BUSINESS.getKey().equals(accType)){
            YwAccountExample example = new YwAccountExample();
            example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO).andStatusEqualTo(Constants.YES);
            YwAccount ywAccount = ywAccountMapper.selectOneByExample(example);
            accountDto = MyBeanUtils.copyProperties(ywAccount, AccountDto.class);
        }
        return accountDto;
    }

    @Override
    public TransactionDto saveTransaction(TransactionReq req) throws ServiceException {
        Transaction transaction = MyBeanUtils.copyProperties(req, Transaction.class);
        transaction.setTransNo(getTransNo());
        transaction.setBatchNo(getBatchNo(req.getBusinessType(), req.getBatchNo()));
        transaction.setOutTransNo(req.getOutTransNo());
        Date date = new Date();
        transaction.setTransType(getTransType(req));
        transaction.setBusinessType(req.getBusinessType());
        transaction.setAccInAmt(new BigDecimal(req.getAmount()));
        transaction.setAccOutAmt(new BigDecimal(req.getAmount()));
        transaction.setStatus(getTrueStatus(req));
        transaction.setCreateTime(date);
        transaction.setModifyTime(date);
        transaction.setYyyy(Integer.parseInt(DateUtil.dateToString(date, DateUtil.YYYY)));
        transaction.setMm(Integer.parseInt(DateUtil.dateToString(date, DateUtil.MM)));
        transaction.setDd(Integer.parseInt(DateUtil.dateToString(date, DateUtil.DD)));
        int num = transactionMapper.insert(transaction);
        TransactionDto transactionDto = MyBeanUtils.copyProperties(transaction, TransactionDto.class);
        return num > 0 ? transactionDto : null;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT", key = "#req.accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT", key = "#req.accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT", key = "#req.accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT", key = "#req.accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
    })
    public int updateBalanceOrFrozen(AccountInfoReq req) throws ServiceException {
        req.check();
        if(StringUtils.equals(TypeEnum.Account.EMBRAVE.getKey(), req.getAccType())){
            JlAccount jlAccount = MyBeanUtils.copyProperties(req, JlAccount.class);
            jlAccount.setModifyTime(new Date());
            return jlAccountMapper.updateByPrimaryKeySelective(jlAccount);
        }else if(StringUtils.equals(TypeEnum.Account.PRODUCT.getKey(), req.getAccType())){
            CpAccount cpAccount = MyBeanUtils.copyProperties(req, CpAccount.class);
            cpAccount.setModifyTime(new Date());
            return cpAccountMapper.updateByPrimaryKeySelective(cpAccount);
        }else if(StringUtils.equals(TypeEnum.Account.PERSONAL.getKey(), req.getAccType())){
            GrAccount grAccount = MyBeanUtils.copyProperties(req, GrAccount.class);
            grAccount.setModifyTime(new Date());
            return grAccountMapper.updateByPrimaryKeySelective(grAccount);
        }else if(StringUtils.equals(TypeEnum.Account.OTHER.getKey(), req.getAccType())){
            QtAccount qtAccount = MyBeanUtils.copyProperties(req, QtAccount.class);
            qtAccount.setModifyTime(new Date());
            return qtAccountMapper.updateByPrimaryKeySelective(qtAccount);
        }
        return 0;
    }

    @Override
    public boolean isExistAccByAccNo(String accNo,  String type) {
        if(Constants.CB_PREFIX.equals(type)){
            return cbAccountMapper.existsWithPrimaryKey(accNo);
        }
        if(TypeEnum.Account.BUSINESS.getKey().equals(type)){
            YwAccountExample example = new YwAccountExample();
            example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO).andStatusEqualTo(Constants.YES);
            YwAccount ywAccount = ywAccountMapper.selectOneByExample(example);
            return ywAccount != null;
        }
        return false;
    }

    @Override
    public GatGrAccount saveGatAccount(String accNo, String personAd, String jobNo) throws ServiceException {
        String gatAccNo = TypeEnum.Account.GAT.getKey()+ "-" + accNo;
        GatGrAccount entity = null;
        String lockKey = accNo;
        try{
            if(redisLockService.lock(lockKey,2000,5000)){
                entity = gatGrAccountMapper.selectByPrimaryKey(gatAccNo);
                if(entity == null){
                    entity = new GatGrAccount();
                    entity.setAccNo(gatAccNo);
                    entity.setJobNo(jobNo);
                    entity.setPersonAd(personAd);
                    entity.setStatus(Constants.YES);
                    int num = gatGrAccountMapper.insert(entity);
                    entity =  num > 0 ? entity : null;
                }
            }
        }catch(Exception e){
            log.error("[Exception] - [AccountService] - method:[saveGatAccount] - errorMsg:[{}]", e.getMessage(),e);
        }finally {
            redisLockService.unlock(lockKey);
        }
        return entity;
    }

    @Override
    public int updateTransStsByTransNoAndOutNo(String transNo, String outTransNo, String transSts) {
        if(StringUtils.isEmpty(transNo) || StringUtils.isEmpty(outTransNo) || !TypeEnum.TransStatus.isExist(transSts)){
            log.info("[RequestParameter] - [AccountService] - method:[updateTransStsByTransNoAndOutNo] - transNo:[{}] - outTransNo:[{}] - transSts:[{}] - [请求参数错误]",
                    transNo, outTransNo, transSts);
            return 0;
        }
        TransactionExample example = new TransactionExample();
        example.createCriteria().andTransNoEqualTo(transNo).andOutTransNoEqualTo(outTransNo);
        Transaction transaction = transactionMapper.selectOneByExample(example);
        if(transaction == null || !TypeEnum.TransStatus.PROCESSING.getCode().equals(transaction.getStatus())){
            return 0;
        }
        Transaction transaction4Update = new Transaction();
        transaction4Update.setKeyId(transaction.getKeyId());
        transaction4Update.setStatus(transSts);
        return transactionMapper.updateByPrimaryKeySelective(transaction4Update);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT", key = "#accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT", key = "#accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT", key = "#accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true),
            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT", key = "#accNo"),
            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
    })
    public int updateAccBalance(String accNo, String changeAmount, int operType) {
        if(StringUtils.isEmpty(accNo) || StringUtils.isEmpty(changeAmount)){
            return 0;
        }
        BigDecimal chAmount = new BigDecimal(changeAmount);
        String tableName = null;
        if(accNo.startsWith(TypeEnum.Account.EMBRAVE.getKey())){
            tableName = "t_jl_account";
        }else if(accNo.startsWith(TypeEnum.Account.PRODUCT.getKey())){
            tableName = "t_cp_account";
        }else if(accNo.startsWith(TypeEnum.Account.PERSONAL.getKey())){
            tableName = "t_gr_account";
        }else if(accNo.startsWith(TypeEnum.Account.OTHER.getKey())){
            tableName = "t_qt_account";
        }
        if(StringUtils.isNotEmpty(tableName)){
            return operType>0?accountMapper.incrAccBalance(accNo,chAmount,tableName):accountMapper.decrAccBalance(accNo,chAmount,tableName);
        }
        //非余额账户默认成功
        return 1;
    }

    @Override
    public int updateTransStatus(String batchNo, String businessType) throws ServiceException {
        if(StringUtils.isBlank(batchNo) || StringUtils.isBlank(businessType)){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        Transaction transaction = new Transaction();
        transaction.setStatus(TypeEnum.TransStatus.FINISHED.getCode());
        transaction.setModifyTime(new Date());
        TransactionExample example = new TransactionExample();
        example.createCriteria().andBatchNoEqualTo(batchNo)
                .andTransTypeEqualTo(TypeEnum.TransType.FREEZE.getCode());
        return transactionMapper.updateByExampleSelective(transaction, example);
    }

    @Override
    public BigDecimal selectGrabAmountByAccNo(String batchNo, String accNo) {
        return transactionMapper.selectGrabAmountByAccNo(batchNo, accNo);
    }

    /**
     * @description
     * @author jiangdan
     * @date 2019/5/29 10:40
     * @param[confirmFlag, businessType]
     * @return java.lang.String
     */
    private String getTrueStatus(TransactionReq req){
        if(Constants.GROUP_REDPACKET_TYPE.equals(req.getBusinessType()) ||
                Constants.SINGLE_REDPACKET_TYPE.equals(req.getBusinessType()) ||
                Constants.INTEGRAL_TYPE.equals(req.getBusinessType())){
            return TypeEnum.TransStatus.PROCESSING.getCode();
        }
        return TypeEnum.TransStatus.FINISHED.getCode();
    }

    /**
     * @description
     * @author jiangdan
     * @date 2019/5/29 10:40
     * @param[confirmFlag, businessType]
     * @return java.lang.String
     */
    private String getTransType(TransactionReq req){
        String transType = TypeEnum.TransType.TRANSFER.getCode();
        if(Constants.GROUP_REDPACKET_TYPE.equals(req.getBusinessType())
                || Constants.SINGLE_REDPACKET_TYPE.equals(req.getBusinessType())){
            transType = TypeEnum.TransType.FREEZE.getCode();
        }else if(Constants.REFUND_REDPACKET_TYPE.equals(req.getBusinessType())){
            transType = TypeEnum.TransType.UNFREEZE.getCode();
        }
        return transType;
    }

    private String getBatchNo(String businessType, String batchNo) throws ServiceException {
        //抢红包时验证批次号是否存在
        if((Constants.GRAB_REDPACKET_TYPE.equals(businessType)
                || Constants.REFUND_REDPACKET_TYPE.equals(businessType))) {
            if(StringUtils.isNotBlank(batchNo)) {
                TransactionExample example = new TransactionExample();
                example.createCriteria().andBatchNoEqualTo(batchNo);
                List<Transaction> list = transactionMapper.selectByExample(example);
                if (CollectionUtils.isEmpty(list)) {
                    throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
                }
                return batchNo;
            }
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        //交易流水号
        return sequenceService.createBatchNo();
    }

    private String getTransNo(){
        String transNo = sequenceService.getTrxNo();
        boolean flag = isExstRedTransNo(transNo);
        if(!flag){
            getTransNo();
        }
        return transNo;
    }

    private boolean isExstRedTransNo(String transNo){
        TransactionExample example = new TransactionExample();
        example.createCriteria().andTransNoEqualTo(transNo);
        List<Transaction> list = transactionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list);
    }

}
