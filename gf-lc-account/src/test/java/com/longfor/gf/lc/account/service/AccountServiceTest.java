package com.longfor.gf.lc.account.service;

import com.longfor.gf.lc.account.GfLcAccountApplicationTest;
import com.longfor.gf.lc.account.dao.entity.GatGrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

/**
 * @ClassName AccountServiceTest
 * @Author jiangdan
 * @Date 2019/6/4 15:56
 **/
public class AccountServiceTest extends GfLcAccountApplicationTest{

    @Autowired
    private AccountService accountService;
    @Autowired
    private CpAccountService cpAccountService;
    @Autowired
    private GrAccountService grAccountService;
    @Autowired
    private JlAccountService jlAccountService;
    @Autowired
    private QtAccountService qtAccountService;
    @Autowired
    private YwAccountService ywAccountService;

    @Test
    public void testQueryAccount() throws ServiceException {
        AccountDto grAcc = accountService.queryAccount("GR-20190604-000066", "GR");
        AccountDto jlAcc = accountService.queryAccount("JL-20190604-000024", "JL");
        AccountDto qtAcc = accountService.queryAccount("QT-20190604-000009", "QT");
        AccountDto cpAcc = accountService.queryAccount("CP-20190604-000011", "CP");
        AccountDto ywAcc = accountService.queryAccount("YW-20190604-000011", "YW");
        Assert.assertNotNull(grAcc);
        Assert.assertNotNull(jlAcc);
        Assert.assertNotNull(qtAcc);
        Assert.assertNotNull(cpAcc);
        Assert.assertNotNull(ywAcc);
    }

    @Test
    @Transactional
    public void testSaveTransaction() throws ServiceException {
        TransactionReq transReq = new TransactionReq();
        transReq.setAccOut("GR-20190601-000001");
        transReq.setAmount("100");
        transReq.setAccIn("GR-20190601-000001");
        transReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-",""));
        transReq.setBusinessType("101001");
        transReq.setRemarks("测试单个红包的测试用例数据");

        TransactionDto singleDto = accountService.saveTransaction(transReq);
        Assert.assertNotNull(singleDto);

        transReq.setBatchNo(singleDto.getBatchNo());
        transReq.setAccOut("GR-20190601-000001");
        transReq.setAmount("90");
        transReq.setAccIn("GR-20190604-000066");
        transReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-",""));
        transReq.setBusinessType("102000");
        transReq.setRemarks("测试抢红包的测试用例数据");
        TransactionDto grabDto = accountService.saveTransaction(transReq);
        Assert.assertNotNull(grabDto);
        transReq.setBatchNo(singleDto.getBatchNo());
        transReq.setAccOut("GR-20190601-000001");
        transReq.setAmount("90");
        transReq.setAccIn("GR-20190601-000001");
        transReq.setOutTransNo(UUID.randomUUID().toString().replaceAll("-",""));
        transReq.setBusinessType("101003");
        transReq.setRemarks("测试红包退款的测试用例数据");
        TransactionDto refundDto = accountService.saveTransaction(transReq);
        Assert.assertNotNull(refundDto);
    }

    @Test
    @Transactional
    public void testUpdateBalanceOrFrozen() throws ServiceException {
        AccountInfoReq accountInfoReq = new AccountInfoReq();
        accountInfoReq.setAccNo("JL-20190601-000011");
        accountInfoReq.setAccType("JL");
        accountInfoReq.setFrozenAmt(new BigDecimal("2"));
        accountInfoReq.setBalanceAmt(new BigDecimal("2"));
        int jl_num = accountService.updateBalanceOrFrozen(accountInfoReq);
//        Assert.assertThat(jl_num, is(1));

        accountInfoReq.setAccNo("CP-20190604-000011");
        accountInfoReq.setAccType("CP");
        int cp_num = accountService.updateBalanceOrFrozen(accountInfoReq);
//        Assert.assertThat(cp_num, is(1));

        accountInfoReq.setAccNo("QT-20190604-000009");
        accountInfoReq.setAccType("QT");
        int qt_num = accountService.updateBalanceOrFrozen(accountInfoReq);
//        Assert.assertThat(qt_num, is(1));

        accountInfoReq.setAccNo("GR-20190601-000008");
        accountInfoReq.setAccType("GR");
        int gr_num = accountService.updateBalanceOrFrozen(accountInfoReq);
//        Assert.assertThat(gr_num, is(1));
    }

    @Test
    public void testIsExistAccByAccNo() {
        assertTrue(accountService.isExistAccByAccNo("CB-JL", "CB"));
        assertTrue(accountService.isExistAccByAccNo("YW-20190604-000011", "YW"));
        assertTrue(!accountService.isExistAccByAccNo("CP-20190604-000011", "CP"));
    }

    @Test
    @Transactional
    public void testSaveGatAccount() throws ServiceException {
        GatGrAccount gatGrAccount = accountService.saveGatAccount("GR-20190604-000066", "100032", "100032");
        Assert.assertNotNull(gatGrAccount);
        Assert.assertEquals("GAT-GR-20190604-000066" , gatGrAccount.getAccNo());
    }

    @Test
    @Transactional
    public void testUpdateTransStsByTransNoAndOutNo(){
        int num = accountService.updateTransStsByTransNoAndOutNo("TN2019060416542657314708", "52b1680ff47b4c26ae3c9021f7079ee7", "1");
//        Assert.assertThat(num, is(1));
    }

    @Test
    @Transactional
    public void testUpdateAccBalance(){
        int gr_num = accountService.updateAccBalance("GR-20190604-000066", "10", 1);
//        Assert.assertThat(gr_num, is(1));
        int cp_num = accountService.updateAccBalance("CP-20190604-000011", "10", 1);
//        Assert.assertThat(cp_num, is(1));
        int jl_num = accountService.updateAccBalance("JL-20190604-000022", "10", 1);
//        Assert.assertThat(jl_num, is(1));
        int qt_num = accountService.updateAccBalance("QT-20190604-000009", "10", 1);
//        Assert.assertThat(qt_num, is(1));
    }

    @Test
    @Transactional
    public void testUpdateTransStatus() throws ServiceException {
        int gr_num = accountService.updateTransStatus("BN20190604165426585883", "101000");
//        Assert.assertThat(gr_num, is(1));
    }

    @Test
    public void testSelectGrabAmountByAccNo(){
        BigDecimal amount = accountService.selectGrabAmountByAccNo("BN20190601151119327784", "JL-20190601-000011");
    }

    @Test
    @Transactional
    public void testAccSave() throws ServiceException {
        GrAccountReq gracc = new GrAccountReq();
        gracc.setPersonAd("1000036");
        int grnum = grAccountService.save(gracc);
       // Assert.assertThat(grnum, is(1));
        JlAccountReq jlacc = new JlAccountReq();
        jlacc.setPersonAd("1000036");
        int jlnum = jlAccountService.save(jlacc);
//        Assert.assertThat(jlnum, is(1));
        CpAccountReq cpacc = new CpAccountReq();
        cpacc.setCpCode("1000036");
        cpacc.setCpName("1000036");
        int cpnum = cpAccountService.save(cpacc);
//        Assert.assertThat(cpnum, is(1));
        YwAccountReq ywacc = new YwAccountReq();
        ywacc.setAccName("1000036");
        int ywnum = ywAccountService.save(ywacc);
//        Assert.assertThat(ywnum, is(1));
        QtAccountReq qtacc = new QtAccountReq();
        qtacc.setQtCode("1000036");
        qtacc.setQtName("1000036");
        int qtnum = qtAccountService.save(qtacc);
//        Assert.assertThat(qtnum, is(1));
    }


}
