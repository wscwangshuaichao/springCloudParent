package com.longfor.gf.lc.account.handle;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.req.AbstractReq;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/16 10:15:29
 */
public class HandleEntity {


    /** 请求对象 */
    private AbstractReq request;
    /** 请求对象 */
    private List requestList;
    /** 请求对象 */
    private String requestParam;
    /** 返回对象 */
    private BaseResponse response;
    /** 调用时间 */
    private long currentTimeMillis;

    public HandleEntity() {

    }
    public HandleEntity(String requestParam) {
        this.requestParam = requestParam;
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public HandleEntity(AbstractReq request) {
        this.request = request;
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public HandleEntity(List requestList) {
        this.requestList = requestList;
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public AbstractReq getRequest() {
        return request;
    }

    public void setRequest(AbstractReq request) {
        this.request = request;
    }

    public List getRequestList() {
        return requestList;
    }

    public void setRequestList(List requestList) {
        this.requestList = requestList;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }
}
