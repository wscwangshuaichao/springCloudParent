package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author guoguangxiao
 * @date 2019/4/24 17:57:26
 */
public class MdgJobReq implements Serializable {

    /** 职务代码 */
    @JSONField(name = "JOB")
    private String job;
    /** 职务名称 */
    @JSONField(name = "JOBDESC")
    private String jobDesc;
    /** 职务简称 */
    @JSONField(name = "JOBDESH")
    private String jobDesh;
    /** 职务层级 */
    @JSONField(name = "MANAGLVL")
    private String managLvl;
    /** 职务层级描述 */
    @JSONField(name = "MAGLVLDES")
    private String magLvlDes;
    /** 职能 */
    @JSONField(name = "JOBFUN")
    private String jobFun;
    /** 职能描述 */
    @JSONField(name = "JOBFUNDES")
    private String jobFunDes;
    /** 职能专业 */
    @JSONField(name = "JOBSUBFUN")
    private String jobSubFun;
    /** 职能专业描述 */
    @JSONField(name = "JOBSUBDEC")
    private String jobSubDec;
    /** 上次更新日期时间 */
    @JSONField(name = "LASTUPDTM")
    private String lastUpDtm;
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


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobDesh() {
        return jobDesh;
    }

    public void setJobDesh(String jobDesh) {
        this.jobDesh = jobDesh;
    }

    public String getManagLvl() {
        return managLvl;
    }

    public void setManagLvl(String managLvl) {
        this.managLvl = managLvl;
    }

    public String getMagLvlDes() {
        return magLvlDes;
    }

    public void setMagLvlDes(String magLvlDes) {
        this.magLvlDes = magLvlDes;
    }

    public String getJobFun() {
        return jobFun;
    }

    public void setJobFun(String jobFun) {
        this.jobFun = jobFun;
    }

    public String getJobFunDes() {
        return jobFunDes;
    }

    public void setJobFunDes(String jobFunDes) {
        this.jobFunDes = jobFunDes;
    }

    public String getJobSubFun() {
        return jobSubFun;
    }

    public void setJobSubFun(String jobSubFun) {
        this.jobSubFun = jobSubFun;
    }

    public String getJobSubDec() {
        return jobSubDec;
    }

    public void setJobSubDec(String jobSubDec) {
        this.jobSubDec = jobSubDec;
    }

    public String getLastUpDtm() {
        return lastUpDtm;
    }

    public void setLastUpDtm(String lastUpDtm) {
        this.lastUpDtm = lastUpDtm;
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
