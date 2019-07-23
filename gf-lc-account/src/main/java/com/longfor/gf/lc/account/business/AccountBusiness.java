package com.longfor.gf.lc.account.business;

import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dao.entity.JlAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.service.GrAccountService;
import com.longfor.gf.lc.account.service.JlAccountService;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.MyBeanUtils;
import com.longfor.gf.lc.account.util.ResultUtils;
import com.longfor.gf.lc.account.util.TypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoguangxiao
 * @date 2019/5/17 14:46:25
 */
@Slf4j
@Service
public class AccountBusiness {

    @Resource
    private GrAccountService grAccountService;
    @Resource
    private JlAccountService jlAccountService;

    public BaseResponse<List<AccountDto>> queryListByAd(String personAd){
        log.info("[RequestParameter] - [AccountBusiness] - method:[queryListByAd] - request:[{}]", personAd);
        long curTime = System.currentTimeMillis();
        BaseResponse<List<AccountDto>> baseResponse = ResultUtils.getSuccess();
        try{
            List<AccountDto> list = new ArrayList<>();
            GrAccount grAccount = grAccountService.queryByPersonAd(personAd);
            if(grAccount != null){
                AccountDto accountDto = MyBeanUtils.copyProperties(grAccount, AccountDto.class);
                accountDto.setAccountType(TypeEnum.Account.PERSONAL.getKey());
                list.add(accountDto);
            }
            JlAccount jlAccount = jlAccountService.queryByPersonAd(personAd);
            if(jlAccount != null){
                AccountDto accountDto = MyBeanUtils.copyProperties(jlAccount, AccountDto.class);
                accountDto.setAccountType(TypeEnum.Account.EMBRAVE.getKey());
                list.add(accountDto);
            }
            baseResponse.setData(list);
            if(CollectionUtils.isEmpty(list)){
                baseResponse = ResultUtils.getResponse(baseResponse, CodeEnum.BizCode.NOT_EXIST);
            }
        }catch (Exception e){
            log.error("[Exception] - [AccountBusiness] - method:[queryListByAd] - errorMsg:[{}]", e.getMessage(),e);
            baseResponse = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountBusiness] - method:[queryListByAd] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(baseResponse), System.currentTimeMillis()-curTime);
        return baseResponse;
    }

    public BaseResponse<Map<String, Object>> queryJlAccountByAd(String personAd){
        log.info("[RequestParameter] - [AccountBusiness] - method:[queryJlAccountByAd] - request:[{}]", personAd);
        long curTime = System.currentTimeMillis();
        BaseResponse<Map<String, Object>> baseResponse = ResultUtils.getSuccess();
        try{
            JlAccount jlAccount = jlAccountService.queryByPersonAd(personAd);
            Map<String, Object> map = new HashMap<>();
            map.put("hasJlaccoubt", true);
            if(jlAccount == null){
                map.put("hasJlaccoubt", false);
            }
            baseResponse.setData(map);
        }catch (Exception e){
            log.error("[Exception] - [AccountBusiness] - method:[queryJlAccountByAd] - errorMsg:[{}]", e.getMessage(),e);
            baseResponse = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountBusiness] - method:[queryJlAccountByAd] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(baseResponse), System.currentTimeMillis()-curTime);
        return baseResponse;
    }
}
