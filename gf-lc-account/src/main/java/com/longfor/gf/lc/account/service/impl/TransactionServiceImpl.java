package com.longfor.gf.lc.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.TransactionMapper;
import com.longfor.gf.lc.account.dao.entity.Transaction;
import com.longfor.gf.lc.account.dao.entity.example.TransactionExample;
import com.longfor.gf.lc.account.dao.entity.example.TransactionQuery;
import com.longfor.gf.lc.account.dto.AccTradingSummaryDto;
import com.longfor.gf.lc.account.dto.AccTransactionDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import com.longfor.gf.lc.account.req.TransactionReq;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.service.TransactionService;
import com.longfor.gf.lc.account.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TransactionServiceImpl
 * @Author jiangdan
 * @Date 2019/5/22 16:18
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private SequenceService sequenceService;

    @Override
    public PageInfo<TransactionDto> queryPage(TradingSearchReq req, PageInfo page) {
        TransactionQuery query = getTransactionQuery(req);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<TransactionDto> list = transactionMapper.queryPage(query);
        page.setList(list);
        page.setTotal(transactionMapper.queryPageCount(query));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
    public TransactionDto queryByTransactionReq(TradingSearchReq req) throws ServiceException {
        TransactionExample transactionExample = dealParam(req);
        if(CollectionUtils.isEmpty(transactionExample.getOredCriteria())){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY);
        }
        List<Transaction> list = transactionMapper.selectByExample(transactionExample);
        if (CollectionUtils.isNotEmpty(list)){
            return MyBeanUtils.copyProperties(list.get(0), TransactionDto.class);
        }
        throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
    }

    private TransactionExample dealParam(TradingSearchReq req){
        TransactionExample transactionExample = new TransactionExample();
        TransactionExample.Criteria criteria = transactionExample.createCriteria();
        if (StringUtils.isNotBlank(req.getAccOut())) {
            criteria.andAccOutEqualTo(req.getAccOut());
        }
        if (StringUtils.isNotBlank(req.getAccIn())) {
            criteria.andAccInEqualTo(req.getAccIn());
        }
        if (StringUtils.isNotBlank(req.getBusinessType())) {
            criteria.andBusinessTypeEqualTo(req.getBusinessType());
        }
        if (StringUtils.isNotBlank(req.getBatchNo())) {
            criteria.andBatchNoEqualTo(req.getBatchNo());
        }
        if (StringUtils.isNotBlank(req.getTransNo())) {
            criteria.andTransNoEqualTo(req.getTransNo());
        }
        if (StringUtils.isNotBlank(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        if (StringUtils.isNotBlank(req.getTransType())) {
            criteria.andTransTypeEqualTo(req.getTransType());
        }
        if (req.getKeyId() != null) {
            criteria.andKeyIdEqualTo(req.getKeyId());
        }
        return transactionExample;
    }

    @Override
    public AccTradingSummaryDto queryTradingMoney(TradingSearchReq req) {
        TransactionQuery query = getTransactionQuery(req);
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expend = BigDecimal.ZERO;
        if(req.getTransWay() == null || (req.getTransWay().intValue() == 0)){
            income = transactionMapper.selectIncomeByAccNo(query);
            expend = transactionMapper.selectExpendByAccNo(query);
        }else if(req.getTransWay().intValue() == 1){
            income = transactionMapper.selectIncomeByAccNo(query);
        }else if(req.getTransWay().intValue() == 2){
            expend = transactionMapper.selectExpendByAccNo(query);
        }
        return dealAccTradingSummaryDto(income, expend, req.getAccNo());
    }

    private AccTradingSummaryDto dealAccTradingSummaryDto( BigDecimal income, BigDecimal expend, String accNo){
        AccTradingSummaryDto accTradingSummaryDto = new AccTradingSummaryDto();
        accTradingSummaryDto.setAccInAmt(income != null ? income : BigDecimal.ZERO);
        accTradingSummaryDto.setAccOutAmt(expend != null ? expend : BigDecimal.ZERO);
        accTradingSummaryDto.setAccNo(accNo);
        return accTradingSummaryDto;
    }


    @Override
    public int updateStatus(Integer keyId, String status) throws ServiceException{
        Transaction entity = transactionMapper.selectByPrimaryKey(keyId);
        if(entity == null){
            throw new ServiceException(CodeEnum.BizCode.NOT_EXIST);
        }
        Transaction transaction = new Transaction();
        transaction.setKeyId(keyId);
        transaction.setStatus(status);
        transaction.setModifyTime(new Date());
        return transactionMapper.updateByPrimaryKeySelective(transaction);
    }

    @Override
    public List<TransactionDto> queryTransactionByExample(TransactionExample transactionExample) throws ServiceException {
        List<Transaction> list = transactionMapper.selectByExample(transactionExample);
        return MyBeanUtils.copyProperties(list, TransactionDto.class);
    }

    @Override
    public List<TransactionDto> redPacketsByAccNo(TradingSearchReq req) {
        TransactionQuery query = getTransactionQuery(req);
        return transactionMapper.redPacketsByAccNo(query);
    }

    @Override
    public int saveList(List<TransactionReq> reqList) {
        if(CollectionUtils.isEmpty(reqList)){
            return 0;
        }

        List<Transaction> recordList = new ArrayList<>();
        Date date = new Date();
        for(TransactionReq reqItem : reqList){
            Transaction transaction = new Transaction();
            transaction.setAccIn(reqItem.getAccIn());
            transaction.setAccOut(reqItem.getAccOut());
            BigDecimal amount = new BigDecimal(reqItem.getAmount());
            transaction.setAccInAmt(amount);
            transaction.setAccOutAmt(amount);
            transaction.setBatchNo(reqItem.getBatchNo());
            transaction.setTransNo(sequenceService.getTrxNo());
            transaction.setOutTransNo(transaction.getTransNo());
            transaction.setBusinessType(reqItem.getBusinessType());
            transaction.setStatus(TypeEnum.TransStatus.FINISHED.getCode());
            transaction.setRemarks(reqItem.getRemarks());
            transaction.setTransType(TypeEnum.TransType.TRANSFER.getCode());
            transaction.setCreateTime(date);
            transaction.setMm(Integer.parseInt(DateUtil.dateToString(date, DateUtil.MM)));
            transaction.setYyyy(Integer.parseInt(DateUtil.dateToString(date, DateUtil.YYYY)));
            transaction.setDd(Integer.parseInt(DateUtil.dateToString(date, DateUtil.DD)));
            transaction.setModifyTime(date);
            recordList.add(transaction);
        }

        return transactionMapper.insertList(recordList);
    }

    @Override
    public Transaction getTransactionByTransNo(String transNo, String outTransNo) {
        TransactionExample example = new TransactionExample();
        TransactionExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(transNo)){
            criteria.andTransNoEqualTo(transNo);
        }
        if(StringUtils.isNotEmpty(outTransNo)){
            criteria.andOutTransNoEqualTo(outTransNo);
        }
        return transactionMapper.selectOneByExample(example);
    }

    @Override
    public PageInfo<AccTransactionDto> queryH5TransPage(TradingSearchReq req, PageInfo page) {
        TransactionQuery query = getTransactionQuery(req);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<AccTransactionDto> list = transactionMapper.queryH5TransPage(query);
        if(CollectionUtils.isNotEmpty(list)){
            AccTransactionDto entity = null;
            for (AccTransactionDto dto : list){
                entity = transactionMapper.queryTransByBnoAndBtypeAndAccNo(dto.getBatchNo(), req.getAccNo(), req.getTransWay(), dto.getBusinessType());
                if(entity != null) {
                    if (TypeEnum.TransType.FREEZE.getCode().equals(entity.getTransType())) {
                        dto.setAccIn(transactionMapper.queryAccInByBnoAndBtype(dto.getBatchNo()));
                    }
                    dto.setAccOut(entity.getAccOut());
                    dto.setCreateTime(entity.getCreateTime());
                    dto.setTransWay(entity.getTransWay());
                    dto.setRemarks(entity.getRemarks());
                    dto.setStatus(entity.getStatus());
                    dto.setTransType(entity.getTransType());
                }
            }
        }
        page.setList(list);
        page.setTotal(transactionMapper.queryH5TransPageCount(query));
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        return page;
    }

    @Override
    public List<AccTransactionDto> selectH5TransPage(TradingSearchReq req) {
        TransactionQuery query = getTransactionQuery(req);
        List<TransactionDto> list = transactionMapper.selectH5TransPage(query);
        List<AccTransactionDto> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            for (TransactionDto dto : list){
                AccTransactionDto accTransactionDto = MyBeanUtils.copyProperties(dto, AccTransactionDto.class);
                accTransactionDto.setAmount(dto.getAccOutAmt());
                result.add(accTransactionDto);
            }
        }
        return result;
    }

    @Override
    public AccTradingSummaryDto selectTradingMoney(TradingSearchReq req) {
        TransactionQuery query = getTransactionQuery(req);
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expend = BigDecimal.ZERO;
        if(req.getTransWay() == null || (req.getTransWay().intValue() == 0)){
            income = transactionMapper.queryUserIncome(query);
            expend = transactionMapper.queryUserExpend(query);
        }else if(req.getTransWay().intValue() == 1){
            income = transactionMapper.queryUserIncome(query);
        }else if(req.getTransWay().intValue() == 2){
            expend = transactionMapper.queryUserExpend(query);
        }
        return dealAccTradingSummaryDto(income, expend, req.getAccNo());
    }

    private TransactionQuery getTransactionQuery(TradingSearchReq req) {
        return MyBeanUtils.copyProperties(req, TransactionQuery.class);
    }

}
