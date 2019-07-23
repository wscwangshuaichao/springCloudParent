package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 成本账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_cb_account")
public class CbAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "acc_name")
    private String accName;

}