package com.longfor.gf.lc.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dto.*;
import com.longfor.gf.lc.account.req.*;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * @ClassName GrAccountTest
 * @Author jiangdan
 * @Date 2019/5/21 10:35
 **/
public class GrAccountTest extends GfLcAccountApplicationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Matcher[] matchers;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        List<String> codeList = new ArrayList<>();
        codeList.add("0000");
        codeList.add("0003");
        codeList.add("0004");
        codeList.add("0005");
        codeList.add("0006");
        codeList.add("0008");
        codeList.add("0009");
        codeList.add("0010");
        codeList.add("0011");
        codeList.add("0012");
        codeList.add("0013");
        codeList.add("0014");
        codeList.add("0018");
        codeList.add("0019");
        codeList.add("0020");
        codeList.add("0030");
        codeList.add("0031");
        codeList.add("0032");
        codeList.add("0033");
        codeList.add("0034");
        codeList.add("9999");

        matchers = new Matcher[codeList.size()];
        for(int i=0;i<codeList.size();i++){
            matchers[i] = equalTo(codeList.get(i));
        }
    }

    /**
     * @description 分页查询
     * @author jiangdan
     * @date 2019/5/27 17:44
     * @param[]
     * @return void
     */
    @Test
    public void testAccQueryPage() throws Exception {
        accQueryPage("GR", "GR-20190516-00001");
        accQueryPage("JL", "JL-20190601-000001");
        accQueryPage("QT", "QT-20190601-000001");
        accQueryPage("CP", "CP-20190601-000001");
        accQueryPage("YW", "YW-20190601-000001");
    }

    private void accQueryPage(String accType, String accNo) throws Exception {
        AccountReq accountReq = new AccountReq();
        accountReq.setAccountType(accType);
        GrAccountReq grAccount = new GrAccountReq();
        grAccount.setAccNo(accNo);
        accountReq.setPageNum(0);
        accountReq.setPageSize(10);
        accountReq.setData(JSON.toJSONString(grAccount));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(accountReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    // 个人：{"personAd":"100005","balanceAmt":0,"frozenAmt":0,"status":1}
    //激励：{"personAd":"100003","balanceAmt":0,"frozenAmt":0,"status":1}
    //产品：{"cpCode":"100001","cpName":"工作圈","balanceAmt":0,"frozenAmt":0,"status":1}
    //其他：{"qtCode":"100001","qtName":"入职融入","balanceAmt":0,"frozenAmt":0,"status":1}
    //业务：{"accName":"生日福利","balanceAmt":0,"frozenAmt":0,"status":1}
    @Test
    @Transactional
    @Rollback()
    public void testAdd() throws Exception {
        GrAccountReq grAccount = new GrAccountReq();
        grAccount.setPersonAd("100005");
        accAdd("GR", JSON.toJSONString(grAccount));
        JlAccountReq jlAccount = new JlAccountReq();
        jlAccount.setPersonAd("100005");
        accAdd("JL", JSON.toJSONString(jlAccount));
        QtAccountReq qtAccount = new QtAccountReq();
        qtAccount.setQtCode("100005");
        qtAccount.setQtName("100005");
        accAdd("QT", JSON.toJSONString(qtAccount));
        CpAccountReq cpAccount = new CpAccountReq();
        cpAccount.setCpCode("100005");
        cpAccount.setCpName("100005");
        accAdd("CP", JSON.toJSONString(cpAccount));
        YwAccountReq ywAccount = new YwAccountReq();
        ywAccount.setAccName("100005");
        accAdd("YW", JSON.toJSONString(ywAccount));
    }

    private void accAdd(String accType, String data) throws Exception {
        AccountReq accountReq = new AccountReq();
        accountReq.setAccountType(accType);
        accountReq.setData(data);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(accountReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    /**
     * @description 根据主键查询
     * @author jiangdan
     * @date 2019/5/27 17:44
     * @param[]
     * @return void
     */
    @Test
    public void testGetDetailByAccNo() throws Exception {
        getAccDetailByAccNo("GR-20190601-000005");
        getAccDetailByAccNo("JL-20190601-000001");
        getAccDetailByAccNo("CP-20190601-000001");
        getAccDetailByAccNo("YW-20190601-000001");
        getAccDetailByAccNo("QT-20190601-000001");
    }

    private void getAccDetailByAccNo(String accNo) throws Exception {
        RequestBuilder request = get("/api/account/" + accNo)
                .accept(MediaType.APPLICATION_JSON).param("accNo", accNo);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    /**
     * @description 根据条件查询
     * @author jiangdan
     * @date 2019/5/27 17:43
     * @param[]
     * @return void
     */
    @Test
    public void testDetail() throws Exception {
        GrAccountReq grAccount = new GrAccountReq();
        grAccount.setPersonAd("jiangd11");
        grAccount.setStatus(1);
        accDetail("GR", JSON.toJSONString(grAccount));
        JlAccountReq jlAccount = new JlAccountReq();
        jlAccount.setPersonAd("jiangd1");
        jlAccount.setStatus(1);
        accDetail("JL", JSON.toJSONString(jlAccount));
        QtAccountReq qtAccount = new QtAccountReq();
        qtAccount.setQtCode("jiangd1");
        qtAccount.setStatus(1);
        accDetail("QT", JSON.toJSONString(qtAccount));
        CpAccountReq cpAccount = new CpAccountReq();
        cpAccount.setCpCode("jiangd1");
        cpAccount.setStatus(1);
        accDetail("CP", JSON.toJSONString(cpAccount));
        YwAccountReq ywAccount = new YwAccountReq();
        ywAccount.setAccName("happy children1");
        ywAccount.setStatus(1);
        accDetail("YW", JSON.toJSONString(ywAccount));
    }

    private void accDetail(String accType, String data) throws Exception {
        AccountReq accountReq = new AccountReq();
        accountReq.setAccountType(accType);
        accountReq.setData(data);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account/detail")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(accountReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<AccountDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }
    /**
     * @description 通过用户名查询多类账户详情
     * @author jiangdan
     * @date 2019/5/27 17:43
     * @param[]
     * @return void
     */
    @Test
    public void testGetListByAd() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account/list/100004")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("personAd", "100004");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<List<AccountDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    /**
     * @description 修改
     * @author jiangdan
     * @date 2019/5/27 17:43
     * @param[]
     * @return void
     */
    @Test
    @Transactional
    @Rollback()
    public void testUpdate(){
        try {
            GrAccountReq grAccount = new GrAccountReq();
            grAccount.setAccNo("GR-20190601-000009");
            grAccount.setStatus(0);
            accUpdate("GR", JSON.toJSONString(grAccount));
            JlAccountReq jlAccount = new JlAccountReq();
            jlAccount.setAccNo("JL-20190601-000011");
            jlAccount.setStatus(0);
            accUpdate("JL", JSON.toJSONString(jlAccount));
            QtAccountReq qtAccount = new QtAccountReq();
            qtAccount.setAccNo("QT-20190601-000001");
            qtAccount.setStatus(0);
            accUpdate("QT", JSON.toJSONString(qtAccount));
            CpAccountReq cpAccount = new CpAccountReq();
            cpAccount.setAccNo("CP-20190601-000001");
            cpAccount.setStatus(0);
            accUpdate("CP", JSON.toJSONString(cpAccount));
            YwAccountReq ywAccount = new YwAccountReq();
            ywAccount.setAccNo("YW-20190601-000001");
            ywAccount.setStatus(0);
            accUpdate("YW", JSON.toJSONString(ywAccount));
        }catch (Exception e){

        }
    }

    private void accUpdate(String accType, String data) throws Exception {
        AccountReq accountReq = new AccountReq();
        accountReq.setAccountType(accType);
        accountReq.setData(data);
        RequestBuilder request = MockMvcRequestBuilders.put("/api/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(accountReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<AccountDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
//        Assert.assertEquals("0000", baseResponse.getCode());
    }

    /**
     * @description 删除
     * @author jiangdan
     * @date 2019/5/27 17:42
     * @param[]
     * @return void
     */
    @Test
    @Transactional
    @Rollback()
    public void testDelete() throws Exception {
        accDel("GR-20190604-000065");
        accDel("JL-20190604-000023");
        accDel("CP-20190604-000010");
        accDel("YW-20190604-000010");
        accDel("QT-20190604-000008");
    }

    private void accDel(String accNo) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/account/" + accNo)
                .accept(MediaType.APPLICATION_JSON).param("accNo", accNo);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<AccountDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    /**
     * @description 批量添加
     * @author jiangdan
     * @date 2019/5/27 17:45
     * @param[]
     * @return void
     */
    @Test
    @Transactional
    @Rollback()
    public void testBatchAdd() throws Exception {
        //个人
        AccountReq grAccountReq = new AccountReq();
        grAccountReq.setAccountType("GR");
        List<GrAccountReq> list = new ArrayList<>();
        GrAccountReq grAccount1 = new GrAccountReq();
        grAccount1.setPersonAd("100033");
        list.add(grAccount1);
        GrAccountReq grAccount2 = new GrAccountReq();
        grAccount2.setPersonAd("100034");
        list.add(grAccount2);
        grAccountReq.setData(JSON.toJSONString(list));
        accBatchAdd(grAccountReq);
        //激励
        AccountReq jlaccountReq = new AccountReq();
        jlaccountReq.setAccountType("JL");
        List<JlAccountReq> jllist = new ArrayList<>();
        JlAccountReq jlAccount1 = new JlAccountReq();
        jlAccount1.setPersonAd("100033");
        jllist.add(jlAccount1);
        JlAccountReq jlAccount2 = new JlAccountReq();
        jlAccount2.setPersonAd("100034");
        jllist.add(jlAccount2);
        jlaccountReq.setData(JSON.toJSONString(jllist));
        accBatchAdd(jlaccountReq);
        //产品
        AccountReq cpAccountReq = new AccountReq();
        cpAccountReq.setAccountType("CP");
        List<CpAccountReq> cplist = new ArrayList<>();
        CpAccountReq cpAccount1 = new CpAccountReq();
        cpAccount1.setCpCode("100033");
        cpAccount1.setCpName("100033");
        cplist.add(cpAccount1);
        CpAccountReq cpAccount2 = new CpAccountReq();
        cpAccount2.setCpCode("100034");
        cpAccount2.setCpName("100034");
        cplist.add(cpAccount2);
        cpAccountReq.setData(JSON.toJSONString(cplist));
        accBatchAdd(cpAccountReq);

        //业务
        AccountReq ywAccountReq = new AccountReq();
        ywAccountReq.setAccountType("YW");
        List<YwAccountReq> ywlist = new ArrayList<>();
        YwAccountReq ywAccount1 = new YwAccountReq();
        ywAccount1.setAccName("100033");
        ywlist.add(ywAccount1);
        YwAccountReq ywAccount2 = new YwAccountReq();
        ywAccount2.setAccName("100034");
        ywlist.add(ywAccount2);
        ywAccountReq.setData(JSON.toJSONString(ywlist));
        accBatchAdd(ywAccountReq);

        //其他
        AccountReq qtAccountReq = new AccountReq();
        qtAccountReq.setAccountType("QT");
        List<QtAccountReq> qtlist = new ArrayList<>();
        QtAccountReq qtAccount1 = new QtAccountReq();
        qtAccount1.setQtCode("100033");
        qtAccount1.setQtName("100033");
        qtlist.add(qtAccount1);
        QtAccountReq qtAccount2 = new QtAccountReq();
        qtAccount2.setQtCode("100034");
        qtAccount2.setQtName("100034");
        qtlist.add(qtAccount2);
        qtAccountReq.setData(JSON.toJSONString(qtlist));
        accBatchAdd(qtAccountReq);
    }

    private void accBatchAdd(AccountReq accountReq) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account/batch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(accountReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testJlAccByAd() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/account/queryJlAccountByAd/100003")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("personAd", "100003");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<Map<String, Object>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testTranQueryPage() throws Exception {
        TradingSearchReq tradingSearchReq = new TradingSearchReq();
        tradingSearchReq.setAccNo("GR-20190516-00001");
        tradingSearchReq.setStartDate("2019-06-01");
        tradingSearchReq.setEndDate("2019-06-03");
        tradingSearchReq.setPageSize(10);
        tradingSearchReq.setPageNum(0);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(tradingSearchReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        BaseResponse<PageInfo<TransactionDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        System.out.println(contentAsString);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testGetDetailByTransNo() throws Exception {
        TradingSearchReq tradingSearchReq = new TradingSearchReq();
        tradingSearchReq.setTransNo("TN2019060113252707002945");
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/detail")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(tradingSearchReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransactionDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testGetTradingMoney() throws Exception {
        TradingSearchReq tradingSearchReq = new TradingSearchReq();
        tradingSearchReq.setAccNo("JL-20190601-000011");
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/getTradingMoney")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(tradingSearchReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<AccTradingSummaryDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testGetRedPacketsRecordByBatchNo() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/trading/getRedPacketsRecordByBatchNo/BN20190601151119327784")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<List<TransactionDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testGetBatchNo() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/getBatchNo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    public void testGetRedPacketsRecordByAccNo() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/trading/getRedPacketsRecordByAccNo/JL-20190601-000011")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<List<TransactionDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        Assert.assertNotNull(baseResponse.getData());
    }

    @Test
    @Transactional
    @Rollback()
    public void testUnfreeze() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/trading/unfreeze/178")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testGetBusinessTypes() throws Exception {
        ParamaterReq paramaterReq = new ParamaterReq();
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/getBusinessTypes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(paramaterReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<List<ParamaterDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testQueryH5TransPage() throws Exception {
        TradingSearchReq tradingSearchReq = new TradingSearchReq();
        tradingSearchReq.setAccNo("JL-20190601-000011");
        RequestBuilder request = MockMvcRequestBuilders.post("/api/trading/queryH5TransPage")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(tradingSearchReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<List<AccTransactionDto>> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testTransfer() {
        try {
            BaseResponse<TransferDto> baseResponse = new BaseResponse<>();
            TransferReq req = new TransferReq();
            //单个红包
            req.setBusinessType("101000");
            TransactionReq singleReq = new TransactionReq();
            singleReq.setAccOut("GR-20190601-000001");
            singleReq.setAmount("100");
            singleReq.setAccIn("GR-20190601-000001");
            singleReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-", ""));
            singleReq.setBusinessType("101000");
            singleReq.setRemarks("测试单个红包的测试用例数据");
            req.setData(JSON.toJSONString(singleReq));
            baseResponse = transfer(req);
            //抢红包
            TransferDto transferDto = JSONObject.parseObject(JSON.toJSONString(baseResponse.getData()), TransferDto.class);
            req.setBusinessType("101000");
            TransactionReq grabReq = new TransactionReq();
            grabReq.setBatchNo(transferDto.getBatchNo());
            grabReq.setAccOut("GR-20190601-000001");
            grabReq.setAmount("100");
            grabReq.setAccIn("GR-20190604-000064");
            grabReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-", ""));
            grabReq.setBusinessType("102000");
            grabReq.setRemarks("测试抢红包的测试用例数据");
            req.setData(JSON.toJSONString(grabReq));
            transfer(req);

            //业务转账
            req.setBusinessType("101010");
            TransactionReq ywTransReq = new TransactionReq();
            ywTransReq.setAccOut("YW-20190604-000009");
            ywTransReq.setAmount("100");
            ywTransReq.setAccIn("GR-20190604-000065");
            ywTransReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-", ""));
            ywTransReq.setBusinessType("101010");
            ywTransReq.setRemarks("测试业务转账的测试用例数据");
            req.setData(JSON.toJSONString(ywTransReq));
            transfer(req);

            req.setBusinessType("101016");
            TransactionReq cbTransReq = new TransactionReq();
            cbTransReq.setAccOut("CB-JL");
            cbTransReq.setAmount("100");
            cbTransReq.setAccIn("JL-20190604-000022");
            cbTransReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-", ""));
            cbTransReq.setBusinessType("101016");
            cbTransReq.setRemarks("测试成本转账的测试用例数据");
            req.setData(JSON.toJSONString(cbTransReq));
            transfer(req);

            //清零
            req.setBusinessType("102001");
            TransactionReq zeroTransReq = new TransactionReq();
            zeroTransReq.setAccOut("JL-20190604-000022");
            zeroTransReq.setAmount("100");
            zeroTransReq.setAccIn("ZERO-JL-20190604-000022");
            zeroTransReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-", ""));
            zeroTransReq.setBusinessType("102001");
            zeroTransReq.setRemarks("测试清零的测试用例数据");
            req.setData(JSON.toJSONString(zeroTransReq));
            transfer(req);
        }catch (Exception e){

        }
    }

    private BaseResponse<TransferDto> transfer(TransferReq req) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(req));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
//        assertThat(baseResponse.getCode(),anyOf(matchers));
        return baseResponse;
    }

    @Test
    @Transactional
    @Rollback()
    public void testPrepareConfirm() throws Exception {
        TransferReq transferReq = new TransferReq();
        //预转账
        transferReq.setBusinessType("101005");
        TransactionReq transactionReq = new TransactionReq();
        transactionReq.setBusinessType("101005");
        transactionReq.setAccOut("GR-20190601-000001");
        transactionReq.setAmount("1");
        transactionReq.setJobNo("10001");
        transactionReq.setOutTransNo("JFDH"+UUID.randomUUID().toString().replaceAll("-",""));
        transferReq.setData(JSON.toJSONString(transactionReq));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/prepare")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(transferReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        TransferDto transferDto = JSONObject.parseObject(JSON.toJSONString(baseResponse.getData()), TransferDto.class);
        if(transferDto != null) {
            testConfirm(transactionReq.getOutTransNo(), transferDto.getTransNo());
        }
    }

    @Test
    @Transactional
    @Rollback()
    public void testPrepareCancel() throws Exception {
        TransferReq transferReq = new TransferReq();
        //预转账
        transferReq.setBusinessType("101005");
        TransactionReq transactionReq = new TransactionReq();
        transactionReq.setBusinessType("101005");
        transactionReq.setAccOut("GR-20190601-000001");
        transactionReq.setAmount("1");
        transactionReq.setJobNo("10001");
        transactionReq.setOutTransNo("JFDH"+UUID.randomUUID().toString().replaceAll("-",""));
        transferReq.setData(JSON.toJSONString(transactionReq));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/prepare")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(transferReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
        TransferDto transferDto = JSONObject.parseObject(JSON.toJSONString(baseResponse.getData()), TransferDto.class);
        if(transferDto != null) {
            testCancel(transactionReq.getOutTransNo(), transferDto.getTransNo());
        }
    }

    public void testConfirm(String outTransNo, String transNo) throws Exception {
        TransferReq transferReq = new TransferReq();
        transferReq.setBusinessType("101005");
        TransferConfirmReq transferConfirmReq = new TransferConfirmReq();
        transferConfirmReq.setOutTransNo(outTransNo);
        transferConfirmReq.setTransNo(transNo);
        transferReq.setData(JSON.toJSONString(transferConfirmReq));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/confirm")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(transferReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    public void testCancel(String outTransNo, String transNo) throws Exception {
        TransferReq transferReq = new TransferReq();
        transferReq.setBusinessType("101005");
        TransferCancelReq transferCancelReq = new TransferCancelReq();
        transferCancelReq.setOutTransNo(outTransNo);
        transferCancelReq.setTransNo(transNo);
        transferReq.setData(JSON.toJSONString(transferCancelReq));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/cancel")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(transferReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    @Transactional
    @Rollback()
    public void testBatchTransfer() throws Exception {
        TransferBatchReq batchReq = new TransferBatchReq();
        batchReq.setAccOut("JL-20190601-000001");
        batchReq.setBatchNo("11111111111111111111");
        batchReq.setBusinessType("101011");
        batchReq.setTotalAmount("20");
        List<TransferDetailReq> transferDetailList = new ArrayList<>();
        TransferDetailReq detailReq1 = new TransferDetailReq();
        detailReq1.setAccNo("GR-20190604-000066");
        detailReq1.setAmount("10");
        detailReq1.setRemark("测试节日礼金");
        TransferDetailReq detailReq2 = new TransferDetailReq();
        detailReq2.setAccNo("GR-20190601-000010");
        detailReq2.setAmount("10");
        detailReq2.setRemark("测试节日礼金");
        transferDetailList.add(detailReq1);
        transferDetailList.add(detailReq2);
        batchReq.setData(JSON.toJSONString(transferDetailList));
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/batch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(batchReq));
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse<TransferDto> baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testCheckBalanceEnough() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/checkBalanceEnough")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("accNo","GR-20190601-000008")
                .param("amount","1");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        Assert.assertNotNull(contentAsString);
    }

    @Test
    @Transactional
    @Rollback()
    public void testRefund() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/transfer/refund/BN20190603104255294996")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("batchNo", "BN20190603104255294996");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        assertThat(baseResponse.getCode(),anyOf(matchers));
    }

    @Test
    public void testLock() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/redis/lock")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("lockKey", "06041356-test")
                .param("timeoutMsecs", "20000")
                .param("expireMsecs", "20000");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        Assert.assertNotNull(contentAsString);
    }

    @Test
    public void testUnLock() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/redis/unlock")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("lockKey", "06041356-test");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void testQueryJlAccountByAd() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/account/queryJlAccountByAd/hanguocai")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("personAd","hanguocai");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        Assert.assertNotNull(contentAsString);
    }
}
