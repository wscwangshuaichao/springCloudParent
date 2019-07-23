package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.JlAccountMapper;
import com.longfor.gf.lc.account.dao.ZeroJlAccountMapper;
import com.longfor.gf.lc.account.dao.entity.JlAccount;
import com.longfor.gf.lc.account.dao.entity.ZeroJlAccount;
import com.longfor.gf.lc.account.dao.entity.example.JlAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.JlAccountReq;
import com.longfor.gf.lc.account.service.JlAccountService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JlAccountServiceImpl
 * @Author jiangdan
 * @Date 2019/5/17 11:23
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JlAccountServiceImpl implements JlAccountService {

    @Resource
    private JlAccountMapper jlAccountMapper;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private ZeroJlAccountMapper zeroJlAccountMapper;
    @Resource
    private RedisLockService redisLockService;


    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true)
    public int save(JlAccountReq accountReq) throws ServiceException {
        accountReq.checkAdd();
        int num = 0 ;
        String lockKey = accountReq.getPersonAd();
        try{
            if(redisLockService.lock(lockKey,2000,20000)){
                JlAccount jlAccount = dealSaveData(accountReq);
                num = jlAccountMapper.insert(jlAccount);
                if(num > 0){
                    //清零账户
                    ZeroJlAccount zeroJlAccount = new ZeroJlAccount();
                    zeroJlAccount.setAccNo(Constants.ZERO_PREFIX + jlAccount.getAccNo());
                    zeroJlAccount.setPersonAd(jlAccount.getPersonAd());
                    zeroJlAccountMapper.insert(zeroJlAccount);
                }
            }
        }catch (Exception e){
            throw new ServiceException(CodeEnum.BizCode.SYS_ERROR.getCode(),e.getMessage());
        }finally {
            redisLockService.unlock(lockKey);
        }

        return num;
    }

    private JlAccount dealSaveData(JlAccountReq accountReq) throws ServiceException {
        JlAccountExample jlAccountExample = new JlAccountExample();
        jlAccountExample.createCriteria()
                .andPersonAdEqualTo(accountReq.getPersonAd())
                .andDeleteEqualTo(Constants.NO);
        List<JlAccount> list = jlAccountMapper.selectByExample(jlAccountExample);
        if(CollectionUtils.isNotEmpty(list)){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getPersonAd() + CodeEnum.BizCode.EXIST.getMsg());
        }
        Date date = new Date();
        JlAccount jlAccount = MyBeanUtils.copyProperties(accountReq, JlAccount.class);
        jlAccount.setCreateTime(date);
        jlAccount.setModifyTime(date);
        jlAccount.setBalanceAmt(BigDecimal.ZERO);
        jlAccount.setFrozenAmt(BigDecimal.ZERO);
        jlAccount.setDelete(Constants.NO);
        jlAccount.setStatus(accountReq.getStatus() != null ? accountReq.getStatus() : Constants.YES);
        jlAccount.setAccNo(sequenceService.createAccNo(TypeEnum.Account.EMBRAVE.getKey()));
        return jlAccount;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", key = "#jlAccountReq.toMd5String(#page.pageNum, #page.pageSize)")
    public PageInfo<AccountDto> queryPage(JlAccountReq jlAccountReq, PageInfo page) {
        JlAccountExample jlAccountExample = dealParam(jlAccountReq);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<JlAccount> list = jlAccountMapper.selectByExample(jlAccountExample);
        page.setList(MyBeanUtils.copyProperties(list, AccountDto.class));
        page.setTotal(jlAccountMapper.selectCountByExample(jlAccountExample));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "JLACCOUNT", key = "#accNo", unless = "#result eq null")
    public JlAccount queryByAccNo(String accNo) {
        JlAccountExample example = new JlAccountExample();
        example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO);
        return jlAccountMapper.selectOneByExample(example);
    }

    private JlAccountExample dealParam(JlAccountReq jlAccountReq){
        JlAccountExample jlAccountExample = new JlAccountExample();
        JlAccountExample.Criteria criteria = jlAccountExample.createCriteria();
        if(jlAccountReq != null) {
            if (StringUtils.isNotBlank(jlAccountReq.getAccNo())) {
                criteria.andAccNoEqualTo(jlAccountReq.getAccNo());
            }
            if (StringUtils.isNotBlank(jlAccountReq.getPersonAd())) {
                criteria.andPersonAdEqualTo(jlAccountReq.getPersonAd());
            }
            if (jlAccountReq.getBalanceAmt() != null) {
                criteria.andBalanceAmtEqualTo(jlAccountReq.getBalanceAmt());
            }
            if (StringUtils.isNotBlank(jlAccountReq.getStartDate())) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDateTime(jlAccountReq.getStartDate()));
            }
            if (StringUtils.isNotBlank(jlAccountReq.getEndDate())) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.parseDateTime(jlAccountReq.getEndDate()));
            }
            if(jlAccountReq.getStatus() != null){
                criteria.andStatusEqualTo(jlAccountReq.getStatus());
            }
        }
        criteria.andDeleteEqualTo(Constants.NO);
        return jlAccountExample;
    }

    @Override
    public JlAccount queryByJlAccountReq(JlAccountReq jlAccountReq) throws ServiceException {
        if(jlAccountReq == null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        JlAccountExample jlAccountExample = dealParam(jlAccountReq);
        List<JlAccount> list = jlAccountMapper.selectByExample(jlAccountExample);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true)
    public int batchSave(List<JlAccountReq> list) throws ServiceException {
        if(CollectionUtils.isNotEmpty(list)){
            List<JlAccount> jlAccounts = new ArrayList<>();
            List<ZeroJlAccount> zeroJlAccounts = new ArrayList<>();
            List<String> personAds = new ArrayList<>();
            for(JlAccountReq jlAccountReq : list){
                jlAccountReq.checkAdd();
                if(CollectionUtils.isNotEmpty(personAds) && personAds.contains(jlAccountReq.getPersonAd())){
                    continue;
                }
                JlAccount jlAccount = dealSaveData(jlAccountReq);
                jlAccounts.add(jlAccount);

                // 清零账户
                ZeroJlAccount zeroJlAccount = new ZeroJlAccount();
                zeroJlAccount.setAccNo(Constants.ZERO_PREFIX + jlAccount.getAccNo());
                zeroJlAccount.setPersonAd(jlAccountReq.getPersonAd());
                zeroJlAccounts.add(zeroJlAccount);

                personAds.add(jlAccountReq.getPersonAd());
            }
            int num = jlAccountMapper.insertList(jlAccounts);
            if(num > 0){
                zeroJlAccountMapper.insertList(zeroJlAccounts);
            }
            return num;
        }
        return 0;
    }

    @Override
    public JlAccount queryByPersonAd(String ad) throws ServiceException {
        if(StringUtils.isBlank(ad)){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        List<JlAccount> list = this.queryListByPersonAd(ad);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public List<JlAccount> queryListByPersonAd(String ad){
        JlAccountExample jlAccountExample = new JlAccountExample();
        JlAccountExample.Criteria criteria = jlAccountExample.createCriteria();
        if (StringUtils.isNotBlank(ad)) {
            criteria.andPersonAdEqualTo(ad);
        }
        criteria.andDeleteEqualTo(Constants.NO);
        criteria.andStatusEqualTo(Constants.YES);
        return jlAccountMapper.selectByExample(jlAccountExample);
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT", key = "#accountReq.accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true)
//    })
    public int update(JlAccountReq accountReq) throws ServiceException {
        JlAccount selJlAccount = this.checkAccNoIsExist(accountReq.getAccNo());
        JlAccount jlAccount = new JlAccount();
        jlAccount.setStatus(accountReq.getStatus());
        jlAccount.setAccNo(selJlAccount.getAccNo());
        jlAccount.setModifyTime(new Date());
        int result = jlAccountMapper.updateByPrimaryKeySelective(jlAccount);
        if(result > 0){
            //更新清零信息
            ZeroJlAccount dbZeroJlAccount = zeroJlAccountMapper.selectByPrimaryKey(Constants.ZERO_PREFIX+accountReq.getAccNo());
            ZeroJlAccount reqZeroJlAccount = new ZeroJlAccount();
            reqZeroJlAccount.setPersonAd(accountReq.getPersonAd());
            ZeroJlAccount zeroJlAccount = MyBeanUtils.compareObj(dbZeroJlAccount, reqZeroJlAccount);
            if(zeroJlAccount!=null){
                zeroJlAccount.setAccNo(dbZeroJlAccount.getAccNo());
                zeroJlAccountMapper.updateByPrimaryKeySelective(zeroJlAccount);
            }
        }
        return result;
    }

    private JlAccount checkAccNoIsExist(String accNo) throws ServiceException {
        JlAccount selJlAccount = this.queryByAccNo(accNo);
        if(selJlAccount == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        return selJlAccount;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT", key = "#accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "JLACCOUNT_PAGE", allEntries = true)
//    })
    public int delete(String accNo) throws ServiceException {
        this.checkAccNoIsExist(accNo);
        JlAccount jlAccount = new JlAccount();
        jlAccount.setModifyTime(new Date());
        jlAccount.setAccNo(accNo);
        jlAccount.setDelete(Constants.YES);
        return jlAccountMapper.updateByPrimaryKeySelective(jlAccount);
    }
}
