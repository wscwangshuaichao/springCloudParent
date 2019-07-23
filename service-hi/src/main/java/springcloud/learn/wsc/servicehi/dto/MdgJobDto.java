package springcloud.learn.wsc.servicehi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MdgJobDto {

    private Integer keyId;

    private String job;

    private String jobDesc;

    private String jobDesh;

    private String managLvl;

    private String magLvlDes;

    private String jobFun;

    private String jobFunDes;

    private String jobSubFun;

    private String jobSubDec;

    private String lastUpDtm;

    private String effDt;

    private String status;

    private String sysFlg;

    private Date createTm;

    private String createUsr;

    private Date updateTm;

    private String updateUsr;

    private String yn;

}