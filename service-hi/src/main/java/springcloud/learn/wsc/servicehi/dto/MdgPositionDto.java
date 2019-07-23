package springcloud.learn.wsc.servicehi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MdgPositionDto {

    private Integer keyId;

    private String postion;

    private String refPost;

    private String deptDesc;

    private String postDesc;

    private String postDesh;

    private String busUnit;

    private String busUnDes;

    private String company;

    private String compDesc;

    private String location;

    private String loctDesc;

    private String refJob;

    private String jobDesc;

    private String managLvl;

    private String jobLvlDes;

    private Integer maxHc;

    private String keypost;

    private String reportTo;

    private String rptDotted;

    private String lastUpDt;

    private String effDt;

    private String status;

    private String sysFlg;

    private Date createTm;

    private String createUsr;

    private Date updateTm;

    private String updateUsr;

    private String yn;

}