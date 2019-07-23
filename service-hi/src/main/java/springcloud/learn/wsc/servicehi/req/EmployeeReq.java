package springcloud.learn.wsc.servicehi.req;

import com.longfor.gf.op.gop.req.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangshuaichao
 * @ClassName: EmployeeReq
 * @Decription TOO
 * @Date 2019/5/28 11:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReq  extends BaseReq {

    /** keyId key_id */
    private String keyId;

    /** 用户员工编号 employee */
    private String employee;

    /** os账户 OaAccount */
    private String usercode;

    /** 用户真实姓名 name1 */
    private String realName;

    /** 更新时间 */
    private Date modifyDateTime;



}
