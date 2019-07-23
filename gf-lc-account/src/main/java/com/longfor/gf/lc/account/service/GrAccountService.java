package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.GrAccountReq;

import java.util.List;

/**
 * @Author jiangdan
 * @Date 2019/5/17 11:19
 */
public interface GrAccountService{

    /**
     * @description 保存个人账号信息
     * @author jiangdan
     * @date 2019/5/17 13:44
     * @param[ accountReq]
     * @return int
     */
    int save(GrAccountReq accountReq) throws ServiceException;

    /**
     * @description 分页查询个人账号信息
     * @author jiangdan
     * @date 2019/5/17 13:43
     * @param[ grAccountReq, page]
     * @return
     */
    PageInfo<AccountDto> queryPage(GrAccountReq grAccountReq, PageInfo page);

    /**
     * @description 根据主键编号查询个人账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[accNo]
     * @return
     */
    GrAccount queryByAccNo(String accNo);

    /**
     * @description 根据条件查询个人账号信息
     * @author jiangdan
     * @date 2019/5/17 15:07
     * @param[grAccountReq]
     * @return com.longfor.gf.lc.account.dao.entity.GrAccount
     */
    GrAccount queryByGrAccountReq(GrAccountReq grAccountReq) throws ServiceException;

    /**
     * @description 批量新增个人账号
     * @author jiangdan
     * @date 2019/5/17 16:55
     * @param[list]
     * @return int
     */
    int batchSave(List<GrAccountReq> list) throws ServiceException;

    /**
     * @description 根据OA账号查询个人账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[ad]
     * @return
     */
    GrAccount queryByPersonAd(String ad) throws ServiceException;

    /**
     * @description 根据OA账号查询个人账号信息
     * @author jiangdan
     * @date 2019/5/21 13:51
     * @param[ad]
     * @return java.util.List<com.longfor.gf.lc.account.dao.entity.GrAccount>
     */
    List<GrAccount> queryListByPersonAd(String ad);

    /**
     * @description 修改个人账号信息
     * @author jiangdan
     * @date 2019/5/20 14:01
     * @param[accountReq]
     * @return int
     */
    int update(GrAccountReq accountReq) throws ServiceException;

    /**
     * @description 删除账号信息
     * @author jiangdan
     * @date 2019/5/20 16:51
     * @param[accNo]
     * @return int
     */
    int delete(String accNo) throws ServiceException;

}
