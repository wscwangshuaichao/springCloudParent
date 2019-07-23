package com.longfor.gf.lc.account.dao;

import com.longfor.gf.lc.account.GfLcAccountApplicationTest;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import com.longfor.gf.lc.account.req.TransactionReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 这个测试是把应用完全起来, 当然, 我们可以在测试的配置文件中去掉很多不需要的配置
 * 通过内存中启动一个 h2 数据库来模拟sql的执行
 * @author jiangdan
 * @date 2019/5/16 17:45:32
 */
public class GrAccountMapperTest extends GfLcAccountApplicationTest {

    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private SequenceMapper sequenceMapper;

    @Test
    public void testInsert() {
        String batchNo = sequenceMapper.getBatchNo();

        for(int i= 0;i<100;i++){
            TransactionReq req = new TransactionReq();
            req.setBatchNo(batchNo);
            req.setAccOut("10000001");
            req.setAccIn("1000000" + i);

        }

    }

    @Test
    public void testSelect() {
//        GrAccount grAccount = grAccountMapper.selectByPrimaryKey("GR-20190601-000007");
        AccountDto dto = accountMapper.selectGrAccByAccNo("GR-20190601-000007");

        assertNotNull(dto);
//        assertThat(dto.getPersonAd(), is("jiangd12"));
    }

    @Test
    public void testSelectTrans() {
        TradingSearchReq req = new TradingSearchReq();
        req.setAccNo("JL-20190601-000011");
//       BigDecimal list = transactionMapper.selectIncomeByAccNo(req);
//        List<TransactionDto> list = transactionMapper.queryPage(req);
        String s = transactionMapper.queryAccInByBnoAndBtype("BN20190601151119327784");
//        assertNotNull(list);

    }

}