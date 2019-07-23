package springcloud.learn.wsc.servicehi.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/4/24 17:56:09
 */
public class MdgEmployeeReq implements Serializable {

    /** GUID */
    @JSONField(name = "GUID")
    private String guid;
    /** 员工ID */
    @JSONField(name = "EMPLOYEE")
    private String employee;
    /** OA账号 */
    @JSONField(name = "OAACCOUNT")
    private String oaAccount;
    /** 姓名 */
    @JSONField(name = "NAME1")
    private String name1;
    /** 性别 */
    @JSONField(name = "SEX")
    private String sex;
    /** 身份证号类型 */
    @JSONField(name = "IDTYPE")
    private String idType;
    /** 身份证号 */
    @JSONField(name = "ID")
    private String id;
    /** 电子邮件 */
    @JSONField(name = "EMAIL")
    private String email;
    /** 出生日期 */
    @JSONField(name = "BIRTHDATE")
    private String birthdate;
    /** 电话 1 */
    @JSONField(name = "PHONE1")
    private String phone1;
    /** 电话 2 */
    @JSONField(name = "PHONE2")
    private String phone2;
    /** 职业生涯开始日期 */
    @JSONField(name = "STARTDT")
    private String startDt;
    /** 上次更新日期时间 */
    @JSONField(name = "LSTUPDTM1")
    private String lstUpDtm1;
    /** 员工记录 */
    @JSONField(name = "EMPLRCD")
    private String emplRcd;
    /** HR状态(在职/离职) */
    @JSONField(name = "EMPSTAS")
    private String empStas;
    /** 招聘渠道 */
    @JSONField(name = "HIRECHNL")
    private String hireChnl;
    /** 招聘渠道描述 */
    @JSONField(name = "HICHNLDES")
    private String hiChnlDes;
    /** 地产标识 */
    @JSONField(name = "DCFLG")
    private String dcFlg;
    /** 员工类别 */
    @JSONField(name = "EMPCLS")
    private String empCls;
    /** 员工类别描述 */
    @JSONField(name = "EMPCLSDES")
    private String empClsDes;
    /** 职级 */
    @JSONField(name = "GRADE")
    private String grade;
    /** 职级描述 */
    @JSONField(name = "GRDDESC")
    private String grdDesc;
    /** 职级序列 */
    @JSONField(name = "GRDFAML")
    private String grdFaml;
    /** 职级序列描述 */
    @JSONField(name = "GRDFAMDES")
    private String grdFamDes;
    /** 业务单位名称 */
    @JSONField(name = "BUSUNDESC")
    private String busUnDesc;
    /** 法人实体编码 */
    @JSONField(name = "LEGALENT")
    private String legalEnt;
    /** 法人实体名称 */
    @JSONField(name = "LEGENTDES")
    private String legEntDes;
    /** 分摊事项 */
    @JSONField(name = "FTSX")
    private String ftsx;
    /** 费用类型描述 */
    @JSONField(name = "FYDESC")
    private String fyDesc;
    /** 内部商务头衔 */
    @JSONField(name = "BUSTITILE")
    private String busTitile;
    /** 工作开始日期 */
    @JSONField(name = "HIREDT")
    private String hireDt;
    /** 毕业时间 */
    @JSONField(name = "GRADDT")
    private String gradDt;
    /** 离职日期 */
    @JSONField(name = "TERMIDT")
    private String termiDt;
    /** 转正日期 */
    @JSONField(name = "ZZDT")
    private String zzDt;
    /** 试用日期 */
    @JSONField(name = "PROBDT")
    private String probDt;
    /** 是否多人评估 */
    @JSONField(name = "IFMUTEVA")
    private String ifMuteVa;
    /** 实际工作地代码 */
    @JSONField(name = "REALLOC")
    private String realLoc;
    /** 实际工作地描述 */
    @JSONField(name = "REALOCDES")
    private String reaLocDes;
    /** NC编码 */
    @JSONField(name = "NCCODE")
    private String ncCode;
    /** NC编码描述 */
    @JSONField(name = "NCDESC")
    private String ncDesc;
    /** 上次更新日期时间 */
    @JSONField(name = "LSTUPDTM2")
    private String lstUpDtm2;
    /** 初次雇用开始日期 */
    @JSONField(name = "ORGHRDT")
    private String orgHrDt;
    /** 第一引导人 */
    @JSONField(name = "REFEMP")
    private String refeMp;
    /** 第一引导人姓名 */
    @JSONField(name = "REFEMPNAM")
    private String refeMpNam;
    /** 支付规则 */
    @JSONField(name = "PAYROLE")
    private String payRole;
    /** 支付规则编码 */
    @JSONField(name = "PAYROLCOD")
    private String payRolCod;
    /** 探亲地名称 */
    @JSONField(name = "HOMETOWN")
    private String homeTown;
    /** 探亲地代码 */
    @JSONField(name = "HMTOWNCOD")
    private String hmTownCod;
    /** 是否计算离职率代码 */
    @JSONField(name = "CALDISCOD")
    private String calDisCod;
    /** 婚姻状况描述 */
    @JSONField(name = "MARSTADES")
    private String marStaDes;
    /** 婚姻状况 */
    @JSONField(name = "MARSTATUS")
    private String marStatus;
    /** 结婚日期 */
    @JSONField(name = "MARDATE")
    private String marDate;
    /** 入职类型 */
    @JSONField(name = "ONBDTYPE")
    private String onbdType;
    /** 系统标识 */
    @JSONField(name = "SYSFLG")
    private String sysFlg;
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
    /** 组织小部门ID */
    @JSONField(name = "ORGMINID")
    private String orgMinId;
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

    /** 部门ID */
    @JSONField(name = "DEPTID")
    private String deptid;
    /** 职位编码 */
    @JSONField(name = "POSTION")
    private String postion;
    /** 职务代码 */
    @JSONField(name = "JOBCODE")
    private String jobcode;

    /** YN */
    @JSONField(name = "EmpPosArray")
    private List<MdgEmployeeAssistReq> mdgEmployeeAssistReqList;

    /** 是否高管 */
    @JSONField(name = "IF_GG")
    private String ifGg;
    /** 地点ID */
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getOaAccount() {
        return oaAccount;
    }

    public void setOaAccount(String oaAccount) {
        this.oaAccount = oaAccount;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getLstUpDtm1() {
        return lstUpDtm1;
    }

    public void setLstUpDtm1(String lstUpDtm1) {
        this.lstUpDtm1 = lstUpDtm1;
    }

    public String getEmplRcd() {
        return emplRcd;
    }

    public void setEmplRcd(String emplRcd) {
        this.emplRcd = emplRcd;
    }

    public String getEmpStas() {
        return empStas;
    }

    public void setEmpStas(String empStas) {
        this.empStas = empStas;
    }

    public String getHireChnl() {
        return hireChnl;
    }

    public void setHireChnl(String hireChnl) {
        this.hireChnl = hireChnl;
    }

    public String getHiChnlDes() {
        return hiChnlDes;
    }

    public void setHiChnlDes(String hiChnlDes) {
        this.hiChnlDes = hiChnlDes;
    }

    public String getDcFlg() {
        return dcFlg;
    }

    public void setDcFlg(String dcFlg) {
        this.dcFlg = dcFlg;
    }

    public String getEmpCls() {
        return empCls;
    }

    public void setEmpCls(String empCls) {
        this.empCls = empCls;
    }

    public String getEmpClsDes() {
        return empClsDes;
    }

    public void setEmpClsDes(String empClsDes) {
        this.empClsDes = empClsDes;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrdDesc() {
        return grdDesc;
    }

    public void setGrdDesc(String grdDesc) {
        this.grdDesc = grdDesc;
    }

    public String getGrdFaml() {
        return grdFaml;
    }

    public void setGrdFaml(String grdFaml) {
        this.grdFaml = grdFaml;
    }

    public String getGrdFamDes() {
        return grdFamDes;
    }

    public void setGrdFamDes(String grdFamDes) {
        this.grdFamDes = grdFamDes;
    }

    public String getBusUnDesc() {
        return busUnDesc;
    }

    public void setBusUnDesc(String busUnDesc) {
        this.busUnDesc = busUnDesc;
    }

    public String getLegalEnt() {
        return legalEnt;
    }

    public void setLegalEnt(String legalEnt) {
        this.legalEnt = legalEnt;
    }

    public String getLegEntDes() {
        return legEntDes;
    }

    public void setLegEntDes(String legEntDes) {
        this.legEntDes = legEntDes;
    }

    public String getFtsx() {
        return ftsx;
    }

    public void setFtsx(String ftsx) {
        this.ftsx = ftsx;
    }

    public String getFyDesc() {
        return fyDesc;
    }

    public void setFyDesc(String fyDesc) {
        this.fyDesc = fyDesc;
    }

    public String getBusTitile() {
        return busTitile;
    }

    public void setBusTitile(String busTitile) {
        this.busTitile = busTitile;
    }

    public String getHireDt() {
        return hireDt;
    }

    public void setHireDt(String hireDt) {
        this.hireDt = hireDt;
    }

    public String getGradDt() {
        return gradDt;
    }

    public void setGradDt(String gradDt) {
        this.gradDt = gradDt;
    }

    public String getTermiDt() {
        return termiDt;
    }

    public void setTermiDt(String termiDt) {
        this.termiDt = termiDt;
    }

    public String getZzDt() {
        return zzDt;
    }

    public void setZzDt(String zzDt) {
        this.zzDt = zzDt;
    }

    public String getProbDt() {
        return probDt;
    }

    public void setProbDt(String probDt) {
        this.probDt = probDt;
    }

    public String getIfMuteVa() {
        return ifMuteVa;
    }

    public void setIfMuteVa(String ifMuteVa) {
        this.ifMuteVa = ifMuteVa;
    }

    public String getRealLoc() {
        return realLoc;
    }

    public void setRealLoc(String realLoc) {
        this.realLoc = realLoc;
    }

    public String getReaLocDes() {
        return reaLocDes;
    }

    public void setReaLocDes(String reaLocDes) {
        this.reaLocDes = reaLocDes;
    }

    public String getNcCode() {
        return ncCode;
    }

    public void setNcCode(String ncCode) {
        this.ncCode = ncCode;
    }

    public String getNcDesc() {
        return ncDesc;
    }

    public void setNcDesc(String ncDesc) {
        this.ncDesc = ncDesc;
    }

    public String getLstUpDtm2() {
        return lstUpDtm2;
    }

    public void setLstUpDtm2(String lstUpDtm2) {
        this.lstUpDtm2 = lstUpDtm2;
    }

    public String getOrgHrDt() {
        return orgHrDt;
    }

    public void setOrgHrDt(String orgHrDt) {
        this.orgHrDt = orgHrDt;
    }

    public String getRefeMp() {
        return refeMp;
    }

    public void setRefeMp(String refeMp) {
        this.refeMp = refeMp;
    }

    public String getRefeMpNam() {
        return refeMpNam;
    }

    public void setRefeMpNam(String refeMpNam) {
        this.refeMpNam = refeMpNam;
    }

    public String getPayRole() {
        return payRole;
    }

    public void setPayRole(String payRole) {
        this.payRole = payRole;
    }

    public String getPayRolCod() {
        return payRolCod;
    }

    public void setPayRolCod(String payRolCod) {
        this.payRolCod = payRolCod;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getHmTownCod() {
        return hmTownCod;
    }

    public void setHmTownCod(String hmTownCod) {
        this.hmTownCod = hmTownCod;
    }

    public String getCalDisCod() {
        return calDisCod;
    }

    public void setCalDisCod(String calDisCod) {
        this.calDisCod = calDisCod;
    }

    public String getMarStaDes() {
        return marStaDes;
    }

    public void setMarStaDes(String marStaDes) {
        this.marStaDes = marStaDes;
    }

    public String getMarStatus() {
        return marStatus;
    }

    public void setMarStatus(String marStatus) {
        this.marStatus = marStatus;
    }

    public String getMarDate() {
        return marDate;
    }

    public void setMarDate(String marDate) {
        this.marDate = marDate;
    }

    public String getOnbdType() {
        return onbdType;
    }

    public void setOnbdType(String onbdType) {
        this.onbdType = onbdType;
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

    public String getOrgMinId() {
        return orgMinId;
    }

    public void setOrgMinId(String orgMinId) {
        this.orgMinId = orgMinId;
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

    public List<MdgEmployeeAssistReq> getMdgEmployeeAssistReqList() {
        return mdgEmployeeAssistReqList;
    }

    public void setMdgEmployeeAssistReqList(List<MdgEmployeeAssistReq> mdgEmployeeAssistReqList) {
        this.mdgEmployeeAssistReqList = mdgEmployeeAssistReqList;
    }

    public String getIfGg() {
        return ifGg;
    }

    public void setIfGg(String ifGg) {
        this.ifGg = ifGg;
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
