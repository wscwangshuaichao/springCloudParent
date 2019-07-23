package springcloud.learn.wsc.servicehi.dto;

import com.longfor.gf.op.gop.dto.DeptDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangshuaichao
 * @ClassName: EmployeeDto
 * @Decription TOO
 * @Date 2019/5/28 11:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

    private Integer keyId;

    private String guid;

    private String employee;

    private String oaAccount;

    private String accNo;

    private String probDt;

    private String name1;

    private String birthdate;

    private String empStas;

    private String grade;

    private Date createDateTime;

    private Date modifyDateTime;

    /** deptlist一个人可能属于多个部门 */
    private List<DeptDto> deptlist;

    /** deptstr一个人可能属于多个部门 */
    private String deptstr;

    /**同步关爱通人员增加字段*/
    private String sex;

    private String email;

    private String phone1;

    /** 最近一次入职时间 */
    private String hireDt;

    /** 第一次入职时间 */
    private String orgHrDt;

    private String idType;

    private String id;

    private String actionChgFlag;


}
