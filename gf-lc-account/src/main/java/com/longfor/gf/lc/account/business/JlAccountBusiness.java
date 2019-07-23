package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.JlAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.AccountBizPlugin;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.req.JlAccountReq;
import com.longfor.gf.lc.account.service.JlAccountService;
import com.longfor.gf.lc.account.util.MyBeanUtils;
import com.longfor.gf.lc.account.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/17 09:55:19
 */
@Service
public class JlAccountBusiness implements AccountBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private JlAccountService jlAccountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    public BaseResponse save(AccountReq req) {
        return apiHelper.invoke(req.getData(),new Handle<JlAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                JlAccountReq jlAccountReq = (JlAccountReq) handelEntity.getRequest();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE, req.getAccountType(),jlAccountReq.getPersonAd(),5,1));
                if(jlAccountService.save(jlAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public JlAccountReq getRequest() {
                return new JlAccountReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "save";
            }
            @Override
            public String getErrorMsg() {
                return "激励账号新增失败";
            }
        });
    }


    @Override
    public BaseResponse batchSave(AccountReq accountReq, int saveNum) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<List<JlAccountReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                if(StringUtils.isNotBlank(accountReq.getData())) {
                    List<JlAccountReq> reqList = accountReq.checkData(accountReq.getData(), saveNum, JlAccountReq.class);
                    int num = jlAccountService.batchSave(reqList);
                    if(num > 0){
                        handelEntity.setResponse(ResultUtils.getSuccess());
                    }else{
                        handelEntity.setResponse(ResultUtils.getFail());
                    }
                }
            }
            @Override
            public List<JlAccountReq> getRequest() {
                return new ArrayList<>();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "batchSave";
            }
            @Override
            public String getErrorMsg() {
                return "激励账号批量新增失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccNo(String accNo) {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                JlAccount jlAccount = jlAccountService.queryByAccNo(accNo);
                if(jlAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(jlAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist(accNo));
                }
            }
            @Override
            public String getRequest() {
                return "queryByAccNo";
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "queryByAccNo";
            }
            @Override
            public String getErrorMsg() {
                return "激励账号查询失败";
            }
        });
    }

    @Override
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page) {
        return apiHelper.invoke(accountReq.getData(),new Handle<JlAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<PageInfo<AccountDto>> response = ResultUtils.getSuccess();
                JlAccountReq jlAccountReq = (JlAccountReq) handelEntity.getRequest();
                PageInfo<AccountDto> pageInfo = jlAccountService.queryPage(jlAccountReq, page);
                response.setData(pageInfo);
                handelEntity.setResponse(response);
            }
            @Override
            public JlAccountReq getRequest() {
                return new JlAccountReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "queryPage";
            }
            @Override
            public String getErrorMsg() {
                return "分页查询激励账号失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<JlAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                JlAccountReq jlAccountReq = (JlAccountReq) handelEntity.getRequest();
                JlAccount jlAccount = jlAccountService.queryByJlAccountReq(jlAccountReq);
                if(jlAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(jlAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist("JL账号"));
                }
            }
            @Override
            public JlAccountReq getRequest() {
                return new JlAccountReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "queryByAccountReq";
            }
            @Override
            public String getErrorMsg() {
                return "根据条件查询激励账号详情失败";
            }
        });
    }

    @Override
    public BaseResponse update(AccountReq req) throws ServiceException {
        return apiHelper.invoke(req.getData(),new Handle<JlAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                JlAccountReq jlAccountReq = (JlAccountReq) handelEntity.getRequest();
                jlAccountReq.checkUpdate();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.UPDATE, req.getAccountType(),jlAccountReq.getPersonAd(),5,1));
                if(jlAccountService.update(jlAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public JlAccountReq getRequest() {
                return new JlAccountReq();
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "update";
            }
            @Override
            public String getErrorMsg() {
                return "激励账号修改失败";
            }
        });
    }

    @Override
    public BaseResponse delete(String accNo) throws ServiceException {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                int num = jlAccountService.delete(accNo);
                if(num > 0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public String getRequest() {
                return "delete";
            }
            @Override
            public String getClassName() {
                return this.getClass().getName();
            }
            @Override
            public String getMethod() {
                return "delete";
            }
            @Override
            public String getErrorMsg() {
                return "激励账号删除失败";
            }
        });
    }
}
