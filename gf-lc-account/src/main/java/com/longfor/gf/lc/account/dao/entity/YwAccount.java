package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 业务账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_yw_account")
public class YwAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "acc_name")
    private String accName;

    private Integer status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "is_delete")
    private Integer delete;
}