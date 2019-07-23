package com.longfor.gf.lc.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.ResultUtils;
import com.longfor.gf.lc.account.util.TypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author guoguangxiao
 * @date 2019/5/16 17:15:19
 */
@Slf4j
@Service
public class AccountManager {

    @Resource
    Map<String, AccountBizPlugin> accountBizPluginMap;
    @Value("${batch.save.account.num}")
    private int saveNum;

    /**
     * Method: 账户新增
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:56
     * @param
     * @return
     */
    public BaseResponse save(AccountReq req) {
        log.info("[RequestParameter] - [AccountManager] - method:[save] - request:[{}]", JSONObject.toJSONString(req));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            req.check();
            response = getAccountPlugin(getPluginCode(req.getAccountType())).save(req);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[save] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[save] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[save] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;

    }

    private String getPluginCode(String accountType) throws ServiceException {

        String pluginCode = TypeEnum.Account.getValue(accountType);
        if(StringUtils.isEmpty(pluginCode)){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_TYPE_NOT_SUPPORT);
        }
        return pluginCode;

    }

    private AccountBizPlugin getAccountPlugin(String pluginCode) throws ServiceException{
        AccountBizPlugin accountBizPlugin = accountBizPluginMap.get(pluginCode);
        if(accountBizPlugin == null){
            throw new ServiceException(CodeEnum.BizCode.ACCOUNT_TYPE_NOT_SUPPORT);
        }
        return accountBizPlugin;
    }

    /**
     * @description 分页查询账户信息
     * @author jiangdan
     * @date 2019/5/17 11:35
     * @param[ accountReq, pageInfo]
     * @return
     */
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo pageInfo){
        log.info("[RequestParameter] - [AccountManager] - method:[queryPage] - request:[{}] - pageInfo:[{}]", JSONObject.toJSONString(accountReq),JSONObject.toJSONString(pageInfo));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        int dataSize = 0;
        try{
            accountReq.check();
            response = getAccountPlugin(getPluginCode(accountReq.getAccountType())).queryPage(accountReq, pageInfo);
            PageInfo data = (PageInfo)response.getData();
            if(data!=null && data.getList()!=null){
                dataSize = data.getList().size();
            }
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[queryPage] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[queryPage] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[queryPage] - response:[{}] - duration:[{}]",
                response.getCode()+"|"+response.getMessage()+"|"+dataSize ,System.currentTimeMillis()-curTime);
        return response;
    }

    /**
     * Method: 通过账户号查询账户详情
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:58
     * @param
     * @return
     */
    public BaseResponse<AccountDto> getDetailByAccNo(String accNo){
        log.info("[RequestParameter] - [AccountManager] - method:[getDetailByAccNo] - request:[{}]", accNo);
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            response = getAccountPlugin(getPluginCode(getAccType(accNo))).queryByAccNo(accNo);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[getDetailByAccNo] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[getDetailByAccNo] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[getDetailByAccNo] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    /**
     * Method: 通过条件查询唯一账户信息
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 18:59
     * @param
     * @return
     */
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq){
        log.info("[RequestParameter] - [AccountManager] - method:[queryByAccountReq] - request:[{}]", JSONObject.toJSONString(accountReq));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            accountReq.check();
            response = getAccountPlugin(getPluginCode(accountReq.getAccountType())).queryByAccountReq(accountReq);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[queryByAccountReq] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[queryByAccountReq] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[queryByAccountReq] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    /**
     * Method: 批量添加账户
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 19:00
     * @param
     * @return
     */
    public BaseResponse<AccountDto> addList(AccountReq accountReq){
        log.info("[RequestParameter] - [AccountManager] - method:[addList] - request:[{}]", JSONObject.toJSONString(accountReq));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            accountReq.check();
            response = getAccountPlugin(getPluginCode(accountReq.getAccountType())).batchSave(accountReq, saveNum);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[addList] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[addList] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[addList] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    /**
     * Method: 修改账户信息
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 19:01
     * @param
     * @return
     */
    public BaseResponse modifyAccount(AccountReq accountReq){
        log.info("[RequestParameter] - [AccountManager] - method:[modifyAccount] - request:[{}]", JSONObject.toJSONString(accountReq));
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            accountReq.check();
            response = getAccountPlugin(getPluginCode(accountReq.getAccountType())).update(accountReq);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[modifyAccount] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[modifyAccount] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[modifyAccount] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }

    private String getAccType(String accNo) {
        return StringUtils.isEmpty(accNo) ? "null" : accNo.substring(0,2);
    }


    /**
     * Method: 删除账户
     * Description:
     * Author guoguangxiao
     * Data 2019/5/30 19:01
     * @param
     * @return
     */
    public BaseResponse removeAccount(String accNo){
        log.info("[RequestParameter] - [AccountManager] - method:[removeAccount] - request:[{}]", accNo);
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            response = getAccountPlugin(getPluginCode(getAccType(accNo))).delete(accNo);
        }catch (ServiceException se){
            log.error("[Exception] - [AccountManager] - method:[removeAccount] - errorMsg:[{}]", se.getMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [AccountManager] - method:[removeAccount] - errorMsg:[{}]", e.getMessage(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [AccountManager] - method:[removeAccount] - response:[{}] - duration:[{}]",
                JSONObject.toJSONString(response) ,System.currentTimeMillis()-curTime);
        return response;
    }
}
