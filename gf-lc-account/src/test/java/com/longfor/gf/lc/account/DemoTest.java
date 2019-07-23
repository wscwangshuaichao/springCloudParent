package com.longfor.gf.lc.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gf.lc.account.dao.entity.QtAccount;
import com.longfor.gf.lc.account.req.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/24 13:43:48
 */
public class DemoTest {

    @Test
    public void testBatchReq(){

        //场景一：业务账户 转 多人
        //场景二：个人或余额账户 转 多人
        TransferBatchReq batchReq = new TransferBatchReq();
        batchReq.setAccOut("JL-20190521-000008");
        batchReq.setBatchNo("1122334455667788");
        batchReq.setBusinessType("101010");
        List<TransferDetailReq> transferDetailList = new ArrayList<>();
        TransferDetailReq detailReq1 = new TransferDetailReq();
        detailReq1.setAccNo("GR-20190521-000018");
        detailReq1.setAmount("1");
        detailReq1.setRemark("你是白痴");
        TransferDetailReq detailReq2 = new TransferDetailReq();
        detailReq2.setAccNo("GR-20190522-000034");
        detailReq2.setAmount("1");
        detailReq2.setRemark("我是白痴");
        transferDetailList.add(detailReq1);
        transferDetailList.add(detailReq2);
        batchReq.setData(JSON.toJSONString(transferDetailList));
        System.out.println(JSON.toJSONString(batchReq));

    }

    @Test
    public void testTccTransfer(){
        //类似于事务 个人转个人 预转账-确认-取消
        TransferReq transferReq = new TransferReq();
        //预转账
        transferReq.setBusinessType("101005");
        TransactionReq transactionReq = new TransactionReq();
        transactionReq.setBusinessType("101005");
        transactionReq.setAccOut("GR-20190522-000034");
        transactionReq.setAmount("100");
        transactionReq.setJobNo("10001");
        transactionReq.setOutTransNo("JFDH"+System.currentTimeMillis());
        transferReq.setData(JSON.toJSONString(transactionReq));
        System.out.println("预转账:"+ JSON.toJSONString(transferReq));
        //确认
        TransferConfirmReq transferConfirmReq = new TransferConfirmReq();
        transferConfirmReq.setOutTransNo("JFDH"+System.currentTimeMillis());
        transferConfirmReq.setTransNo("TN"+System.currentTimeMillis());
        transferReq.setData(JSON.toJSONString(transferConfirmReq));
        System.out.println("确认:"+ JSON.toJSONString(transferReq));
        //取消
        TransferCancelReq transferCancelReq = new TransferCancelReq();
        transferCancelReq.setOutTransNo("JFDH"+System.currentTimeMillis());
        transferCancelReq.setTransNo("TN"+System.currentTimeMillis());
        transferReq.setData(JSON.toJSONString(transferCancelReq));
        System.out.println("取消:"+ JSON.toJSONString(transferReq));

    }

    @Test
    public void testAddAccount(){
        AccountReq accountReq = new AccountReq();

        accountReq.setAccountType("JL");
        JlAccountReq jlAccountReq = new JlAccountReq();
        jlAccountReq.setAccNo("JL-20190516-00001");
        jlAccountReq.setPersonAd("jiangd");
        jlAccountReq.setBalanceAmt(BigDecimal.ZERO);
        jlAccountReq.setFrozenAmt(BigDecimal.ZERO);
        jlAccountReq.setStatus(1);
        jlAccountReq.setCreateUser("1");
        jlAccountReq.setModifyUser("1");
        jlAccountReq.setStartDate("2019-05-22");
        jlAccountReq.setEndDate("2019-05-31");
        accountReq.setData(JSONObject.toJSONString(jlAccountReq));
        System.out.println("激励："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("GR");
        GrAccountReq grAccountReq = new GrAccountReq();
        grAccountReq.setAccNo("GR-20190516-00001");
        grAccountReq.setPersonAd("jiangd");
        grAccountReq.setBalanceAmt(BigDecimal.ZERO);
        grAccountReq.setFrozenAmt(BigDecimal.ZERO);
        grAccountReq.setStatus(1);
        grAccountReq.setCreateUser("1");
        grAccountReq.setModifyUser("1");
        grAccountReq.setStartDate("2019-05-22");
        grAccountReq.setEndDate("2019-05-31");
        accountReq.setData(JSONObject.toJSONString(grAccountReq));
        System.out.println("个人："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("YW");
        YwAccountReq ywAccountReq = new YwAccountReq();
        ywAccountReq.setAccNo("YW-20190516-00001");
        ywAccountReq.setAccName("happy children");
        ywAccountReq.setStatus(1);
        ywAccountReq.setCreateUser("1");
        ywAccountReq.setModifyUser("1");
        ywAccountReq.setStartDate("2019-05-22");
        ywAccountReq.setEndDate("2019-05-31");
        accountReq.setData(JSONObject.toJSONString(ywAccountReq));
        System.out.println("业务："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("CP");
        CpAccountReq cpAccountReq = new CpAccountReq();
        cpAccountReq.setAccNo("CP-20190516-00001");
        cpAccountReq.setCpCode("jiangd");
        cpAccountReq.setCpName("产品名称");
        cpAccountReq.setBalanceAmt(BigDecimal.ZERO);
        cpAccountReq.setFrozenAmt(BigDecimal.ZERO);
        cpAccountReq.setStatus(1);
        cpAccountReq.setCreateUser("1");
        cpAccountReq.setModifyUser("1");
        cpAccountReq.setStartDate("2019-05-22");
        cpAccountReq.setEndDate("2019-05-31");
        accountReq.setData(JSONObject.toJSONString(cpAccountReq));
        System.out.println("产品："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("QT");
        QtAccountReq qtAccountReq = new QtAccountReq();
        qtAccountReq.setAccNo("QT-20190516-00001");
        qtAccountReq.setQtCode("jiangd");
        qtAccountReq.setQtName("名称");
        qtAccountReq.setBalanceAmt(BigDecimal.ZERO);
        qtAccountReq.setFrozenAmt(BigDecimal.ZERO);
        qtAccountReq.setStatus(1);
        qtAccountReq.setCreateUser("1");
        qtAccountReq.setModifyUser("1");
        qtAccountReq.setStartDate("2019-05-22");
        qtAccountReq.setEndDate("2019-05-31");
        accountReq.setData(JSONObject.toJSONString(qtAccountReq));
        System.out.println("其他："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("GR");
        List<GrAccountReq> list = new ArrayList<>();
        GrAccountReq grAccountReq0 = new GrAccountReq();
        grAccountReq0.setPersonAd("jiangd11");
        GrAccountReq grAccountReq1 = new GrAccountReq();
        grAccountReq1.setPersonAd("jiangd12");
        GrAccountReq grAccountReq2 = new GrAccountReq();
        grAccountReq2.setPersonAd("jiangd13");
        list.add(grAccountReq);
        list.add(grAccountReq1);
        list.add(grAccountReq2);
        accountReq.setData(JSONObject.toJSONString(list));
        System.out.println("批量个人："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("JL");
        List<JlAccountReq> jlList = new ArrayList<>();
        jlList.add(jlAccountReq);
        accountReq.setData(JSONObject.toJSONString(jlList));
        System.out.println("批量激励："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("CP");
        List<CpAccountReq> cpList = new ArrayList<>();
        cpList.add(cpAccountReq);
        accountReq.setData(JSONObject.toJSONString(cpList));
        System.out.println("批量产品："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("QT");
        List<QtAccountReq> qtList = new ArrayList<>();
        qtList.add(qtAccountReq);
        accountReq.setData(JSONObject.toJSONString(qtList));
        System.out.println("批量其他："+JSONObject.toJSONString(accountReq));

        accountReq.setAccountType("YW");
        List<YwAccountReq> ywList = new ArrayList<>();
        ywList.add(ywAccountReq);
        accountReq.setData(JSONObject.toJSONString(ywList));
        System.out.println("批量业务："+JSONObject.toJSONString(accountReq));
    }

    @Test
    public void testTradingDetail(){
        TradingSearchReq tradingSearchReq = new TradingSearchReq();
        tradingSearchReq.setKeyId(1);
        tradingSearchReq.setBatchNo("BN20190531162706432947");
        tradingSearchReq.setTransNo("TN2019053116270641910171");
        tradingSearchReq.setOutTransNo("1559291226296100127426");
        tradingSearchReq.setAccNo("GR-20190521-000033");
        tradingSearchReq.setAccOut("GR-20190521-000033");
        tradingSearchReq.setAccIn("GAT-GR-20190521-000033");
        tradingSearchReq.setBusinessType("101005");
        tradingSearchReq.setTransType("1");
        tradingSearchReq.setTransWay(0);
        tradingSearchReq.setStartDate("2019-05-22");
        tradingSearchReq.setEndDate("2019-05-31");
        tradingSearchReq.setYyyy(2019);
        tradingSearchReq.setMm(5);
        tradingSearchReq.setDd(22);
        tradingSearchReq.setPageNum(1);
        tradingSearchReq.setPageSize(10);
        List<String> accOuts = new ArrayList<>();
        accOuts.add("GR-20190521-000033");
        accOuts.add("GR-20190521-000032");
        accOuts.add("GR-20190521-000034");
        tradingSearchReq.setAccOuts(accOuts);
        System.out.println(JSONObject.toJSONString(tradingSearchReq));
    }
    @Test
    public void testTransfer(){
        TransferReq result = new TransferReq();
        result.setBusinessType("101016");
        TransactionReq req = new TransactionReq();
        req.setBatchNo("BN20190531162706432947");
        req.setOutTransNo("1559291226296100127426");
        req.setAccOut("GR-20190521-000033");
        req.setAccIn("GAT-GR-20190521-000033");
        req.setBusinessType("101005");
        req.setAmount("10000");
        req.setRemarks("六一儿童节快乐");
        req.setJobNo("1000001");
        result.setData(JSON.toJSONString(req));
        System.out.println(JSONObject.toJSONString(result));
    }


}
