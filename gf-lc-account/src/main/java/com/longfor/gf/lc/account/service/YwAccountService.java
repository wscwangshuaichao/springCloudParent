package com.longfor.gf.lc.account.service;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.YwAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.YwAccountReq;

import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/5/16 16:50:03
 */
public interface YwAccountService  {

    int save(YwAccountReq accountReq) throws ServiceException;

    /**
     * @description 分页查询业务账号信息
     * @author jiangdan
     * @date 2019/5/17 13:42
     * @param[ywAccountReq, page]
     * @return
     */
    PageInfo<AccountDto> queryPage(YwAccountReq ywAccountReq, PageInfo page);

    /**
     * @description 根据主键编号查询业务账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[accNo]
     * @return
     */
    YwAccount queryByAccNo(String accNo);

    /**
     * @description 根据条件查询其他账号信息
     * @author jiangdan
     * @date 2019/5/17 15:07
     * @param[ywAccountReq]
     * @return com.longfor.gf.lc.account.dao.entity.YwAccount
     */
    YwAccount queryByYwAccountReq(YwAccountReq ywAccountReq) throws ServiceException;

    /**
     * @description 批量新增业务账号
     * @author jiangdan
     * @date 2019/5/17 16:55
     * @param[list]
     * @return int
     */
    int batchSave(List<YwAccountReq> list) throws ServiceException;

    /**
     * @description 修改激励业务信息
     * @author jiangdan
     * @date 2019/5/20 14:01
     * @param[accountReq]
     * @return int
     */
    int update(YwAccountReq accountReq) throws ServiceException;

    /**
     * @description 删除账号信息
     * @author jiangdan
     * @date 2019/5/20 16:51
     * @param[accNo]
     * @return int
     */
    int delete(String accNo) throws ServiceException;

}
