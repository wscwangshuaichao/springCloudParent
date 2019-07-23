package com.longfor.gf.lc.account.service;

import com.longfor.gf.lc.account.exception.ServiceException;

public interface SequenceService {

    /**
     * Method: 获取指定序列下一个值
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 10:05
     * @param
     * @return
     */
    int nextval(String seqCode);

    /**
     * Method: 获取指定序列当前值
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 10:05
     * @param
     * @return
     */
    int currval(String seqCode);

    /**
     * Method: 通过账号类型使用序列自动生成账户号
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 10:05
     * @param
     * @return
     */
    String createAccNo(String accountType) throws ServiceException;
    /**
     * @description 自动生成交易批次号
     * @author jiangdan
     * @date 2019/5/22 18:06
     * @param[]
     * @return java.lang.String
     */
    String createBatchNo();

    /**
     * Method: 自动生成交易流水号
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 14:46
     * @param
     * @return
     */
    String getTrxNo();

}
