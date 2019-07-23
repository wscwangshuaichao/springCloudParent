package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 个人账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_gr_account")
public class GrAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "person_ad")
    private String personAd;
    @Column(name = "balance_amt")
    private BigDecimal balanceAmt;
    @Column(name = "frozen_amt")
    private BigDecimal frozenAmt;

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