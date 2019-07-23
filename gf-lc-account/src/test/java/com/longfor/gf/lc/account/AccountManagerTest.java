//package com.longfor.gf.lc.account;
//
//import com.longfor.gf.lc.account.business.BaseBusiness;
//import com.longfor.gf.lc.account.dao.SequenceMapper;
//import com.longfor.gf.lc.account.dao.TransactionMapper;
//import com.longfor.gf.lc.account.exception.ServiceException;
//import com.longfor.gf.lc.account.factory.FrequencyFactory;
//import com.longfor.gf.lc.account.req.TradingSearchReq;
//import com.longfor.gf.lc.account.service.AccountService;
//import com.longfor.gf.lc.account.service.SequenceService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
///**
// * @author guoguangxiao
// * @date 2019/5/16 18:42:07
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class AccountManagerTest {
//
//    @Resource
//    private SequenceService sequenceService;
//    @Resource
//    private SequenceMapper sequenceMapper;
//    @Resource
//    private BaseBusiness baseBusiness;
//    @Resource
//    private AccountService accountService;
//
//    @Resource
//    private TransactionMapper transactionMapper;
//
//    @Test
//    public void testCreateAccNo() throws ServiceException {
//
////        String accNo = sequenceService.createAccNo("GR");
////        System.out.println("====="+Thread.currentThread().getName()+":"+accNo);
//
//        for(int i= 0;i<100;i++){
//
//            new Thread(()->{
//                try {
//                    String accNo = sequenceService.createAccNo("GR");
//                    System.out.println("======================="+Thread.currentThread().getName()+":"+accNo);
//                } catch (ServiceException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//        }
//
//    }
//
//    @Test
//    public void testTrxNo(){
//        for(int i= 0;i<200;i++){
//            new Thread(()->System.out.println("==================="+sequenceMapper.getBatchNo()+"========="+sequenceMapper.getTrxNo())).start();
//        }
//    }
//    @Test
//    public void testFrequency()  {
//        for(int i=0;i<10;i++){
//            try {
//                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE,"GR","accno",20,1));
//            } catch (ServiceException e) {
//                e.printStackTrace();
//            }
//            System.out.println("==================");
//        }
//    }
//
//    @Test
//    public void testUpdateBalance(){
//        try{
//            accountService.updateAccBalance("JL-20190521-000002","1",2);
//            System.out.println("=================");
//        }catch (Exception e){
//            System.out.println("=================11111111");
//            e.printStackTrace();
//        }
//
//    }
//    @Test
//    public void testTransNo(){
//        for(int i=0;i<10;i++){
//            System.out.println("==========="+sequenceService.getTrxNo());
//        }
//    }
//
//    @Test
//    public void testTranRedPacket(){
//        try{
//            String s = transactionMapper.queryAccInByBnoAndBtype("1212");
//            System.out.println("==========="+s);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            TradingSearchReq req = new TradingSearchReq();
//            req.setAccNo("121212");
//            System.out.println("==========="+transactionMapper.redPacketsByAccNo(req));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
