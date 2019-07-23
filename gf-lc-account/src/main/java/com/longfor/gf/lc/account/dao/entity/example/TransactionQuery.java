package com.longfor.gf.lc.account.dao.entity.example;

import com.longfor.gf.lc.account.dao.entity.Transaction;
import lombok.Data;

import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/6/17 18:20:33
 */
@Data
public class TransactionQuery extends Transaction {

    /** 交易方式 */
    private Integer transWay;
    /** 开始日期 */
    private String startDate;
    /** 结束日期 */
    private String endDate;
    /** 用户账号 */
    private String accNo;
    /** 操作人 */
    private List<String> accOuts;


}
