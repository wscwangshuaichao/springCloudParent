package com.longfor.gf.lc.account.business;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dao.entity.YwAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyFactory;
import com.longfor.gf.lc.account.handle.ApiHelper;
import com.longfor.gf.lc.account.handle.Handle;
import com.longfor.gf.lc.account.handle.HandleEntity;
import com.longfor.gf.lc.account.manager.AccountBizPlugin;
import com.longfor.gf.lc.account.req.AccountReq;
import com.longfor.gf.lc.account.req.YwAccountReq;
import com.longfor.gf.lc.account.service.YwAccountService;
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
public class YwAccountBusiness implements AccountBizPlugin {

    @Resource
    private ApiHelper apiHelper;
    @Resource
    private YwAccountService ywAccountService;
    @Resource
    private BaseBusiness baseBusiness;

    @Override
    public BaseResponse save(AccountReq req) {
        return apiHelper.invoke(req.getData(),new Handle<YwAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                YwAccountReq ywAccountReq = (YwAccountReq) handelEntity.getRequest();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.SAVE, req.getAccountType(),ywAccountReq.getAccName(),20,1));
                if(ywAccountService.save(ywAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public YwAccountReq getRequest() {
                return new YwAccountReq();
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
                return "业务账号新增失败";
            }
        });
    }


    @Override
    public BaseResponse batchSave(AccountReq accountReq, int saveNum) throws ServiceException {

        return apiHelper.invoke(accountReq.getData(),new Handle<List<YwAccountReq>>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                List<YwAccountReq> reqList = accountReq.checkData(accountReq.getData(), saveNum, YwAccountReq.class);
                int num = ywAccountService.batchSave(reqList);
                if(num > 0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public List<YwAccountReq> getRequest() {
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
                return "业务账号批量新增失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccNo(String accNo) {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                YwAccount ywAccount = ywAccountService.queryByAccNo(accNo);
                if(ywAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(ywAccount, AccountDto.class));
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
                return "业务账号查询失败";
            }
        });
    }

    @Override
    public BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page) {
        return apiHelper.invoke(accountReq.getData(),new Handle<YwAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<PageInfo<AccountDto>> response = ResultUtils.getSuccess();
                YwAccountReq ywAccountReq = (YwAccountReq) handelEntity.getRequest();
                PageInfo<AccountDto> pageInfo = ywAccountService.queryPage(ywAccountReq, page);
                response.setData(pageInfo);
                handelEntity.setResponse(response);
            }
            @Override
            public YwAccountReq getRequest() {
                return new YwAccountReq();
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
                return "分页查询业务账号失败";
            }
        });
    }

    @Override
    public BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException {
        return apiHelper.invoke(accountReq.getData(),new Handle<YwAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                BaseResponse<AccountDto> response = ResultUtils.getSuccess();
                YwAccountReq ywAccountReq = (YwAccountReq) handelEntity.getRequest();
                YwAccount ywAccount = ywAccountService.queryByYwAccountReq(ywAccountReq);
                if(ywAccount != null) {
                    response.setData(MyBeanUtils.copyProperties(ywAccount, AccountDto.class));
                    handelEntity.setResponse(response);
                }else{
                    handelEntity.setResponse(ResultUtils.getNotExist("YW账号"));
                }
            }
            @Override
            public YwAccountReq getRequest() {
                return new YwAccountReq();
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
                return "根据条件查询业务账号详情失败";
            }
        });
    }

    @Override
    public BaseResponse update(AccountReq req) throws ServiceException {
        return apiHelper.invoke(req.getData(),new Handle<YwAccountReq>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                YwAccountReq ywAccountReq = (YwAccountReq) handelEntity.getRequest();
                ywAccountReq.checkUpdate();
                baseBusiness.checkFrequency(FrequencyFactory.getFrequencyEntity(FrequencyFactory.FrequencyType.UPDATE, req.getAccountType(),ywAccountReq.getAccName(),20,1));
                if(ywAccountService.update(ywAccountReq)>0){
                    handelEntity.setResponse(ResultUtils.getSuccess());
                }else{
                    handelEntity.setResponse(ResultUtils.getFail());
                }
            }
            @Override
            public YwAccountReq getRequest() {
                return new YwAccountReq();
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
                return "业务账号修改失败";
            }
        });
    }

    @Override
    public BaseResponse delete(String accNo) throws ServiceException {
        return apiHelper.invoke(accNo,new Handle<String>() {
            @Override
            public void handle(HandleEntity handelEntity) throws Exception {
                int num = ywAccountService.delete(accNo);
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
                return "业务账号删除失败";
            }
        });

    }
}
