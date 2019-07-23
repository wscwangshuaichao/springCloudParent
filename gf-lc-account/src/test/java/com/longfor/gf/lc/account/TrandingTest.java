package com.longfor.gf.lc.account;

import com.alibaba.fastjson.JSON;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName TrandingTest
 * @Author jiangdan
 * @Date 2019/6/3 16:13
 **/
public class TrandingTest extends GfLcAccountApplicationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testQueryPage() throws Exception {
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
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        Assert.assertEquals("0000",baseResponse.getCode());
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
        BaseResponse baseResponse = JSON.parseObject(contentAsString, BaseResponse.class);
        Assert.assertNotNull(baseResponse);
        Assert.assertEquals("0000",baseResponse.getCode());
        Assert.assertNotNull(baseResponse.getData());
    }
}
