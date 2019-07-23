package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.CpAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.AccountBizPlugin;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.req.CpAccountReq;
import com.longfor.gf.lc.account.service.CpAccountService;
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
public class CpAccountBusiness implements AccountBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private CpAccountService cpAccountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    public BaseResponse save(AccountReq req) {
        return apiHelper.invoke(req.getData(),new Handle<CpAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                CpAccountReq cpAccountReq = (CpAccountReq) handelEntity.getRequest();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE, req.getAccountType(),cpAccountReq.getCpName(),20,1));
                if(cpAccountService.save(cpAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public CpAccountReq getRequest() {
                return new CpAccountReq();
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
        return apiHelper.invoke(accountReq.getData(),new Handle<List<CpAccountReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                if(StringUtils.isNotBlank(accountReq.getData())) {
                    List<CpAccountReq> reqList = accountReq.checkData(accountReq.getData(), saveNum, CpAccountReq.class);
                    int num = cpAccountService.batchSave(reqList);
                    if(num > 0){
                        handelEntity.setResponse(ResultUtils.getSuccess());
                    }else{
                        handelEntity.setResponse(ResultUtils.getFail());
                    }
                }
            }
            @Override
            public List<CpAccountReq> getRequest() {
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
                return "产品账号批量新增失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccNo(String accNo) {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                CpAccount cpAccount = cpAccountService.queryByAccNo(accNo);
                if(cpAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(cpAccount, AccountDto.class));
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
                return "产品账号查询失败";
            }
        });
    }

    @Override
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page) {
        return apiHelper.invoke(accountReq.getData(),new Handle<CpAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<PageInfo<AccountDto>> response = ResultUtils.getSuccess();
                CpAccountReq cpAccountReq = (CpAccountReq) handelEntity.getRequest();
                PageInfo<AccountDto> pageInfo = cpAccountService.queryPage(cpAccountReq, page);
                response.setData(pageInfo);
                handelEntity.setResponse(response);
            }
            @Override
            public CpAccountReq getRequest() {
                return new CpAccountReq();
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
                return "分页查询产品账号失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<CpAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                CpAccountReq cpAccountReq = (CpAccountReq) handelEntity.getRequest();
                CpAccount cpAccount = cpAccountService.queryByCpAccountReq(cpAccountReq);
                if(cpAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(cpAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist("CP账号"));
                }
            }
            @Override
            public CpAccountReq getRequest() {
                return new CpAccountReq();
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
                return "根据条件查询产品账户详情失败";
            }
        });
    }

    @Override
    public BaseResponse update(AccountReq req) throws ServiceException {
        return apiHelper.invoke(req.getData(),new Handle<CpAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                CpAccountReq cpAccountReq = (CpAccountReq) handelEntity.getRequest();
                cpAccountReq.checkUpdate();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.UPDATE, req.getAccountType(),cpAccountReq.getCpName(),20,1));
                if(cpAccountService.update(cpAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public CpAccountReq getRequest() {
                return new CpAccountReq();
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
                return "产品账号修改失败";
            }
        });
    }

    @Override
    public BaseResponse delete(String accNo) throws ServiceException {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                int num = cpAccountService.delete(accNo);
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
                return "产品账号删除失败";
            }
        });
    }

}
