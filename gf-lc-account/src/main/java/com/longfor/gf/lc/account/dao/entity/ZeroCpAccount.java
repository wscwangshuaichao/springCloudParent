package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 余额清零产品账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_zero_cp_account")
public class ZeroCpAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "cp_code")
    private String cpCode;

}