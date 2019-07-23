package com.longfor.gf.lc.account.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AbstractReq;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * @author guoguangxiao
 * @date 2019/5/16 10:13:03
 */
@Slf4j
@Service
public class ApiHelper {

    public BaseResponse invoke(String data,Handle handle) {
        log.info("[RequestParameter] - [invoke] - className:[{}] - method:[{}] - body:[{}]", handle.getClassName(), handle.getMethod(), data);
        long curTime = System.currentTimeMillis();
        BaseResponse response = null;
        HandleEntity handelEntity ;
        try{
            handelEntity = initHandelEntity(data, handle);
            handle.preHandle(handelEntity);
            handle.handle(handelEntity);
            handle.postHandle(handelEntity);
            response = handelEntity.getResponse();
        }catch (ServiceException se){
            log.error("[Exception] - [invoke] - className:[{}] - method:[{}] - errorMsg:[{}]", handle.getClassName(), handle.getMethod(), handle.getErrorMsg(),se);
            response = ResultUtils.getResponse(response,se.getCode(),se.getMsg());
        }catch (Exception e){
            log.error("[Exception] - [invoke] - className:[{}] - method:[{}] - errorMsg:[{}]", handle.getClassName(), handle.getMethod(), handle.getErrorMsg(),e);
            response = ResultUtils.getException(e);
        }
        log.info("[ResponseParameter] - [invoke] - className:[{}] - method:[{}] - response:[{}] - duration:[{}]",
                handle.getClassName(), handle.getMethod(), getResponseLog(response), System.currentTimeMillis()-curTime);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    public BaseResponse invokeInTransaction(String data,Handle handle) throws Exception{
        log.info("[RequestParameter] - [invokeInTransaction] - className:[{}] - method:[{}] - body:[{}]", handle.getClassName(), handle.getMethod(), data);
        long curTime = System.currentTimeMillis();
        HandleEntity handelEntity ;
        handelEntity = initHandelEntity(data, handle);
        handle.preHandle(handelEntity);
        handle.handle(handelEntity);
        handle.postHandle(handelEntity);
        BaseResponse response = handelEntity.getResponse();
        log.info("[ResponseParameter] - [invokeInTransaction] - className:[{}] - method:[{}] - response:[{}] - duration:[{}]",
                handle.getClassName(), handle.getMethod(), getResponseLog(response), System.currentTimeMillis()-curTime);
        return response;
    }


    private HandleEntity initHandelEntity(String data, Handle handle) throws ServiceException {
        HandleEntity handleEntity = null;
        Object request = handle.getRequest();
        try{
            if (request instanceof String) {
                handleEntity = new HandleEntity(data);
            } else if ((request instanceof List) && StringUtils.isNotEmpty(data)) {
                List requestList = JSON.parseArray(data, getHandleRequestClass(handle));
                handleEntity = new HandleEntity(requestList);
            } else if ((request instanceof AbstractReq) && StringUtils.isNotEmpty(data)) {
                AbstractReq requestObj = (AbstractReq) JSON.parseObject(data, handle.getClassType());
                handleEntity = new HandleEntity(requestObj);
            }
        }catch (Exception e){
            log.error("[Exception] - [ApiHelper] - [initHandelEntity] - data:[{}]", data,e);
            throw new ServiceException(CodeEnum.BizCode.PARAM_FORMAT_ERROR);
        }

        if (handleEntity == null) {
            handleEntity = new HandleEntity();
        }
        return handleEntity;
    }

    /**
     * Method: 获取list实际类型
     * Description:
     * Author guoguangxiao
     * Data 2019/5/22 14:07
     * @param
     * @return
     */
    private Class getHandleRequestClass(Handle handle){
        try{
            Method getRequest = handle.getClass().getMethod("getRequest", null);
            Type genericReturnType = getRequest.getGenericReturnType();
            if(genericReturnType instanceof ParameterizedType){
                ParameterizedType type = (ParameterizedType) genericReturnType;
                Type[] typeArguments = type.getActualTypeArguments();
                return (Class) typeArguments[0];
            }
        }catch (Exception e){
            log.error("[ResponseParameter] - className:[ApiHelper] - method:[getHandleRequestClass] - errormsg:[{}]",
                    e.getMessage(), e);

        }
        return handle.getRequest().getClass();
    }

    /**
     * Method: 打印response日志
     * Description:
     * Author guoguangxiao
     * Data 2019/6/17 16:15
     * @param
     * @return
     */
    private String getResponseLog(BaseResponse baseResponse){
        if(baseResponse==null || baseResponse.getData()==null){
            return null;
        }
        String resLog ;
        Object data = baseResponse.getData();
        if(data instanceof PageInfo && ((PageInfo) data).getList()!=null){
            resLog = baseResponse.getCode()+"||"+baseResponse.getMessage()+"||"+((PageInfo) data).getList().size();
        }else if(data instanceof List && CollectionUtils.isNotEmpty((List)data)){
            resLog = baseResponse.getCode()+"||"+baseResponse.getMessage()+"||"+((List) data).size();
        }else{
            resLog = JSONObject.toJSONString(baseResponse);
        }
        return resLog;
    }

}
