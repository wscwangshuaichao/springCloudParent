package springcloud.learn.wsc.servicehi.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.longfor.gf.op.gop.mdg.dto.MdgEmployeeDetailDto;

/**
 * @author guoguangxiao
 * @date 2019/4/28 16:24:49
 */
public class MdgEmpRes {

    @JSONField(name = "code")
    private String code;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "employee")
    private MdgEmployeeDetailDto mdgEmployeeDetail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MdgEmployeeDetailDto getMdgEmployeeDetail() {
        return mdgEmployeeDetail;
    }

    public void setMdgEmployeeDetail(MdgEmployeeDetailDto mdgEmployeeDetail) {
        this.mdgEmployeeDetail = mdgEmployeeDetail;
    }
}
