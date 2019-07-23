package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.JlAccountMapper;
import com.longfor.gf.lc.account.dao.ZeroJlAccountMapper;
import com.longfor.gf.lc.account.dao.entity.JlAccount;
import com.longfor.gf.lc.account.dao.entity.ZeroJlAccount;
import com.longfor.gf.lc.account.dao.entity.example.JlAccountExample;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.JlAccountReq;
import com.longfor.gf.lc.account.service.impl.JlAccountServiceImpl;
import com.longfor.gf.lc.account.util.MyBeanUtils;
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
import static org.mockito.Mockito.*;

/**
 * @ClassName JlAccountServiceImplTest
 * @Author jiangdan
 * @Date 2019/6/5 11:19
 **/
@RunWith(PowerMockRunner.class)
public class JlAccountServiceImplTest {

    @InjectMocks
    private JlAccountServiceImpl jlAccountService;
    @Mock
    private SequenceService sequenceService;
    @Mock
    private JlAccountMapper jlAccountMapper;
    @Mock
    private ZeroJlAccountMapper zeroJlAccountMapper;
    @Mock
    private RedisLockService redisLockService;

    @Test
    public void testAccSave(){
        String accNo = "JL-20190605134000001";
        List<JlAccount> list = new ArrayList<>();
        try {
            when(redisLockService.lock(any(String.class), any(Long.class), any(Long.class))).thenReturn(true);
            when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(list);
            when(sequenceService.createAccNo(any(String.class))).thenReturn(accNo);
            when(jlAccountMapper.insert(any(JlAccount.class))).thenReturn(1);
            when(zeroJlAccountMapper.insert(any(ZeroJlAccount.class))).thenReturn(1);
            redisLockService.unlock(any(String.class));
            JlAccountReq accountReq = new JlAccountReq();
            accountReq.setPersonAd("100036");
            int num = jlAccountService.save(accountReq);
//        Assert.assertThat(num, is(1));
            verify(redisLockService, times(1)).lock(any(String.class), any(Long.class), any(Long.class));
            verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));
            verify(jlAccountMapper, times(1)).insert(any(JlAccount.class));
            verify(zeroJlAccountMapper, times(1)).insert(any(ZeroJlAccount.class));
        }catch (Exception e){

        }
    }

    @Test
    public void testAccSaveLock() {
        try {
            when(redisLockService.lock(any(String.class), any(Long.class), any(Long.class))).thenReturn(false);
            when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(null);
            when(sequenceService.createAccNo(any(String.class))).thenThrow(new ServiceException());
            when(jlAccountMapper.insert(any(JlAccount.class))).thenReturn(0);
            when(zeroJlAccountMapper.insert(any(ZeroJlAccount.class))).thenReturn(0);
            redisLockService.unlock(any(String.class));
            JlAccountReq accountReq = new JlAccountReq();
            accountReq.setPersonAd("100036");
            int num = jlAccountService.save(accountReq);
        }catch (Exception e){

        }
    }

    @Test(expected = Exception.class)
    public void testAccSaveException() throws ServiceException {
        when(redisLockService.lock(any(String.class), any(Long.class), any(Long.class))).thenReturn(true);
        when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(null);
        when(sequenceService.createAccNo(any(String.class))).thenThrow(new ServiceException());
        when(jlAccountMapper.insert(any(JlAccount.class))).thenReturn(0);
        when(zeroJlAccountMapper.insert(any(ZeroJlAccount.class))).thenReturn(0);
        redisLockService.unlock(any(String.class));
        JlAccountReq accountReq = new JlAccountReq();
        accountReq.setPersonAd("100036");
        int num = jlAccountService.save(accountReq);
    }

    @Test
    public void testQueryPage(){
        List<JlAccount> list = new ArrayList<>();
        JlAccount dto = new JlAccount();
        dto.setPersonAd("100036");
        dto.setAccNo("GR-20190605134000001");
        list.add(dto);
        int total = 1;
        when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(list);
        when(jlAccountMapper.selectCountByExample(any(JlAccountExample.class))).thenReturn(total);
        PageInfo page = new PageInfo();
        page.setPageSize(10);
        page.setPageNum(1);
        page.setList(list);
        page.setTotal(total);
        page.setPages((int)(page.getTotal()+page.getPageSize()-1)/page.getPageSize());
        JlAccountReq accountReq = new JlAccountReq();
        page = jlAccountService.queryPage(accountReq, page);
        verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));
        verify(jlAccountMapper, times(1)).selectCountByExample(any(JlAccountExample.class));

    }

    @Test
    public void testQueryByAccNo(){
        JlAccount jlAccount = new JlAccount();
        jlAccount.setAccNo("JL-20190605134000001");
        when(jlAccountMapper.selectOneByExample(any(JlAccountExample.class))).thenReturn(jlAccount);
        jlAccount = jlAccountService.queryByAccNo(jlAccount.getAccNo());

        verify(jlAccountMapper, times(1)).selectOneByExample(any(JlAccountExample.class));

    }

    @Test
    public void testQueryByGrAccountReq(){
        try {
            JlAccountReq accountReq = new JlAccountReq();
            accountReq.setPersonAd("100036");
            List<JlAccount> list = new ArrayList<>();
            JlAccount jlAccount = new JlAccount();
            jlAccount.setPersonAd(accountReq.getPersonAd());
            list.add(jlAccount);
            when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(list);
            jlAccount = jlAccountService.queryByJlAccountReq(accountReq);

            verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));
        }catch (Exception e){

        }
    }

    @Test
    public void testBatchSave(){
        try {
            List<JlAccountReq> list = new ArrayList<>();
            JlAccountReq accountReq = new JlAccountReq();
            accountReq.setPersonAd("100036");
            list.add(accountReq);
            List<JlAccount> searchList = new ArrayList<>();
            when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(searchList);
            String accNo = "JL-20190605134000001";
            when(sequenceService.createAccNo(any(String.class))).thenReturn(accNo);
            when(jlAccountMapper.insertList(any(List.class))).thenReturn(1);
            int num = jlAccountService.batchSave(list);

            verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));
            verify(sequenceService, times(1)).createAccNo(any(String.class));
            verify(jlAccountMapper, times(1)).insertList(any(List.class));
        }catch (Exception e){

        }
    }

    @Test
    public void testQueryByPersonAd(){
        try {
            String ad = "100036";
            List<JlAccount> searchList = new ArrayList<>();
            JlAccount jlAccount = new JlAccount();
            jlAccount.setPersonAd(ad);
            searchList.add(jlAccount);
            when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(searchList);
            jlAccount = jlAccountService.queryByPersonAd(ad);

            verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));
        }catch (Exception e){

        }
    }

    @Test
    public void testQueryListByPersonAd() {
        String ad = "100036";
        List<JlAccount> searchList = new ArrayList<>();
        JlAccount jlAccount = new JlAccount();
        jlAccount.setPersonAd(ad);
        searchList.add(jlAccount);
        when(jlAccountMapper.selectByExample(any(JlAccountExample.class))).thenReturn(searchList);
        List<JlAccount> list = jlAccountService.queryListByPersonAd(ad);

        verify(jlAccountMapper, times(1)).selectByExample(any(JlAccountExample.class));

    }

    @Test
    public void testUpdate() {
        try {
            JlAccountReq accountReq = new JlAccountReq();
            accountReq.setAccNo("JL-20190605134000001");
            accountReq.setStatus(0);
            JlAccount jlAccount = new JlAccount();
            jlAccount.setAccNo(accountReq.getAccNo());
            jlAccount.setStatus(1);
            when(jlAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(jlAccount);
            when(jlAccountMapper.updateByPrimaryKeySelective(any(JlAccount.class))).thenReturn(1);
            int num = jlAccountService.update(accountReq);

            verify(jlAccountMapper, times(1)).selectByPrimaryKey(any(String.class));
            verify(jlAccountMapper, times(1)).updateByPrimaryKeySelective(any(JlAccount.class));
        }catch (Exception e){

        }
    }

    @Test(expected = Exception.class)
    public void testUpdateException() throws ServiceException {
        when(jlAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(null);
        when(jlAccountMapper.updateByPrimaryKeySelective(any(JlAccount.class))).thenThrow(new ServiceException());
        JlAccountReq accountReq = new JlAccountReq();
        accountReq.setAccNo("JL-20190605134000001");
        int num = jlAccountService.update(accountReq);
    }

    @Test
    public void testDelete() {
        try {
            String accNo = "JL-20190605134000001";
            JlAccount grAccount = new JlAccount();
            when(jlAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(grAccount);
            when(jlAccountMapper.updateByPrimaryKeySelective(any(JlAccount.class))).thenReturn(1);
            int num = jlAccountService.delete(accNo);
        }catch (Exception e){

        }
    }

    @Test(expected = Exception.class)
    public void testDeleteException() throws ServiceException {
        when(jlAccountMapper.selectByPrimaryKey(any(String.class))).thenReturn(null);
        when(jlAccountMapper.updateByPrimaryKeySelective(any(JlAccount.class))).thenThrow(new ServiceException());
        String accNo = "JL-20190605134000001";
        int num = jlAccountService.delete(accNo);
    }
}
