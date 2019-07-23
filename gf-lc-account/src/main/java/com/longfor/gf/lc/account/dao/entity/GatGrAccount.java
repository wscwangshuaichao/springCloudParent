package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 关爱通个人账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_gat_gr_account")
public class GatGrAccount {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "person_ad")
    private String personAd;
    @Column(name = "job_no")
    private String jobNo;
    private Integer status;

}