package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.GrAccountMapper;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dao.entity.example.GrAccountExample;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.GrAccountReq;
import com.longfor.gf.lc.account.service.impl.GrAccountServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @ClassName GrAccountServiceImplTest
 * @Author jiangdan
 * @Date 2019/6/5 11:19
 **/
@RunWith(PowerMockRunner.class)
public class GrAccountServiceImplTest {

    @InjectMocks
    private GrAccountServiceImpl grAccountService;
    @Mock
    private GrAccountMapper grAccountMapper;
    @Mock
    private SequenceService sequenceService;

    @Test
    public void testAccSave() {
        String accNo = "GR-20190605134000001";
        List<GrAccount> list = new ArrayList<>();
        try {
            when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(list);
            when(sequenceService.createAccNo(any(String.class))).thenReturn(accNo);
            when(grAccountMapper.insert(any(GrAccount.class))).thenReturn(1);
            GrAccountReq accountReq = new GrAccountReq();
            accountReq.setPersonAd("100036");
            int num = grAccountService.save(accountReq);
//        Assert.assertThat(num, is(1));
        } catch (ServiceException e) {
        }
    }

    @Test(expected = Exception.class)
    public void testAccSaveException() throws ServiceException {
        when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(null);
        when(sequenceService.createAccNo(any(String.class))).thenThrow(new ServiceException());
        when(grAccountMapper.insert(any(GrAccount.class))).thenThrow(new ServiceException());
        GrAccountReq accountReq = new GrAccountReq();
        accountReq.setPersonAd("100036");
        int num = grAccountService.save(accountReq);
    }

    @Test
    public void testQueryPage(){
        List<GrAccount> list = new ArrayList<>();
        GrAccount dto = new GrAccount();
        dto.setPersonAd("100036");
        dto.setAccNo("GR-20190605134000001");
        list.add(dto);
        int total = 1;
        when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(list);
        when(grAccountMapper.selectCountByExample(any(GrAccountExample.class))).thenReturn(total);
        PageInfo page = new PageInfo();
        page.setPageSize(10);
        page.setPageNum(1);
        page.setList(list);
        page.setTotal(total);
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        GrAccountReq accountReq = new GrAccountReq();
        page = grAccountService.queryPage(accountReq, page);
    }

    @Test
    public void testQueryByAccNo(){
        GrAccount grAccount = new GrAccount();
        grAccount.setPersonAd("100036");
        when(grAccountMapper.selectOneByExample(any(GrAccountExample.class))).thenReturn(grAccount);
        grAccount = grAccountService.queryByAccNo(grAccount.getPersonAd());
    }

    @Test
    public void testQueryByGrAccountReq() {
        GrAccountReq accountReq = new GrAccountReq();
        accountReq.setPersonAd("100036");
        List<GrAccount> grAccounts = new ArrayList<>();
        GrAccount grAccount = new GrAccount();
        grAccount.setPersonAd(accountReq.getPersonAd());
        grAccounts.add(grAccount);
        when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(grAccounts);
        try {
            grAccount = grAccountService.queryByGrAccountReq(accountReq);
        } catch (ServiceException e) {
        }
    }

    @Test
    public void testBatchSave(){
        List<GrAccountReq> list = new ArrayList<>();
        GrAccountReq accountReq = new GrAccountReq();
        accountReq.setPersonAd("100036");
        list.add(accountReq);
        List<GrAccount> searchList = new ArrayList<>();
        try {
            when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(searchList);
            String accNo = "GR-20190605134000001";
            when(sequenceService.createAccNo(any(String.class))).thenReturn(accNo);
            when(grAccountMapper.insertList(any(List.class))).thenReturn(1);
            int num = grAccountService.batchSave(list);
        } catch (ServiceException e) {
        }
    }

    @Test
    public void testQueryByPersonAd(){
        String ad = "100036";
        List<GrAccount> searchList = new ArrayList<>();
        GrAccount grAccount = new GrAccount();
        grAccount.setPersonAd(ad);
        searchList.add(grAccount);
        when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(searchList);
        try {
            grAccount = grAccountService.queryByPersonAd(ad);
        } catch (ServiceException e) {
        }
    }

    @Test
    public void testQueryListByPersonAd(){
        String ad = "100036";
        List<GrAccount> searchList = new ArrayList<>();
        GrAccount grAccount = new GrAccount();
        grAccount.setPersonAd(ad);
        searchList.add(grAccount);
        when(grAccountMapper.selectByExample(any(GrAccountExample.class))).thenReturn(searchList);
        List<GrAccount> list = grAccountService.queryListByPersonAd(ad);
    }

    @Test
    public void testUpdate(){
        GrAccountReq accountReq = new GrAccountReq();
        accountReq.setAccNo("GR-20190605134000001");
        accountReq.setStatus(0);
        GrAccount grAccount = new GrAccount();
        grAccount.setAccNo(accountReq.getAccNo());
        grAccount.setStatus(1);
        when(grAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(grAccount);
        when(grAccountMapper.updateByPrimaryKeySelective(any(GrAccount.class))).thenReturn(1);
        try {
            int num = grAccountService.update(accountReq);
        } catch (ServiceException e) {
        }
    }

    @Test(expected = Exception.class)
    public void testUpdateException(){
        when(grAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(null);
        when(grAccountMapper.updateByPrimaryKeySelective(any(GrAccount.class))).thenThrow(new ServiceException());
        GrAccountReq accountReq = new GrAccountReq();
        accountReq.setAccNo("GR-20190605134000001");
        try {
            int num = grAccountService.update(accountReq);
        } catch (ServiceException e) {

        }
    }

    @Test
    public void testDelete(){
        String accNo = "GR-20190605134000001";
        GrAccount grAccount = new GrAccount();
        grAccount.setAccNo(accNo);
        when(grAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(grAccount);
        when(grAccountMapper.updateByPrimaryKeySelective(any(GrAccount.class))).thenReturn(1);
        try {
            int num = grAccountService.delete(accNo);
        } catch (ServiceException e) {
        }
    }

    @Test(expected = Exception.class)
    public void testDeleteException() throws ServiceException {
        when(grAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(null);
        when(grAccountMapper.updateByPrimaryKeySelective(any(GrAccount.class))).thenThrow(new ServiceException());
        String accNo = "GR-20190605134000001";
        int num = grAccountService.delete(accNo);
    }
}
