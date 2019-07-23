package com.longfor.gf.lc.account.service;


import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gf.lc.account.dao.entity.CpAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.CpAccountReq;

import java.util.List;

/**
 * @author jiangdan
 * @date 2019/5/17 13:43
 */
public interface CpAccountService{
    /**
     * @description 保存产品账号信息
     * @author jiangdan
     * @date 2019/5/17 13:44
     * @param[accountReq]
     * @return int
     */
    int save(CpAccountReq accountReq) throws ServiceException;

    /**
     * @description 分页查询产品账号信息
     * @author jiangdan
     * @date 2019/5/17 13:43
     * @param[ywAccountReq, page]
     * @return
     */
    PageInfo<AccountDto> queryPage(CpAccountReq cpAccountReq, PageInfo page);

    /**
     * @description 根据主键编号查询产品账号信息
     * @author jiangdan
     * @date 2019/5/17 14:42
     * @param[accNo]
     * @return
     */
    CpAccount queryByAccNo(String accNo);

    /**
     * @description 根据条件查询产品账号信息
     * @author jiangdan
     * @date 2019/5/17 15:07
     * @param[cpAccountReq]
     * @return com.longfor.gf.lc.account.dao.entity.CpAccount
     */
     CpAccount queryByCpAccountReq(CpAccountReq cpAccountReq) throws ServiceException;

     /**
      * @description 批量新增产品账号
      * @author jiangdan
      * @date 2019/5/17 16:55
      * @param[list]
      * @return int
      */
     int batchSave(List<CpAccountReq> list) throws ServiceException;

    /**
     * @description 修改产品账号信息
     * @author jiangdan
     * @date 2019/5/20 14:01
     * @param[accountReq]
     * @return int
     */
     int update(CpAccountReq accountReq) throws ServiceException;

     /**
      * @description 删除账号信息
      * @author jiangdan
      * @date 2019/5/20 16:51
      * @param[accNo]
      * @return int
      */
     int delete(String accNo) throws ServiceException;
    /**
     * @description 根据产品编号查询产品信息
     * @author jiangdan
     * @date 2019/5/21 14:59
     * @param[cpCode]
     * @return
     */
     List<CpAccount> queryListByCpCode(String cpCode);

}
