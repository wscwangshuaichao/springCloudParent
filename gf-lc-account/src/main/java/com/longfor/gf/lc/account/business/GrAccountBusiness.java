package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.AccountBizPlugin;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.req.GrAccountReq;
import com.longfor.gf.lc.account.service.GrAccountService;
import com.longfor.gf.lc.account.util.MyBeanUtils;
import com.longfor.gf.lc.account.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangdan
 * @date 2019/5/17 09:55:19
 */
@Service
public class GrAccountBusiness implements AccountBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private GrAccountService grAccountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    public BaseResponse save(AccountReq req) {
        return apiHelper.invoke(req.getData(),new Handle<GrAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                GrAccountReq grAccountReq = (GrAccountReq) handelEntity.getRequest();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE, req.getAccountType(),grAccountReq.getPersonAd(),20,1));
                if(grAccountService.save(grAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public GrAccountReq getRequest() {
                return new GrAccountReq();
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
                return "个人账号新增失败";
            }
        });
    }

    @Override
    public BaseResponse batchSave(AccountReq accountReq, int saveNum) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<List<GrAccountReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                if(StringUtils.isNotBlank(accountReq.getData())) {
                    List<GrAccountReq> reqList = accountReq.checkData(accountReq.getData(), saveNum, GrAccountReq.class);
                    int num = grAccountService.batchSave(reqList);
                    if(num > 0){
                        handelEntity.setResponse(ResultUtils.getSuccess());
                    }else{
                        handelEntity.setResponse(ResultUtils.getFail());
                    }
                }
            }
            @Override
            public List<GrAccountReq> getRequest() {
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
                return "个人账号批量新增失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccNo(String accNo) {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                GrAccount grAccount = grAccountService.queryByAccNo(accNo);
                if(grAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(grAccount, AccountDto.class));
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
                return "个人账号查询失败";
            }
        });
    }

    @Override
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page) {
        return apiHelper.invoke(accountReq.getData(),new Handle<GrAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<PageInfo<AccountDto>> response = ResultUtils.getSuccess();
                GrAccountReq grAccountReq = (GrAccountReq) handelEntity.getRequest();
                PageInfo<AccountDto> pageInfo = grAccountService.queryPage(grAccountReq, page);
                response.setData(pageInfo);
                handelEntity.setResponse(response);
            }
            @Override
            public GrAccountReq getRequest() {
                return new GrAccountReq();
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
                return "分页查询个人账号失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<GrAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                GrAccountReq grAccountReq = (GrAccountReq) handelEntity.getRequest();
                GrAccount grAccount = grAccountService.queryByGrAccountReq(grAccountReq);
                if(grAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(grAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist("GR账号"));
                }
            }
            @Override
            public GrAccountReq getRequest() {
                return new GrAccountReq();
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
                return "根据条件查询个人账户详情失败";
            }
        });
    }

    @Override
    public BaseResponse update(AccountReq req) throws ServiceException {
        return apiHelper.invoke(req.getData(),new Handle<GrAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                GrAccountReq grAccountReq = (GrAccountReq) handelEntity.getRequest();
                grAccountReq.checkUpdate();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.UPDATE, req.getAccountType(),grAccountReq.getPersonAd(),20,1));
                if(grAccountService.update(grAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public GrAccountReq getRequest() {
                return new GrAccountReq();
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
                return "个人账号修改失败";
            }
        });
    }

    @Override
    public BaseResponse delete(String accNo) throws ServiceException {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                int num = grAccountService.delete(accNo);
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
                return "个人账号删除失败";
            }
        });
    }

}
