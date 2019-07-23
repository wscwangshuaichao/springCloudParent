package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.QtAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.QtAccountReq;

import java.util.List;

/**
 * @Author jiangdan
 * @Date 2019/5/17 11:23
 **/
public interface QtAccountService{

    /**
     * @description 保存其他账号信息
     * @author jiangdan
     * @date 2019/5/17 13:44
     * @param[accountReq]
     * @return int
     */
    int save(QtAccountReq accountReq) throws ServiceException;

    /**
     * @description 分页查询其他账号信息
     * @author jiangdan
     * @date 2019/5/17 13:43
     * @param[ qtAccountReq, page]
     * @return
     */
    PageInfo<AccountDto> queryPage(QtAccountReq qtAccountReq, PageInfo page);

    /**
     * @description 根据主键编号查询其他账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[accNo]
     * @return
     */
    QtAccount queryByAccNo(String accNo);

    /**
     * @description 根据条件查询其他账号信息
     * @author jiangdan
     * @date 2019/5/17 15:07
     * @param[qtAccountReq]
     * @return com.longfor.gf.lc.account.dao.entity.QtAccount
     */
    QtAccount queryByQtAccountReq(QtAccountReq qtAccountReq) throws ServiceException;

    /**
     * @description 批量新增其他账号
     * @author jiangdan
     * @date 2019/5/17 16:55
     * @param[list]
     * @return int
     */
    int batchSave(List<QtAccountReq> list) throws ServiceException;

    /**
     * @description 修改激励其他信息
     * @author jiangdan
     * @date 2019/5/20 14:01
     * @param[accountReq]
     * @return int
     */
    int update(QtAccountReq accountReq) throws ServiceException;

    /**
     * @description 删除账号信息
     * @author jiangdan
     * @date 2019/5/20 16:51
     * @param[accNo]
     * @return int
     */
    int delete(String accNo) throws ServiceException;

    /**
     * @description 根据其他编号查询其他账号信息
     * @author jiangdan
     * @date 2019/5/21 15:13
     * @param[qtCode]
     * @return
     */
    List<QtAccount> queryListByQtCode(String qtCode);

}
