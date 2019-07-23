package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 余额清零激励账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_zero_jl_account")
public class ZeroJlAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "person_ad")
    private String personAd;

}