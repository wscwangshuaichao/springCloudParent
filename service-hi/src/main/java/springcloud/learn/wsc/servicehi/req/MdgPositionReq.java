package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author guoguangxiao
 * @date 2019/4/24 17:57:54
 */
public class MdgPositionReq implements Serializable {

    /** 职位代码 */
    @JSONField(name = "POSTION")
    private String postion;
    /** 部门 ID */
    @JSONField(name = "REF_POST")
    private String refPost;
    /** 部门名称 */
    @JSONField(name = "DEPTDESC")
    private String deptDesc;
    /** 职位名称 */
    @JSONField(name = "POSTDESC")
    private String postDesc;
    /** 职位简称 */
    @JSONField(name = "POSTDESH")
    private String postDesh;
    /** 业务单位 */
    @JSONField(name = "BUSUNIT")
    private String busUnit;
    /** 业务单位描述 */
    @JSONField(name = "BUSUNDES")
    private String busUnDes;
    /** 公司 */
    @JSONField(name = "COMPANY")
    private String company;
    /** 公司描述 */
    @JSONField(name = "COMPDESC")
    private String compDesc;
    /** 地点 */
    @JSONField(name = "LOCATION")
    private String location;
    /** 地点描述 */
    @JSONField(name = "LOCTDESC")
    private String loctDesc;
    /** 职务代码 */
    @JSONField(name = "REF_JOB")
    private String refJob;
    /** 职务名称 */
    @JSONField(name = "JOBDESC")
    private String jobDesc;
    /** 职务层级 */
    @JSONField(name = "MANAGLVL")
    private String managLvl;
    /** 职务级别描述 */
    @JSONField(name = "JOBLVLDES")
    private String jobLvlDes;
    /** 人数上限 */
    @JSONField(name = "MAXHC")
    private Integer maxHc;
    /** 重要职位 */
    @JSONField(name = "KEYPOST")
    private String keypost;
    /** 实线汇报岗位 */
    @JSONField(name = "REPORTTO")
    private String reportTo;
    /** 虚线汇报岗位 */
    @JSONField(name = "RPTDOTTED")
    private String rptDotted;
    /** 上次更新日期时间 */
    @JSONField(name = "LASTUPDT")
    private String lastUpDt;
    /** 生效日期 */
    @JSONField(name = "EFFDT")
    private String effDt;
    /** 状态 */
    @JSONField(name = "STATUS")
    private String status;
    /** 系统标识 */
    @JSONField(name = "SYSFLG")
    private String sysFlg;
    /** 创建日期时间 */
    @JSONField(name = "CREATETM")
    private String createTm;
    /** 创建人 */
    @JSONField(name = "CREATEUSR")
    private String createUsr;
    /** 更新日期时间 */
    @JSONField(name = "UPDATETM")
    private String updateTm;
    /** 更新人 */
    @JSONField(name = "UPDATEUSR")
    private String updateUsr;
    /** YN */
    @JSONField(name = "YN")
    private String yn;
    /** 预留字段 */
    @JSONField(name = "TEXT_01")
    private String text01;
    /** 预留字段 */
    @JSONField(name = "TEXT_02")
    private String text02;
    /** 预留字段 */
    @JSONField(name = "TEXT_03")
    private String text03;
    /** 预留字段 */
    @JSONField(name = "TEXT_04")
    private String text04;
    /** 预留字段 */
    @JSONField(name = "TEXT_05")
    private String text05;
    /** 预留字段 */
    @JSONField(name = "TEXT_06")
    private String text06;
    /** 预留字段 */
    @JSONField(name = "TEXT_07")
    private String text07;
    /** 预留字段 */
    @JSONField(name = "TEXT_08")
    private String text08;
    /** 预留字段 */
    @JSONField(name = "TEXT_09")
    private String text09;
    /** 预留字段 */
    @JSONField(name = "TEXT_10")
    private String text10;

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getRefPost() {
        return refPost;
    }

    public void setRefPost(String refPost) {
        this.refPost = refPost;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostDesh() {
        return postDesh;
    }

    public void setPostDesh(String postDesh) {
        this.postDesh = postDesh;
    }

    public String getBusUnit() {
        return busUnit;
    }

    public void setBusUnit(String busUnit) {
        this.busUnit = busUnit;
    }

    public String getBusUnDes() {
        return busUnDes;
    }

    public void setBusUnDes(String busUnDes) {
        this.busUnDes = busUnDes;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLoctDesc() {
        return loctDesc;
    }

    public void setLoctDesc(String loctDesc) {
        this.loctDesc = loctDesc;
    }

    public String getRefJob() {
        return refJob;
    }

    public void setRefJob(String refJob) {
        this.refJob = refJob;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getManagLvl() {
        return managLvl;
    }

    public void setManagLvl(String managLvl) {
        this.managLvl = managLvl;
    }

    public String getJobLvlDes() {
        return jobLvlDes;
    }

    public void setJobLvlDes(String jobLvlDes) {
        this.jobLvlDes = jobLvlDes;
    }

    public Integer getMaxHc() {
        return maxHc;
    }

    public void setMaxHc(Integer maxHc) {
        this.maxHc = maxHc;
    }

    public String getKeypost() {
        return keypost;
    }

    public void setKeypost(String keypost) {
        this.keypost = keypost;
    }

    public String getReportTo() {
        return reportTo;
    }

    public void setReportTo(String reportTo) {
        this.reportTo = reportTo;
    }

    public String getRptDotted() {
        return rptDotted;
    }

    public void setRptDotted(String rptDotted) {
        this.rptDotted = rptDotted;
    }

    public String getLastUpDt() {
        return lastUpDt;
    }

    public void setLastUpDt(String lastUpDt) {
        this.lastUpDt = lastUpDt;
    }

    public String getEffDt() {
        return effDt;
    }

    public void setEffDt(String effDt) {
        this.effDt = effDt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysFlg() {
        return sysFlg;
    }

    public void setSysFlg(String sysFlg) {
        this.sysFlg = sysFlg;
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm;
    }

    public String getCreateUsr() {
        return createUsr;
    }

    public void setCreateUsr(String createUsr) {
        this.createUsr = createUsr;
    }

    public String getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(String updateTm) {
        this.updateTm = updateTm;
    }

    public String getUpdateUsr() {
        return updateUsr;
    }

    public void setUpdateUsr(String updateUsr) {
        this.updateUsr = updateUsr;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }

    public String getText01() {
        return text01;
    }

    public void setText01(String text01) {
        this.text01 = text01;
    }

    public String getText02() {
        return text02;
    }

    public void setText02(String text02) {
        this.text02 = text02;
    }

    public String getText03() {
        return text03;
    }

    public void setText03(String text03) {
        this.text03 = text03;
    }

    public String getText04() {
        return text04;
    }

    public void setText04(String text04) {
        this.text04 = text04;
    }

    public String getText05() {
        return text05;
    }

    public void setText05(String text05) {
        this.text05 = text05;
    }

    public String getText06() {
        return text06;
    }

    public void setText06(String text06) {
        this.text06 = text06;
    }

    public String getText07() {
        return text07;
    }

    public void setText07(String text07) {
        this.text07 = text07;
    }

    public String getText08() {
        return text08;
    }

    public void setText08(String text08) {
        this.text08 = text08;
    }

    public String getText09() {
        return text09;
    }

    public void setText09(String text09) {
        this.text09 = text09;
    }

    public String getText10() {
        return text10;
    }

    public void setText10(String text10) {
        this.text10 = text10;
    }
}
