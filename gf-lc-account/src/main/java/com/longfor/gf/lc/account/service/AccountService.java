package com.longfor.gf.lc.account.service;

import com.longfor.gf.lc.account.dao.entity.GatGrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountInfoReq;
import com.longfor.gf.lc.account.req.TransactionReq;

import java.math.BigDecimal;

/**
 * @Author jiangdan
 * @Date 2019/5/24 9:49
 **/
public interface AccountService {

    /**
     * @description 根据主键编号、类型查询账号余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:29
     * @param[accNo, accType]
     * @return com.longfor.gf.lc.account.dto.AccountDto
     */
    AccountDto queryAccount(String accNo, String accType) throws ServiceException;
    /**
     * @description 创建交易明细
     * @author jiangdan
     * @date 2019/5/22 15:17
     * @param[req]
     * @return int
     */
    TransactionDto saveTransaction(TransactionReq req) throws ServiceException;
    /**
     * @description 修改余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:49
     * @param[req]
     * @return int
     */
    int updateBalanceOrFrozen(AccountInfoReq req) throws ServiceException;
    /**
     * @description 判断该成本、业务账户是否存在
     * @author jiangdan
     * @date 2019/5/24 16:05
     * @param[accNo type]
     * @return boolean
     */
    boolean isExistAccByAccNo(String accNo,  String type);
    /**
     * @description 保存关通同账号
     * @author jiangdan
     * @date 2019/5/24 16:46
     * @param[accountDto, jobNo]
     * @return int
     */
    GatGrAccount saveGatAccount(String accNo, String personAd, String jobNo) throws ServiceException;

    /**
     * Method: 通过交易号，外部交易号，将交易状态置为
     * Description:
     * Author guoguangxiao
     * Data 2019/5/28 17:54
     * @param
     * @return
     */
    int updateTransStsByTransNoAndOutNo(String transNo, String outTransNo, String transSts);

    /**
     * Method: 更新余额账户余额
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 16:42
     * @param operType 正数 增加 非正数 减少
     * @return
     */
    int updateAccBalance(String accNo, String changeAmount, int operType);

    /**
     * @description 修改红包冻结的交易状态
     * @author jiangdan
     * @date 2019/5/31 11:14
     * @param[batchNo, businessType]
     * @return int
     */
    int updateTransStatus(String batchNo, String businessType) throws ServiceException;

    /**
     * @description 查询已抢红包总金额
     * @author jiangdan
     * @date 2019/5/31 16:19
     * @param[batchNo, accNo]
     * @return java.math.BigDecimal
     */
    BigDecimal selectGrabAmountByAccNo(String batchNo, String accNo);
}
