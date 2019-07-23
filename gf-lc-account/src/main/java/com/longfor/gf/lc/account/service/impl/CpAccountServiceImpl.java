package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.CpAccountMapper;
import com.longfor.gf.lc.account.dao.ZeroCpAccountMapper;
import com.longfor.gf.lc.account.dao.entity.CpAccount;
import com.longfor.gf.lc.account.dao.entity.ZeroCpAccount;
import com.longfor.gf.lc.account.dao.entity.example.CpAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.CpAccountReq;
import com.longfor.gf.lc.account.service.CpAccountService;
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
 * @author jiangdan
 * @date 2019/5/17 10:03:10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CpAccountServiceImpl implements CpAccountService {

    @Resource
    private CpAccountMapper cpAccountMapper;
    @Resource
    private ZeroCpAccountMapper zeroCpAccountMapper;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private RedisLockService redisLockService;

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true)
    public int save(CpAccountReq accountReq) throws ServiceException {
        accountReq.checkAdd();
        int num = 0 ;
        String lockKey = accountReq.getCpCode() + accountReq.getCpName();
        try{
            if(redisLockService.lock(lockKey,2000,20000)) {
                CpAccount cpAccount = dealSaveData(accountReq);
                num = cpAccountMapper.insert(cpAccount);
                if (num > 0) {
                    ZeroCpAccount zeroCpAccount = new ZeroCpAccount();
                    zeroCpAccount.setCpCode(accountReq.getCpCode());
                    zeroCpAccount.setAccNo(Constants.ZERO_PREFIX + cpAccount.getAccNo());
                    zeroCpAccountMapper.insert(zeroCpAccount);
                }
            }
        }catch (Exception e){
            throw new ServiceException(CodeEnum.BizCode.SYS_ERROR.getCode(),e.getMessage());
        }finally {
            redisLockService.unlock(lockKey);
        }
        return num;
    }

    private CpAccount dealSaveData(CpAccountReq accountReq) throws ServiceException {
        List<CpAccount> list = this.queryListByCpCode(accountReq.getCpCode());
        if(CollectionUtils.isNotEmpty(list)){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getCpCode() + CodeEnum.BizCode.EXIST.getMsg());
        }
        Date date = new Date();
        CpAccount cpAccount = MyBeanUtils.copyProperties(accountReq, CpAccount.class);
        cpAccount.setBalanceAmt(BigDecimal.ZERO);
        cpAccount.setFrozenAmt(BigDecimal.ZERO);
        cpAccount.setCreateTime(date);
        cpAccount.setModifyTime(date);
        cpAccount.setDelete(Constants.NO);
        cpAccount.setStatus(accountReq.getStatus() != null ? accountReq.getStatus() : Constants.YES);
        // 获取序列号
        String seqcode = sequenceService.createAccNo(TypeEnum.Account.PRODUCT.getKey());
        cpAccount.setAccNo(seqcode);
        return cpAccount;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", key = "#cpAccountReq.toMd5String(#page.pageNum, #page.pageSize)")
    public PageInfo<AccountDto> queryPage(CpAccountReq cpAccountReq, PageInfo page) {
        CpAccountExample cpAccountExample = dealParam(cpAccountReq);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CpAccount> list = cpAccountMapper.selectByExample(cpAccountExample);
        page.setList(MyBeanUtils.copyProperties(list, AccountDto.class));
        page.setTotal(cpAccountMapper.selectCountByExample(cpAccountExample));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "CPACCOUNT", key = "#accNo", unless = "#result eq null")
    public CpAccount queryByAccNo(String accNo) {
        CpAccountExample example = new CpAccountExample();
        example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO);
        return cpAccountMapper.selectOneByExample(example);
    }

    private CpAccountExample dealParam(CpAccountReq cpAccountReq){
        CpAccountExample cpAccountExample = new CpAccountExample();
        CpAccountExample.Criteria criteria = cpAccountExample.createCriteria();
        if(cpAccountReq != null) {
            if (StringUtils.isNotBlank(cpAccountReq.getAccNo())) {
                criteria.andAccNoEqualTo(cpAccountReq.getAccNo());
            }
            if (StringUtils.isNotBlank(cpAccountReq.getCpCode())) {
                criteria.andCpCodeEqualTo(cpAccountReq.getCpCode());
            }
            if (StringUtils.isNotBlank(cpAccountReq.getCpName())) {
                criteria.andCpNameEqualTo(cpAccountReq.getCpName());
            }
            if (cpAccountReq.getBalanceAmt() != null) {
                criteria.andBalanceAmtEqualTo(cpAccountReq.getBalanceAmt());
            }
            if (StringUtils.isNotBlank(cpAccountReq.getStartDate())) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDateTime(cpAccountReq.getStartDate()));
            }
            if (StringUtils.isNotBlank(cpAccountReq.getEndDate())) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.parseDateTime(cpAccountReq.getEndDate()));
            }
            if(cpAccountReq.getStatus() != null){
                criteria.andStatusEqualTo(cpAccountReq.getStatus());
            }
        }
        criteria.andDeleteEqualTo(Constants.NO);
        return cpAccountExample;
    }

    @Override
    public CpAccount queryByCpAccountReq(CpAccountReq cpAccountReq) throws ServiceException {
        if(cpAccountReq == null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        CpAccountExample cpAccountExample = dealParam(cpAccountReq);
        List<CpAccount> list = cpAccountMapper.selectByExample(cpAccountExample);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true)
    public int batchSave(List<CpAccountReq> list) throws ServiceException {
        if(CollectionUtils.isNotEmpty(list)){
            List<CpAccount> cpAccounts = new ArrayList<>();
            List<ZeroCpAccount> zeroCpAccounts = new ArrayList<>();
            List<String> cpCodes = new ArrayList<>();
            for(CpAccountReq cpAccountReq : list){
                cpAccountReq.checkAdd();
                if(CollectionUtils.isNotEmpty(cpCodes) && cpCodes.contains(cpAccountReq.getCpCode())){
                    continue;
                }
                CpAccount cpAccount = dealSaveData(cpAccountReq);
                cpAccounts.add(cpAccount);

                // 清零账户
                ZeroCpAccount zeroCpAccount = new ZeroCpAccount();
                zeroCpAccount.setCpCode(cpAccount.getCpCode());
                zeroCpAccount.setAccNo(Constants.ZERO_PREFIX + cpAccount.getAccNo());
                zeroCpAccounts.add(zeroCpAccount);
                cpCodes.add(cpAccountReq.getCpCode());
            }
            int num = cpAccountMapper.insertList(cpAccounts);
            if(num > 0){
                zeroCpAccountMapper.insertList(zeroCpAccounts);
            }
            return num;
        }
        return 0;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT", key = "#accountReq.accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true)
//    })
    public int update(CpAccountReq accountReq) throws ServiceException {
        CpAccount cpAccount = new CpAccount();
        BeanUtils.copyProperties(accountReq, cpAccount);
        CpAccount selCpAccount = this.checkAccNoIsExist(accountReq.getAccNo());
        CpAccount entity = MyBeanUtils.compareObj(selCpAccount, cpAccount);
        if(StringUtils.isNotBlank(accountReq.getCpCode())) {
            //查询产品编号是否已存在
            CpAccountExample cpAccountExample = new CpAccountExample();
            cpAccountExample.createCriteria().andCpCodeEqualTo(accountReq.getCpCode()).andDeleteEqualTo(Constants.NO);
            CpAccount cpAcc = cpAccountMapper.selectOneByExample(cpAccountExample);
            if (cpAcc != null && !selCpAccount.getAccNo().equals(cpAcc.getAccNo())) {
                throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getCpCode() + CodeEnum.BizCode.EXIST.getMsg());
            }
        }
        if(entity != null){
            CpAccount cpAccountEntity = new CpAccount();
            cpAccountEntity.setAccNo(selCpAccount.getAccNo());
            cpAccountEntity.setModifyTime(new Date());
            cpAccountEntity.setStatus(accountReq.getStatus());
            cpAccountEntity.setCpCode(accountReq.getCpCode());
            cpAccountEntity.setCpName(accountReq.getCpName());
            int result = cpAccountMapper.updateByPrimaryKeySelective(cpAccountEntity);
            if(result > 0){
                //更新清零信息
                ZeroCpAccount dbZeroCpAccount = zeroCpAccountMapper.selectByPrimaryKey(Constants.ZERO_PREFIX+accountReq.getAccNo());
                ZeroCpAccount reqZeroCpAccount = new ZeroCpAccount();
                reqZeroCpAccount.setCpCode(accountReq.getCpCode());
                ZeroCpAccount zeroCpAccount = MyBeanUtils.compareObj(dbZeroCpAccount, reqZeroCpAccount);
                if(zeroCpAccount!=null){
                    zeroCpAccount.setAccNo(dbZeroCpAccount.getAccNo());
                    zeroCpAccountMapper.updateByPrimaryKeySelective(zeroCpAccount);
                }
            }
            return result;
        }
        throw new ServiceException(CodeEnum.BizCode.NO_CHANGE_ERROR);
    }

    private CpAccount checkAccNoIsExist(String accNo) throws ServiceException {
        CpAccount selCpAccount = this.queryByAccNo(accNo);
        if(selCpAccount == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        return selCpAccount;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT", key = "#accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "CPACCOUNT_PAGE", allEntries = true)
//    })
    public int delete(String accNo) throws ServiceException {
        this.checkAccNoIsExist(accNo);
        CpAccount cpAccount = new CpAccount();
        cpAccount.setModifyTime(new Date());
        cpAccount.setAccNo(accNo);
        cpAccount.setDelete(Constants.YES);
        return cpAccountMapper.updateByPrimaryKeySelective(cpAccount);
    }

    @Override
    public List<CpAccount> queryListByCpCode(String cpCode) {
        CpAccountExample cpAccountExample = new CpAccountExample();
        CpAccountExample.Criteria criteria = cpAccountExample.createCriteria();
        if (StringUtils.isNotBlank(cpCode)) {
            criteria.andCpCodeEqualTo(cpCode);
        }
        criteria.andDeleteEqualTo(Constants.NO);
        return cpAccountMapper.selectByExample(cpAccountExample);
    }

}
