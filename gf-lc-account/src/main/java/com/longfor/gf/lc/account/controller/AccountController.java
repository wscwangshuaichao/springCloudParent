package com.longfor.gf.lc.account.controller;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiAccountService;
import com.longfor.gf.lc.account.business.AccountBusiness;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.manager.AccountManager;
import com.longfor.gf.lc.account.req.AccountReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author guoguangxiao
 * @date 2019/5/15 11:37:38
 */
@Slf4j
@RestController
public class AccountController implements ApiAccountService {

    @Resource
    private AccountManager accountManager;
    @Resource
    private AccountBusiness accountBusiness;

    @Override
    public BaseResponse<PageInfo<AccountDto>> getList(@RequestBody(required = false) AccountReq accountReq) {
        PageInfo<AccountDto> pageInfo = new PageInfo<AccountDto>();
        pageInfo.setPageNum(accountReq.getPageNum() > 0 ? accountReq.getPageNum() : 1);
        pageInfo.setPageSize(accountReq.getPageSize() > 0 ? accountReq.getPageSize() : 10);
        return accountManager.queryPage(accountReq, pageInfo);
    }

    @Override
    public BaseResponse<AccountDto> getDetailByAccNo(@PathVariable("accNo")String accNo){
        return accountManager.getDetailByAccNo(accNo);
    }

    @Override
    public BaseResponse<AccountDto> getDetail(@RequestBody(required = false)AccountReq accountReq) {
        return accountManager.queryByAccountReq(accountReq);
    }

    @Override
    public BaseResponse<List<AccountDto>> getListByAd(@PathVariable("personAd") String personAd) {
        return accountBusiness.queryListByAd(personAd);
    }


    @Override
    public BaseResponse<AccountDto> addAccount(@RequestBody(required = false) AccountReq accountReq) {
        return accountManager.save(accountReq);
    }

    @Override
    public BaseResponse<AccountDto> addList(@RequestBody AccountReq accountReq) {
        return accountManager.addList(accountReq);
    }

    @Override
    public BaseResponse modifyAccount(@RequestBody AccountReq accountReq) {
        return accountManager.modifyAccount(accountReq);
    }

    @Override
    public BaseResponse removeAccount(@PathVariable("accNo") String accNo) {
        return accountManager.removeAccount(accNo);
    }

    @Override
    public BaseResponse<Map<String, Object>> queryJlAccountByAd(@PathVariable("personAd") String personAd) {
        return accountBusiness.queryJlAccountByAd(personAd);
    }
}
