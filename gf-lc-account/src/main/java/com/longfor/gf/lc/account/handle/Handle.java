package com.longfor.gf.lc.account.handle;

import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AbstractReq;

import java.lang.reflect.ParameterizedType;

/**
 * @author guoguangxiao
 * @date 2019/5/16 10:15:21
 */
public abstract class Handle<T> {

    /**
     * Method: 前置处理
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 10:41
     * @param handelEntity
     * @return
     */
    public void preHandle(HandleEntity handelEntity) throws ServiceException {
        Object obj = handelEntity.getRequest();
        if(obj instanceof AbstractReq){
            AbstractReq request = (AbstractReq)obj;
            request.check();
        }
    }
    /**
     * Method: 后置处理
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 10:41
     * @param handelEntity
     * @return
     */
    public void postHandle(HandleEntity handelEntity){
    }

    /**
     * Method: 调用真是业务逻辑
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 10:41
     * @param handelEntity
     * @return HandleEntity
     */
    public abstract void handle(HandleEntity handelEntity) throws Exception;

    public abstract T getRequest();

    public abstract String getClassName();

    public abstract String getMethod();

    public String getErrorMsg(){
        return null;
    }

    public Class getClassType(){
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }


}
