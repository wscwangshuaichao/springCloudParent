package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.Transaction;
import com.longfor.gf.lc.account.dao.entity.example.TransactionQuery;
import com.longfor.gf.lc.account.dto.AccTransactionDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TransactionMapper extends LFMySQLMapper<Transaction> {

    /**
     * @description 根据条件查询分页数据
     * @author jiangdan
     * @date 2019/5/22 17:36
     * @param[req]
     * @return java.util.List<com.longfor.gf.lc.account.dto.TransactionDto>
     */
    List<TransactionDto> queryPage(TransactionQuery req);
    /**
     * @description 查询分页总数
     * @author jiangdan
     * @date 2019/5/22 17:36
     * @param[req]
     * @return long
     */
    long queryPageCount(TransactionQuery req);

    /**
     * @description 查询用户转账成功的总收入
     * @author jiangdan
     * @date 2019/5/22 18:55
     * @param[req]
     * @return java.math.BigDecimal
     */
    BigDecimal selectIncomeByAccNo(TransactionQuery req);
    /**
     * @description 查询用户转账成功的总支出
     * @author jiangdan
     * @date 2019/5/22 18:55
     * @param[req]
     * @return java.math.BigDecimal
     */
    BigDecimal selectExpendByAccNo(TransactionQuery req);
    /**
     * @description 根据条件查询交易明细
     * @author jiangdan
     * @date 2019/5/28 12:46
     * @param[req]
     * @return
     */
    List<TransactionDto> redPacketsByAccNo(TransactionQuery req);
    /**
     * @description h5个人收支明细
     * @author jiangdan
     * @date 2019/5/30 17:39
     * @param[req]
     * @return java.util.List<com.longfor.gf.lc.account.dto.AccTransactionDto>
     */
    List<AccTransactionDto> queryH5TransPage(TransactionQuery req);
    long queryH5TransPageCount(TransactionQuery req);

    /**
     * @description h5个人总收支
     * @author jiangdan
     * @date 2019/6/18 15:54
     * @param[req]
     * @return java.math.BigDecimal
     */
    BigDecimal queryUserIncome(TransactionQuery req);
    BigDecimal queryUserExpend(TransactionQuery req);
    /**
     * @description 根据条件查询交易记录最新一条信息
     * @author jiangdan
     * @date 2019/5/30 18:32
     * @param[batchNo, businessType, accNo]
     * @return com.longfor.gf.lc.account.dto.AccTransactionDto
     */
    AccTransactionDto queryTransByBnoAndBtypeAndAccNo(@Param("batchNo")String batchNo,@Param("accNo")String accNo,
       @Param("transWay") Integer transWay, @Param("businessType")String businessType);
    /**
     * @description 根据批次号查询抢红包的人
     * @author jiangdan
     * @date 2019/5/31 16:07
     * @param[batchNo]
     * @return java.lang.String
     */
    String queryAccInByBnoAndBtype(@Param("batchNo")String batchNo);
    /**
     * @description 查询已抢红包总金额
     * @author jiangdan
     * @date 2019/5/31 16:19
     * @param[batchNo, accNo]
     * @return java.math.BigDecimal
     */
    BigDecimal selectGrabAmountByAccNo(@Param("batchNo")String batchNo,@Param("accNo")String accNo);

    /**
     * @description 查询用户转账成功的交易记录
     * @author jiangdan
     * @date 2019/6/18 15:33
     * @param[req]
     * @return java.util.List<com.longfor.gf.lc.account.dto.TransactionDto>
     */
    List<TransactionDto> selectH5TransPage(TransactionQuery req);

}