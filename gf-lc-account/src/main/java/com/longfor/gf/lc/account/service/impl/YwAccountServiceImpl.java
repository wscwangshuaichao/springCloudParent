package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.YwAccountMapper;
import com.longfor.gf.lc.account.dao.entity.YwAccount;
import com.longfor.gf.lc.account.dao.entity.example.YwAccountExample;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.YwAccountReq;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.service.YwAccountService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/16 17:10:39
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YwAccountServiceImpl implements YwAccountService {

    @Resource
    private YwAccountMapper ywAccountMapper;
    @Resource
    private SequenceService sequenceService;

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT_PAGE", allEntries = true)
    public int save(YwAccountReq accountReq) throws ServiceException {
        accountReq.checkAdd();
        return ywAccountMapper.insert(dealSaveData(accountReq));
    }

    /**
     * @description 封装要保存的数据
     * @author jiangdan
     * @date 2019/5/20 18:04
     * @param[accountReq]
     * @return
     */
    private YwAccount dealSaveData(YwAccountReq accountReq) throws ServiceException {
        YwAccountExample ywAccountExample = new YwAccountExample();
        ywAccountExample.createCriteria().andAccNameEqualTo(accountReq.getAccName()).andDeleteEqualTo(Constants.NO);
        List<YwAccount> list = ywAccountMapper.selectByExample(ywAccountExample);
        if(CollectionUtils.isNotEmpty(list)){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getAccName() + CodeEnum.BizCode.EXIST.getMsg());
        }
        YwAccount ywAccount = new YwAccount();
        BeanUtils.copyProperties(accountReq, ywAccount);
        // 处理序列号
        Date date = new Date();
        String accNo = sequenceService.createAccNo(TypeEnum.Account.BUSINESS.getKey());
        ywAccount.setAccNo(accNo);
        ywAccount.setCreateTime(date);
        ywAccount.setModifyTime(date);
        ywAccount.setDelete(Constants.NO);
        ywAccount.setStatus(accountReq.getStatus() != null ? accountReq.getStatus() : Constants.YES);
        return ywAccount;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "YWACCOUNT_PAGE", key = "#ywAccountReq.toMd5String(#page.pageNum, #page.pageSize)")
    public PageInfo<AccountDto> queryPage(YwAccountReq ywAccountReq, PageInfo page) {
        YwAccountExample ywAccountExample = dealParam(ywAccountReq);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<YwAccount> list = ywAccountMapper.selectByExample(ywAccountExample);
        page.setList(MyBeanUtils.copyProperties(list, AccountDto.class));
        page.setTotal(ywAccountMapper.selectCountByExample(ywAccountExample));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
//    @Cacheable(value = Constants.CACHE_PREFIX + "YWACCOUNT", key = "#accNo", unless = "#result eq null")
    public YwAccount queryByAccNo(String accNo) {
        YwAccountExample example = new YwAccountExample();
        example.createCriteria().andAccNoEqualTo(accNo).andDeleteEqualTo(Constants.NO);
        return ywAccountMapper.selectOneByExample(example);
    }

    private YwAccountExample dealParam(YwAccountReq ywAccountReq){
        YwAccountExample ywAccountExample = new YwAccountExample();
        YwAccountExample.Criteria criteria = ywAccountExample.createCriteria();
        if(ywAccountReq != null) {
            if (StringUtils.isNotBlank(ywAccountReq.getAccNo())) {
                criteria.andAccNoEqualTo(ywAccountReq.getAccNo());
            }
            if (StringUtils.isNotBlank(ywAccountReq.getAccName())) {
                criteria.andAccNameEqualTo(ywAccountReq.getAccName());
            }
            if (StringUtils.isNotBlank(ywAccountReq.getStartDate())) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDateTime(ywAccountReq.getStartDate()));
            }
            if (StringUtils.isNotBlank(ywAccountReq.getEndDate())) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.parseDateTime(ywAccountReq.getEndDate()));
            }
            if(ywAccountReq.getStatus() != null){
                criteria.andStatusEqualTo(ywAccountReq.getStatus());
            }
        }
        criteria.andDeleteEqualTo(Constants.NO);
        return ywAccountExample;
    }

    @Override
    public YwAccount queryByYwAccountReq(YwAccountReq ywAccountReq) throws ServiceException {
        if(ywAccountReq == null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        YwAccountExample ywAccountExample = dealParam(ywAccountReq);
        List<YwAccount> list = ywAccountMapper.selectByExample(ywAccountExample);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
//    @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT_PAGE", allEntries = true)
    public int batchSave(List<YwAccountReq> list) throws ServiceException {
        if(CollectionUtils.isNotEmpty(list)){
            List<YwAccount> ywAccounts = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for(YwAccountReq ywAccountReq : list){
                ywAccountReq.checkAdd();
                if(CollectionUtils.isNotEmpty(names) && names.contains(ywAccountReq.getAccName())){
                    continue;
                }
                ywAccounts.add(dealSaveData(ywAccountReq));
                names.add(ywAccountReq.getAccName());
            }
            return ywAccountMapper.insertList(ywAccounts);
        }
        return 0;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT", key = "#accountReq.accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT_PAGE", allEntries = true)
//    })
    public int update(YwAccountReq accountReq) throws ServiceException {
        YwAccount ywAccount = new YwAccount();
        BeanUtils.copyProperties(accountReq, ywAccount);
        YwAccount selYwAccount = this.checkAccNoIsExist(accountReq.getAccNo());
        YwAccount entity = MyBeanUtils.compareObj(selYwAccount, ywAccount);
        //查询业务名称是否已存在
        YwAccountExample ywAccountExample = new YwAccountExample();
        ywAccountExample.createCriteria().andAccNameEqualTo(accountReq.getAccName()).andDeleteEqualTo(Constants.NO);
        YwAccount ywAcc = ywAccountMapper.selectOneByExample(ywAccountExample);
        if(ywAcc != null && !selYwAccount.getAccNo().equals(ywAcc.getAccNo())){
            throw new ServiceException(CodeEnum.BizCode.EXIST.getCode(), accountReq.getAccName() + CodeEnum.BizCode.EXIST.getMsg());
        }
        if(entity != null){
            entity.setAccNo(selYwAccount.getAccNo());
            entity.setModifyTime(new Date());
            return ywAccountMapper.updateByPrimaryKeySelective(entity);
        }
        throw new ServiceException(CodeEnum.BizCode.NO_CHANGE_ERROR);
    }

    private YwAccount checkAccNoIsExist(String accNo) throws ServiceException {
        YwAccount selQtAccount = this.queryByAccNo(accNo);
        if(selQtAccount == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        return selQtAccount;
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT", key = "#accNo"),
//            @CacheEvict(value = Constants.CACHE_PREFIX + "YWACCOUNT_PAGE", allEntries = true)
//    })
    public int delete(String accNo) throws ServiceException {
        this.checkAccNoIsExist(accNo);
        YwAccount ywAccount = new YwAccount();
        ywAccount.setModifyTime(new Date());
        ywAccount.setAccNo(accNo);
        ywAccount.setDelete(Constants.YES);
        return ywAccountMapper.updateByPrimaryKeySelective(ywAccount);
    }

}
