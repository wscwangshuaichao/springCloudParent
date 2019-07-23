package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.QtAccountMapper;
import com.longfor.gf.lc.account.dao.ZeroQtAccountMapper;
import com.longfor.gf.lc.account.dao.entity.QtAccount;
import com.longfor.gf.lc.account.dao.entity.ZeroQtAccount;
import com.longfor.gf.lc.account.dao.entity.example.QtAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.QtAccountReq;
import com.longfor.gf.lc.account.service.QtAccountService;
import com.longfor.gf.lc.account.service.RedisLockService;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName QtAccountServiceImpl
 * @Author jiangdan
 * @Date 2019/5/17 11:30
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class QtAccountServiceImpl implements QtAccountService {

    @Resource
    private QtAccountMapper qtAccountMapper;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private ZeroQtAccountMapper zeroQtAccountMapper;
    @Resource
    private RedisLockService redisLockService;

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
    public int save(QtAccountReq accountReq) throws ServiceException {
        accountReq.checkAdd();
        int num = 0 ;
        String lockKey = accountReq.getQtCode() + accountReq.getQtName();
        try{
            if(redisLockService.lock(lockKey,2000,20000)) {
                QtAccount qtAccount = dealSaveData(accountReq);
                num = qtAccountMapper.insert(qtAccount);
                if(num > 0){
                    ZeroQtAccount zeroQtAccount = new ZeroQtAccount();
                    zeroQtAccount.setAccNo(Constants.ZERO_PREFIX + qtAccount.getAccNo());
                    zeroQtAccount.setQtCode(qtAccount.getQtCode());
                    zeroQtAccountMapper.insert(zeroQtAccount);
                }
            }
        }catch (Exception e){
            throw new ServiceException(CodeEnum.BizCode.SYS_ERROR.getCode(),e.getMessage());
        }finally {
            redisLockService.unlock(lockKey);
        }
        return num;
    }

    private QtAccount dealSaveData(QtAccountReq accountReq) throws ServiceException {
        List<QtAccount> list = this.queryListByQtCode(accountReq.getQtCode());
        if(CollectionUtils.isNotEmpty(list)){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getQtCode() + CodeEnum.BizCode.EXIST.getMsg());
        }
        Date date = new Date();
        QtAccount qtAccount = MyBeanUtils.copyProperties(accountReq, QtAccount.class);
        qtAccount.setBalanceAmt(BigDecimal.ZERO);
        qtAccount.setFrozenAmt(BigDecimal.ZERO);
        qtAccount.setDelete(Constants.NO);
        qtAccount.setStatus(accountReq.getStatus() != null ? accountReq.getStatus() : Constants.YES);
        qtAccount.setAccNo(sequenceService.createAccNo(TypeEnum.Account.OTHER.getKey()));
        qtAccount.setCreateTime(date);
        qtAccount.setModifyTime(date);
        return qtAccount;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", key = "#qtAccountReq.toMd5String(#page.pageNum, #page.pageSize)")
    public PageInfo<AccountDto> queryPage(QtAccountReq qtAccountReq, PageInfo page) {
        QtAccountExample qtAccountExample = dealParam(qtAccountReq);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<QtAccount> list = qtAccountMapper.selectByExample(qtAccountExample);
        page.setList(MyBeanUtils.copyProperties(list, AccountDto.class));
        page.setTotal(qtAccountMapper.selectCountByExample(qtAccountExample));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "QTACCOUNT", key = "#accNo", unless = "#result eq null")
    public QtAccount queryByAccNo(String accNo) {
        QtAccountExample example = new QtAccountExample();
        example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO);
        return qtAccountMapper.selectOneByExample(example);
    }

    private QtAccountExample dealParam(QtAccountReq qtAccountReq){
        QtAccountExample qtAccountExample = new QtAccountExample();
        QtAccountExample.Criteria criteria = qtAccountExample.createCriteria();
        if(qtAccountReq != null) {
            if (StringUtils.isNotBlank(qtAccountReq.getAccNo())) {
                criteria.andAccNoEqualTo(qtAccountReq.getAccNo());
            }
            if (StringUtils.isNotBlank(qtAccountReq.getQtCode())) {
                criteria.andQtCodeEqualTo(qtAccountReq.getQtCode());
            }
            if (StringUtils.isNotBlank(qtAccountReq.getQtName())) {
                criteria.andQtNameEqualTo(qtAccountReq.getQtName());
            }
            if (qtAccountReq.getBalanceAmt() != null) {
                criteria.andBalanceAmtEqualTo(qtAccountReq.getBalanceAmt());
            }
            if (StringUtils.isNotBlank(qtAccountReq.getStartDate())) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDateTime(qtAccountReq.getStartDate()));
            }
            if (StringUtils.isNotBlank(qtAccountReq.getEndDate())) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.parseDateTime(qtAccountReq.getEndDate()));
            }
            if(qtAccountReq.getStatus() != null){
                criteria.andStatusEqualTo(qtAccountReq.getStatus());
            }
        }
        criteria.andDeleteEqualTo(Constants.NO);
        return qtAccountExample;
    }

    @Override
    public QtAccount queryByQtAccountReq(QtAccountReq qtAccountReq) throws ServiceException {
        if(qtAccountReq == null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        QtAccountExample qtAccountExample = dealParam(qtAccountReq);
        List<QtAccount> list = qtAccountMapper.selectByExample(qtAccountExample);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
    public int batchSave(List<QtAccountReq> list) throws ServiceException {
        if(CollectionUtils.isNotEmpty(list)){
            List<QtAccount> qtAccounts = new ArrayList<>();
            List<ZeroQtAccount> zeroQtAccounts = new ArrayList<>();
            List<String> qtCodes = new ArrayList<>();
            for(QtAccountReq qtAccountReq : list){
                qtAccountReq.checkAdd();
                if(CollectionUtils.isNotEmpty(qtCodes) && qtCodes.contains(qtAccountReq.getQtCode())){
                    continue;
                }
                QtAccount qtAccount = dealSaveData(qtAccountReq);
                qtAccounts.add(qtAccount);

                //清零账户
                ZeroQtAccount zeroQtAccount = new ZeroQtAccount();
                zeroQtAccount.setAccNo(Constants.ZERO_PREFIX + qtAccount.getAccNo());
                zeroQtAccount.setQtCode(qtAccount.getQtCode());
                zeroQtAccounts.add(zeroQtAccount);

                qtCodes.add(qtAccountReq.getQtCode());
            }
            int num = qtAccountMapper.insertList(qtAccounts);
            if(num > 0){
                zeroQtAccountMapper.insertList(zeroQtAccounts);
            }
            return num;
        }
        return 0;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT", key = "#accountReq.accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
//    })
    public int update(QtAccountReq accountReq) throws ServiceException {
        QtAccount qtAccount = new QtAccount();
        BeanUtils.copyProperties(accountReq, qtAccount);
        QtAccount selQtAccount = this.checkAccNoIsExist(accountReq.getAccNo());
        QtAccount entity = MyBeanUtils.compareObj(selQtAccount, qtAccount);
        //查询其他编号是否已存在
        if(StringUtils.isNotBlank(accountReq.getQtCode())) {
            QtAccountExample qtAccountExample = new QtAccountExample();
            qtAccountExample.createCriteria().andQtCodeEqualTo(accountReq.getQtCode()).andDeleteEqualTo(Constants.NO);
            QtAccount qtAcc = qtAccountMapper.selectOneByExample(qtAccountExample);
            if (qtAcc != null && !selQtAccount.getAccNo().equals(qtAcc.getAccNo())) {
                throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getQtCode() + CodeEnum.BizCode.EXIST.getMsg());
            }
        }
        if(entity != null){
            QtAccount qtAccountEntity = new QtAccount();
            qtAccountEntity.setAccNo(selQtAccount.getAccNo());
            qtAccountEntity.setModifyTime(new Date());
            qtAccountEntity.setStatus(accountReq.getStatus());
            qtAccountEntity.setQtCode(accountReq.getQtCode());
            qtAccountEntity.setQtName(accountReq.getQtName());
            int result = qtAccountMapper.updateByPrimaryKeySelective(qtAccountEntity);
            if(result > 0){
                //更新清零信息
                ZeroQtAccount dbZeroQtAccount = zeroQtAccountMapper.selectByPrimaryKey(Constants.ZERO_PREFIX+accountReq.getAccNo());
                ZeroQtAccount reqZeroQtAccount = new ZeroQtAccount();
                reqZeroQtAccount.setQtCode(accountReq.getQtCode());
                ZeroQtAccount zeroQtAccount = MyBeanUtils.compareObj(dbZeroQtAccount, reqZeroQtAccount);
                if(zeroQtAccount!=null){
                    zeroQtAccount.setAccNo(dbZeroQtAccount.getAccNo());
                    zeroQtAccountMapper.updateByPrimaryKeySelective(zeroQtAccount);
                }
            }
            return result;
        }
        throw new ServiceException(CodeEnum.BizCode.NO_CHANGE_ERROR);
    }

    private QtAccount checkAccNoIsExist(String accNo) throws ServiceException {
        QtAccount selQtAccount = this.queryByAccNo(accNo);
        if(selQtAccount == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        return selQtAccount;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT", key = "#accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "QTACCOUNT_PAGE", allEntries = true)
//    })
    public int delete(String accNo) throws ServiceException {
        this.checkAccNoIsExist(accNo);
        QtAccount qtAccount = new QtAccount();
        qtAccount.setModifyTime(new Date());
        qtAccount.setAccNo(accNo);
        qtAccount.setDelete(Constants.YES);
        return qtAccountMapper.updateByPrimaryKeySelective(qtAccount);
    }

    @Override
    public List<QtAccount> queryListByQtCode(String qtCode) {
        QtAccountExample qtAccountExample = new QtAccountExample();
        QtAccountExample.Criteria criteria = qtAccountExample.createCriteria();
        if (StringUtils.isNotBlank(qtCode)) {
            criteria.andQtCodeEqualTo(qtCode);
        }
        criteria.andStatusEqualTo(Constants.YES);
        return qtAccountMapper.selectByExample(qtAccountExample);
    }

}
