package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "key_id")
    private Integer keyId;
    @Column(name = "batch_no")
    private String batchNo;
    @Column(name = "trans_no")
    private String transNo;
    @Column(name = "out_trans_no")
    private String outTransNo;
    @Column(name = "acc_out")
    private String accOut;
    @Column(name = "acc_out_amt")
    private BigDecimal accOutAmt;
    @Column(name = "acc_in")
    private String accIn;
    @Column(name = "acc_in_amt")
    private BigDecimal accInAmt;
    @Column(name = "business_type")
    private String businessType;
    @Column(name = "trans_type")
    private String transType;
    @Column(name = "yyyy")
    private Integer yyyy;
    @Column(name = "mm")
    private Integer mm;
    @Column(name = "dd")
    private Integer dd;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "modify_user")
    private String modifyUser;

}