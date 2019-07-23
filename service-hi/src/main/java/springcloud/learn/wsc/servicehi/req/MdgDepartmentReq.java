package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author guoguangxiao
 * @date 2019/4/24 17:57:08
 */
public class MdgDepartmentReq implements Serializable {

    @JSONField(name = "GUID")
    private String guid;
    /** 集合ID */
    @JSONField(name = "SETID")
    private String setid;
    /** 部门ID */
    @JSONField(name = "DEPART")
    private String depart;
    /** 树节点 */
    @JSONField(name = "NODE")
    private String node;
    /** 树节点级别 */
    @JSONField(name = "LEVEL")
    private String level;
    /** 树父节点 */
    @JSONField(name = "PARNODE")
    private String parNode;
    /** 部门名称 */
    @JSONField(name = "DEPDESC")
    private String depDesc;
    /** 部门简称 */
    @JSONField(name = "DEPDESCSH")
    private String depDescSh;
    /** 编制归属 */
    @JSONField(name = "FUNCTION")
    private String function;
    /** 编制归属描述 */
    @JSONField(name = "FUN_DESC")
    private String funDesc;
    /** 部门类型 */
    @JSONField(name = "DEPT_TYPE")
    private String deptType;
    /** 部门类型2 */
    @JSONField(name = "UCDEPTTYP")
    private String ucDeptTyp;
    /** 部门类别 */
    @JSONField(name = "DEPT_CAT")
    private String deptCat;
    /** 公司 */
    @JSONField(name = "COMPANY")
    private String company;
    /** 公司描述 */
    @JSONField(name = "COMP_DESC")
    private String compDesc;
    /** 上次更新日期时间 */
    @JSONField(name = "LASTUPDTM")
    private String lastUpDtm;
    /** 生效日期 */
    @JSONField(name = "EFFDT")
    private String effDt;
    /** 状态 */
    @JSONField(name = "DEPSTATUS")
    private String depStatus;
    /** 系统标识 */
    @JSONField(name = "SYSFLG")
    private String sysFlg;
    /** 时间戳 */
    @JSONField(name = "TIMESTAMP")
    private String timestamp;
    /** 创建日期时间 */
    @JSONField(name = "CREATETM")
    private String createTm;
    /** 创建人 */
    @JSONField(name = "CREATEUSR")
    private String createUsr;
    /** 组织公司ID */
    @JSONField(name = "ORGCOMPID")
    private String orgCompId;
    /** 组织部门ID */
    @JSONField(name = "ORGDEPTID")
    private String orgDeptId;
    /** 组织三部门ID */
    @JSONField(name = "ORGTHRID")
    private String orgThrId;
    /** 更新日期时间 */
    @JSONField(name = "UPDATETM")
    private String updateTm;
    /** 更新人 */
    @JSONField(name = "UPDATEUSR")
    private String updateUsr;
    /** YN */
    @JSONField(name = "YN")
    private String yn;
    @JSONField(name = "TEXT_01")
    private String text01;
    /** 地点描述 */
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParNode() {
        return parNode;
    }

    public void setParNode(String parNode) {
        this.parNode = parNode;
    }

    public String getDepDesc() {
        return depDesc;
    }

    public void setDepDesc(String depDesc) {
        this.depDesc = depDesc;
    }

    public String getDepDescSh() {
        return depDescSh;
    }

    public void setDepDescSh(String depDescSh) {
        this.depDescSh = depDescSh;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunDesc() {
        return funDesc;
    }

    public void setFunDesc(String funDesc) {
        this.funDesc = funDesc;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getUcDeptTyp() {
        return ucDeptTyp;
    }

    public void setUcDeptTyp(String ucDeptTyp) {
        this.ucDeptTyp = ucDeptTyp;
    }

    public String getDeptCat() {
        return deptCat;
    }

    public void setDeptCat(String deptCat) {
        this.deptCat = deptCat;
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

    public String getDepStatus() {
        return depStatus;
    }

    public void setDepStatus(String depStatus) {
        this.depStatus = depStatus;
    }

    public String getSysFlg() {
        return sysFlg;
    }

    public void setSysFlg(String sysFlg) {
        this.sysFlg = sysFlg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getOrgCompId() {
        return orgCompId;
    }

    public void setOrgCompId(String orgCompId) {
        this.orgCompId = orgCompId;
    }

    public String getOrgDeptId() {
        return orgDeptId;
    }

    public void setOrgDeptId(String orgDeptId) {
        this.orgDeptId = orgDeptId;
    }

    public String getOrgThrId() {
        return orgThrId;
    }

    public void setOrgThrId(String orgThrId) {
        this.orgThrId = orgThrId;
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
