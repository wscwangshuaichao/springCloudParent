package springcloud.learn.wsc.servicehi.req;

import java.io.Serializable;

/**
 * @author guoguangxiao
 * @date 2019/4/26 17:44:06
 */
public class MdgExampleReq implements Serializable {

    private String loginName;

    /** 当前页 */
    private int num;
    /** 分页数量 */
    private int size;
    /** 数据最后更新时间（返回最后更新时间大于这个值的数据，首次同步可不传）格式 “yyyy-MM-dd hh:mm:ss” */
    private String psUpdateTime;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPsUpdateTime() {
        return psUpdateTime;
    }

    public void setPsUpdateTime(String psUpdateTime) {
        this.psUpdateTime = psUpdateTime;
    }
}
