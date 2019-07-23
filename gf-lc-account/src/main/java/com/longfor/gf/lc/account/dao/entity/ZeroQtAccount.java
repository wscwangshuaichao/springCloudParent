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
@Table(name = "t_zero_qt_account")
public class ZeroQtAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "qt_code")
    private String qtCode;

}