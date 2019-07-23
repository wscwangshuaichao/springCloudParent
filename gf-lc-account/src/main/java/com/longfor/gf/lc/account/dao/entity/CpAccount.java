package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_cp_account")
public class CpAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "cp_code")
    private String cpCode;
    @Column(name = "cp_name")
    private String cpName;
    @Column(name = "balance_amt")
    private BigDecimal balanceAmt;
    @Column(name = "frozen_amt")
    private BigDecimal frozenAmt;
    @Column(name = "last_zero_time")
    private Date lastZeroTime;
    @Column(name = "next_zero_time")
    private Date nextZeroTime;
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