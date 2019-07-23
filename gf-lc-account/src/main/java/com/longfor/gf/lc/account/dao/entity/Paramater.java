package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_paramater")
public class Paramater {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "param_id")
    private Integer paramId;
    @Column(name = "param_name")
    private String paramName;
    @Column(name = "param_value")
    private String paramValue;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "remark")
    private String remark;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "is_delete")
    private Integer delete;

}