package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author guoguangxiao
 * @date 2019/4/28 14:03:42
 */
public class MdgEmployeeAssistReq {

    /** 部门ID */
    @JSONField(name = "DEPTID")
    private String deptid;
    /** 职位编码 */
    @JSONField(name = "POSTION")
    private String postion;
    /** 职务代码 */
    @JSONField(name = "JOBCODE")
    private String jobcode;

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }
}
