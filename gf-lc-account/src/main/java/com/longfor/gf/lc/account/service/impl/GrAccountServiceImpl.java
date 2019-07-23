package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.GrAccountMapper;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dao.entity.example.GrAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.GrAccountReq;
import com.longfor.gf.lc.account.service.GrAccountService;
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
 * @author jiangdan
 * @date 2019/5/17 10:03:10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GrAccountServiceImpl implements GrAccountService {

    @Resource
    private GrAccountMapper grAccountMapper;
    @Resource
    private SequenceService sequenceService;

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true)
    public int save(GrAccountReq accountReq) throws ServiceException {
        accountReq.checkAdd();
        return grAccountMapper.insert(dealSaveData(accountReq));
    }

    private GrAccount dealSaveData(GrAccountReq accountReq) throws ServiceException {
        GrAccountExample grAccountExample = new GrAccountExample();
        grAccountExample.createCriteria()
                .andPersonAdEqualTo(accountReq.getPersonAd())
                .andDeleteEqualTo(Constants.NO);
        List<GrAccount> list = grAccountMapper.selectByExample(grAccountExample);
        if(CollectionUtils.isNotEmpty(list)){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getPersonAd() + CodeEnum.BizCode.EXIST.getMsg());
        }
        Date date = new Date();
        GrAccount grAccount = MyBeanUtils.copyProperties(accountReq, GrAccount.class);
        grAccount.setCreateTime(date);
        grAccount.setModifyTime(date);
        grAccount.setDelete(Constants.NO);
        grAccount.setBalanceAmt(BigDecimal.ZERO);
        grAccount.setFrozenAmt(BigDecimal.ZERO);
        grAccount.setStatus(accountReq.getStatus() != null ? accountReq.getStatus() : Constants.YES);
        String accNo = sequenceService.createAccNo(TypeEnum.Account.PERSONAL.getKey());
        grAccount.setAccNo(accNo);
        return grAccount;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", key = "#grAccountReq.toMd5String(#page.pageNum, #page.pageSize)")
    public PageInfo<AccountDto> queryPage(GrAccountReq grAccountReq, PageInfo page) {
        GrAccountExample grAccountExample = dealParam(grAccountReq);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GrAccount> list = grAccountMapper.selectByExample(grAccountExample);
        page.setList(MyBeanUtils.copyProperties(list, AccountDto.class));
        page.setTotal(grAccountMapper.selectCountByExample(grAccountExample));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "GRACCOUNT", key = "#accNo", unless = "#result eq null")
    public GrAccount queryByAccNo(String accNo) {
        GrAccountExample example = new GrAccountExample();
        example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO);
        return grAccountMapper.selectOneByExample(example);
    }

    private GrAccountExample dealParam(GrAccountReq grAccountReq){
        GrAccountExample grAccountExample = new GrAccountExample();
        GrAccountExample.Criteria criteria = grAccountExample.createCriteria();
        if(grAccountReq != null) {
            if (StringUtils.isNotBlank(grAccountReq.getAccNo())) {
                criteria.andAccNoEqualTo(grAccountReq.getAccNo());
            }
            if (StringUtils.isNotBlank(grAccountReq.getPersonAd())) {
                criteria.andPersonAdEqualTo(grAccountReq.getPersonAd());
            }
            if (grAccountReq.getBalanceAmt() != null) {
                criteria.andBalanceAmtEqualTo(grAccountReq.getBalanceAmt());
            }
            if (StringUtils.isNotBlank(grAccountReq.getStartDate())) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDateTime(grAccountReq.getStartDate()));
            }
            if (StringUtils.isNotBlank(grAccountReq.getEndDate())) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.parseDateTime(grAccountReq.getEndDate()));
            }
            if(grAccountReq.getStatus() != null){
                criteria.andStatusEqualTo(grAccountReq.getStatus());
            }
        }
        criteria.andDeleteEqualTo(0);
        return grAccountExample;
    }

    @Override
    public GrAccount queryByGrAccountReq(GrAccountReq grAccountReq) throws ServiceException {
        if(grAccountReq == null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        GrAccountExample grAccountExample = dealParam(grAccountReq);
        List<GrAccount> list = grAccountMapper.selectByExample(grAccountExample);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true)
    public int batchSave(List<GrAccountReq> list) throws ServiceException {
        if(CollectionUtils.isNotEmpty(list)){
            List<GrAccount> grAccounts = new ArrayList<>();
            List<String> personAds = new ArrayList<>();
            for(GrAccountReq grAccountReq : list){
                grAccountReq.checkAdd();
                if(CollectionUtils.isNotEmpty(personAds) && personAds.contains(grAccountReq.getPersonAd())){
                    continue;
                }
                grAccounts.add(dealSaveData(grAccountReq));
                personAds.add(grAccountReq.getPersonAd());
            }
            return grAccountMapper.insertList(grAccounts);
        }
        return 0;
    }

    @Override
    public GrAccount queryByPersonAd(String ad) throws ServiceException {
        if(StringUtils.isBlank(ad)){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        List<GrAccount> list = this.queryListByPersonAd(ad);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public List<GrAccount> queryListByPersonAd(String ad){
        GrAccountExample grAccountExample = new GrAccountExample();
        GrAccountExample.Criteria criteria = grAccountExample.createCriteria();
        if (StringUtils.isNotBlank(ad)) {
            criteria.andPersonAdEqualTo(ad);
        }
        criteria.andStatusEqualTo(Constants.YES);
        criteria.andDeleteEqualTo(Constants.NO);
        return grAccountMapper.selectByExample(grAccountExample);
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT", key = "#accountReq.accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true)
//    })
    public int update(GrAccountReq accountReq) throws ServiceException {
        GrAccount selGrAccount = this.checkAccNoIsExist(accountReq.getAccNo());
        GrAccount grAccount = new GrAccount();
        grAccount.setStatus(accountReq.getStatus());
        grAccount.setAccNo(selGrAccount.getAccNo());
        grAccount.setModifyTime(new Date());
        return grAccountMapper.updateByPrimaryKeySelective(grAccount);
    }

    private GrAccount checkAccNoIsExist(String accNo) throws ServiceException {
        GrAccount selGrAccount = this.queryByAccNo(accNo);
        if(selGrAccount == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        return selGrAccount;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT", key = "#accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "GRACCOUNT_PAGE", allEntries = true)
//    })
    public int delete(String accNo) throws ServiceException {
        this.checkAccNoIsExist(accNo);
        GrAccount grAccount = new GrAccount();
        grAccount.setModifyTime(new Date());
        grAccount.setAccNo(accNo);
        grAccount.setDelete(Constants.YES);
        return grAccountMapper.updateByPrimaryKeySelective(grAccount);
    }

  
}
