package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/4/25 15:05:50
 */
public class MdgDataReq<T> {

    @JSONField(name = "SystemID")
    private String systemId;
    @JSONField(name = "ItemArray")
    private List<T> dataList;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
