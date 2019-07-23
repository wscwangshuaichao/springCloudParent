package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.QtAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.AccountBizPlugin;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.req.QtAccountReq;
import com.longfor.gf.lc.account.service.QtAccountService;
import com.longfor.gf.lc.account.util.MyBeanUtils;
import com.longfor.gf.lc.account.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/17 09:55:19
 */
@Service
public class QtAccountBusiness implements AccountBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private QtAccountService qtAccountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    public BaseResponse save(AccountReq req) {
        return apiHelper.invoke(req.getData(),new Handle<QtAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                QtAccountReq qtAccountReq = (QtAccountReq) handelEntity.getRequest();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE, req.getAccountType(),qtAccountReq.getQtName(),20,1));
                if(qtAccountService.save(qtAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public QtAccountReq getRequest() {
                return new QtAccountReq();
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
                return "产品账号新增失败";
            }
        });
    }


    @Override
    public BaseResponse batchSave(AccountReq accountReq, int saveNum) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<List<QtAccountReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                List<QtAccountReq> reqList = accountReq.checkData(accountReq.getData(), saveNum, QtAccountReq.class);
                int num = qtAccountService.batchSave(reqList);
                if(num > 0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public List<QtAccountReq> getRequest() {
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
                return "其他账号批量新增失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccNo(String accNo) {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                QtAccount qtAccount = qtAccountService.queryByAccNo(accNo);
                if(qtAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(qtAccount, AccountDto.class));
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
                return "其他账号查询失败";
            }
        });
    }

    @Override
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page) {
        return apiHelper.invoke(accountReq.getData(),new Handle<QtAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<PageInfo<AccountDto>> response = ResultUtils.getSuccess();
                QtAccountReq qtAccountReq = (QtAccountReq) handelEntity.getRequest();
                PageInfo<AccountDto> pageInfo = qtAccountService.queryPage(qtAccountReq, page);
                response.setData(pageInfo);
                handelEntity.setResponse(response);
            }
            @Override
            public QtAccountReq getRequest() {
                return new QtAccountReq();
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
                return "分页查询其他账号失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<QtAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                QtAccountReq qtAccountReq = (QtAccountReq) handelEntity.getRequest();
                QtAccount qtAccount = qtAccountService.queryByQtAccountReq(qtAccountReq);
                if(qtAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(qtAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist("QT账号"));
                }
            }
            @Override
            public QtAccountReq getRequest() {
                return new QtAccountReq();
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
                return "根据条件查询其他账号详情失败";
            }
        });
    }

    @Override
    public BaseResponse update(AccountReq req) throws ServiceException {
        return apiHelper.invoke(req.getData(),new Handle<QtAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                QtAccountReq qtAccountReq = (QtAccountReq) handelEntity.getRequest();
                qtAccountReq.checkUpdate();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.UPDATE, req.getAccountType(),qtAccountReq.getQtName(),20,1));
                if(qtAccountService.update(qtAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public QtAccountReq getRequest() {
                return new QtAccountReq();
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
                return "其他账号修改失败";
            }
        });
    }

    @Override
    public BaseResponse delete(String accNo) throws ServiceException {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                int num = qtAccountService.delete(accNo);
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
                return "其他账号删除失败";
            }
        });

    }
}
