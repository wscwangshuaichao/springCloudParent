package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.Transaction;
import com.longfor.gf.lc.account.dao.entity.example.TransactionExample;
import com.longfor.gf.lc.account.dto.AccTradingSummaryDto;
import com.longfor.gf.lc.account.dto.AccTransactionDto;
import com.longfor.gf.lc.account.dto.TransactionDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.TradingSearchReq;
import com.longfor.gf.lc.account.req.TransactionReq;

import java.util.List;

/**
 * @Author jiangdan
 * @Date 2019/5/22 15:15
 **/
public interface TransactionService {

    /**
     * @description 根据条件分页查询用户交易信息
     * @author jiangdan
     * @date 2019/5/22 15:16
     * @param[req, page]
     * @return
     */
    PageInfo<TransactionDto> queryPage(TradingSearchReq req, PageInfo page);

    /**
     * @description 根据条件查询交易明细详情
     * @author jiangdan
     * @date 2019/5/22 15:22
     * @param[example]
     * @return
     */
    TransactionDto queryByTransactionReq(TradingSearchReq req) throws ServiceException;

    /**
     * @description 获取用户的收支金额（只有转账成功和抢红包记录trans_type = '1' and status = '1'）
     * @author jiangdan
     * @date 2019/5/22 18:49
     * @param[req]
     * @return
     */
    AccTradingSummaryDto queryTradingMoney(TradingSearchReq req);

    /**
     * @description 修改交易状态
     * @author jiangdan
     * @date 2019/5/24 11:50
     * @param[req]
     * @return int
     */
    int updateStatus(Integer keyId, String status) throws ServiceException;
    /**
     * @description 根据条件查询交易记录
     * @author jiangdan
     * @date 2019/5/24 14:47
     * @param[transactionExample]
     * @return java.util.List<com.longfor.gf.lc.account.dto.TransactionDto>
     */
    List<TransactionDto> queryTransactionByExample(TransactionExample transactionExample) throws ServiceException;

    /**
     * @description 根据条件查询用户的交易明细
     * @author jiangdan
     * @date 2019/5/28 12:46
     * @param[req]
     * @return
     */
    List<TransactionDto> redPacketsByAccNo(TradingSearchReq req);

    /**
     * Method: 批量保存
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 16:09
     * @param
     * @return
     */
    int saveList(List<TransactionReq> reqList);


    /**
     * Method: 通过流水号查询唯一记录
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 19:28
     * @param
     * @return
     */
    Transaction getTransactionByTransNo(String transNo, String outTransNo);

    /**
     * @description H5根据条件分页查询用户交易信息（没有抢红包、未处理、失败、关闭、取消的记录）
     * @author jiangdan
     * @date 2019/5/22 15:16
     * @param[req, page]
     * @return
     */
    PageInfo<AccTransactionDto> queryH5TransPage(TradingSearchReq req, PageInfo page);
    /**
     * @description H5根据条件分页查询用户交易信息（只有转账成功和抢红包记录trans_type = '1' and status = '1'）
     * @author jiangdan
     * @date 2019/5/22 15:16
     * @param[req, page]
     * @return
     */
    List<AccTransactionDto> selectH5TransPage(TradingSearchReq req);

    /**
     * @description 获取用户的总收支金额（没有抢红包、未处理、失败、关闭、取消的记录）
     * @author jiangdan
     * @date 2019/6/18 16:02
     * @param[req]
     * @return com.longfor.gf.lc.account.dto.AccTradingSummaryDto
     */
    AccTradingSummaryDto selectTradingMoney(TradingSearchReq req);
}
