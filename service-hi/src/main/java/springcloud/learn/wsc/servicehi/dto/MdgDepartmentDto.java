package springcloud.learn.wsc.servicehi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MdgDepartmentDto {

    private Integer keyId;

    private String guid;

    private String setid;

    private String depart;

    private String node;

    private String level;

    private String parNode;

    private String depDesc;

    private String depDescSh;

    private String function;

    private String funDesc;

    private String deptType;

    private String ucDeptTyp;

    private String deptCat;

    private String company;

    private String compDesc;

    private String lastUpDtm;

    private String effDt;

    private String depStatus;

    private String sysFlg;

    private String timestamp;

    private Date createTm;

    private String createUsr;

    private String orgCompId;

    private String orgDeptId;

    private String orgThrId;

    private Date updateTm;

    private String updateUsr;

    private String yn;

}