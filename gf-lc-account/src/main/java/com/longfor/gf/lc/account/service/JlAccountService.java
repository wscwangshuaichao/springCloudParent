package com.longfor.gf.lc.account.service;


import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.JlAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.JlAccountReq;

import java.util.List;

/**
 * @author jiangdan
 * @date 2019/5/17 10:51:03
 */
public interface JlAccountService{
    /**
     * @description 保存激励账号信息
     * @author jiangdan
     * @date 2019/5/17 13:44
     * @param[accountReq]
     * @return int
     */
    int save(JlAccountReq accountReq) throws ServiceException;

    /**
     * @description 分页查询激励账号信息
     * @author jiangdan
     * @date 2019/5/17 13:43
     * @param[ jlAccountReq, page]
     * @return
     */
    PageInfo<AccountDto> queryPage(JlAccountReq jlAccountReq, PageInfo page);

    /**
     * @description 根据主键编号查询激励账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[accNo]
     * @return
     */
    JlAccount queryByAccNo(String accNo);

    /**
     * @description 根据条件查询激励账号信息
     * @author jiangdan
     * @date 2019/5/17 15:07
     * @param[jlAccountReq]
     * @return com.longfor.gf.lc.account.dao.entity.JlAccount
     */
    JlAccount queryByJlAccountReq(JlAccountReq jlAccountReq) throws ServiceException;

    /**
     * @description 批量新增激励账号
     * @author jiangdan
     * @date 2019/5/17 16:55
     * @param[list]
     * @return int
     */
    int batchSave(List<JlAccountReq> list) throws ServiceException;

    /**
     * @description 根据OA账号查询激励账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[ad]
     * @return
     */
    JlAccount queryByPersonAd(String ad) throws ServiceException;

    /**
     * @description 根据OA账号查询激励账号信息
     * @author jiangdan
     * @date 2019/5/21 15:07
     * @param[ad]
     * @return
     */
    List<JlAccount> queryListByPersonAd(String ad);

    /**
     * @description 修改激励账号信息
     * @author jiangdan
     * @date 2019/5/20 14:01
     * @param[accountReq]
     * @return int
     */
    int update(JlAccountReq accountReq) throws ServiceException;

    /**
     * @description 删除账号信息
     * @author jiangdan
     * @date 2019/5/20 16:51
     * @param[accNo]
     * @return int
     */
    int delete(String accNo) throws ServiceException;

}
