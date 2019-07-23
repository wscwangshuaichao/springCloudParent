package com.longfor.gf.lc.account.manager;

import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.req.AccountReq;

/**
 * @author guoguangxiao
 * @date 2019/5/16 17:17:58
 */
public interface AccountBizPlugin {

    /**
     * Method: 增加记录
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 17:00
     * @param
     * @return
     */
    BaseResponse save(AccountReq req) throws ServiceException;

    /**
     * Method: 批量增加记录
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 17:00
     * @param
     * @return
     */
    BaseResponse batchSave(AccountReq accountReq, int saveNum) throws ServiceException;


    /**
     * Method: 通过账号查询
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 17:03
     * @param
     * @return
     */
    BaseResponse queryByAccNo(String accNo);

    /**
     * 分页列表查询
     * @param accountReq
     * @param page
     * @return
     */
    BaseResponse<PageInfo<AccountDto>> queryPage(AccountReq accountReq, PageInfo page);

    /**
     * @description 通过用户名查询账户详情
     * @author jiangdan
     * @date 2019/5/17 14:59
     * @param accountReq
     * @return
     */
    BaseResponse<AccountDto> queryByAccountReq(AccountReq accountReq) throws ServiceException;

    /**
     * @description 修改账号信息
     * @author jiangdan
     * @date 2019/5/20 13:39
     * @param[req]
     * @return
     */
    BaseResponse update(AccountReq req) throws ServiceException;
    /**
     * @description 删除账号信息
     * @author jiangdan
     * @date 2019/5/20 16:46
     * @param[accNo]
     * @return
     */
    BaseResponse delete(String accNo) throws ServiceException;


}
