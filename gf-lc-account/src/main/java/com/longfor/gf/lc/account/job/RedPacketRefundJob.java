//package com.longfor.gf.lc.account.job;
//
//import com.alibaba.fastjson.JSONObject;
//import com.longfor.gaia.gfs.core.response.BaseResponse;
//import com.longfor.gf.lc.account.business.BalanceAccTransferBusiness;
//import com.longfor.gf.lc.account.dao.TransactionMapper;
//import com.longfor.gf.lc.account.dao.entity.Transaction;
//import com.longfor.gf.lc.account.dao.entity.example.TransactionExample;
//import com.longfor.gf.lc.account.service.RedisLockService;
//import com.longfor.gf.lc.account.util.Constants;
//import com.longfor.gf.lc.account.util.DateUtil;
//import com.longfor.gf.lc.account.util.TypeEnum;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
//import com.xxl.job.core.log.XxlJobLogger;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author guoguangxiao
// * @date 2019/6/20 17:51:27
// */
//@Slf4j
//@JobHandler(value="redPacketRefundJob")
//@Component
//public class RedPacketRefundJob extends IJobHandler {
//
//    @Resource
//    private TransactionMapper transactionMapper;
//    @Resource
//    private BalanceAccTransferBusiness transferBusiness;
//    @Resource
//    private RedisLockService redisLockService;
//
//    private final int EXPIRE_HOURS = 24;
//
//    @Override
//    public ReturnT<String> execute(String s) throws Exception {
//
//        long curTime = System.currentTimeMillis();
//        log.info("[RequestParameter] - [RedPacketRefundJob] - method:[execute] - [task_start]");
//        XxlJobLogger.log("RedPacketRefundJob=====start=====");
//
//        //获取两天红包状态为非终态的
//        List<String> redPacketBusinessTypeList = Arrays.asList(Constants.GROUP_REDPACKET_TYPE, Constants.SINGLE_REDPACKET_TYPE);
//        TransactionExample example = new TransactionExample();
//        example.createCriteria()
//                .andStatusEqualTo(TypeEnum.TransStatus.PROCESSING.getCode())
//                .andBusinessTypeIn(redPacketBusinessTypeList)
//                .andCreateTimeGreaterThanOrEqualTo(DateUtil.changeDate(2,"-"));
//        List<Transaction> transactionList = transactionMapper.selectByExample(example);
//        if(CollectionUtils.isEmpty(transactionList)){
//            log.info("[ResponseParameter] - [RedPacketRefundJob] - method:[execute] - [task_end] - [无可退款红包交易] - duration:[{}]" ,System.currentTimeMillis()-curTime);
//            return SUCCESS;
//        }
//
//        //过滤时间已超过24小时的
//        Date curDate = new Date();
//        List<Transaction> transactionRefundList = transactionList.stream().filter(item->item.getCreateTime().before(DateUtil.changeDateByHour(curDate,EXPIRE_HOURS,"-"))).collect(Collectors.toList());
//        if(CollectionUtils.isEmpty(transactionRefundList)){
//            log.info("[ResponseParameter] - [RedPacketRefundJob] - method:[execute] - [task_end] - [无可退款红包] - duration:[{}]" ,System.currentTimeMillis()-curTime);
//            return SUCCESS;
//        }
//
//        //退款
//        for(Transaction transaction : transactionRefundList){
//            singleRefund(transaction);
//        }
//
//        XxlJobLogger.log("RedPacketRefundJob=====end====="+(System.currentTimeMillis()-curTime));
//        log.info("[ResponseParameter] - [RedPacketRefundJob] - method:[execute] - [task_end] - size:[{}] - duration:[{}]" ,transactionRefundList.size(), System.currentTimeMillis()-curTime);
//        return SUCCESS;
//    }
//
//    /**
//     * Method: 单笔红包退款
//     * Description:
//     * Author guoguangxiao
//     * Data 2019/6/21 11:01
//     * @param
//     * @return
//     */
//    private void singleRefund(Transaction transaction){
//        String lockKey = null;
//        try{
//            lockKey = transaction.getKeyId().toString() + "_" + transaction.getBatchNo();
//            if(redisLockService.lock(lockKey,2000,60*1000)){
//                log.info("[RequestParameter] - [RedPacketRefundJob] - method:[singleRefund] - request:[{}]", JSONObject.toJSONString(transaction));
//                BaseResponse refund = transferBusiness.refund(transaction.getBatchNo());
//                log.info("[ResponseParameter] - [RedPacketRefundJob] - method:[singleRefund] - response:[{}]", JSONObject.toJSONString(refund));
//            }
//        }catch (Exception e){
//            log.error("[Exception] - [RedPacketRefundJob] - method:[singleRefund] - errorMsg:[{}]", e.getMessage(), e);
//        }finally {
//            redisLockService.unlock(lockKey);
//        }
//
//    }
//
//
//}
