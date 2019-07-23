package com.longfor.gf.lc.account.dao.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 业务账号信息
 * @author jiangdan
 */
@Data
@Table(name = "t_sequence")
public class Sequence {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "key_id")
    private Integer keyId;
    @Column(name = "seq_code")
    private String seqCode;
    @Column(name = "seq_name")
    private String seqName;
    @Column(name = "cur_value")
    private Integer curValue;
    @Column(name = "incr_value")
    private Integer incrValue;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "modify_user")
    private String modifyUser;
}
