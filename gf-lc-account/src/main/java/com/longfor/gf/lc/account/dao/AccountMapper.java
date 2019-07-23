package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.GrAccount;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.dto.ZeroAccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author jiangdan
 * @Date 2019/5/24 9:58
 **/
@Mapper
public interface AccountMapper extends LFMySQLMapper<GrAccount> {

    /**
     * @description 根据oa账号查询激励清零账户信息
     * @author jiangdan
     * @date 2019/5/24 10:05
     * @param[personAd]
     * @return com.longfor.gf.lc.account.dto.ZeroAccountDto
     */
    ZeroAccountDto selectZeroJlAccNoByPersonAd(@Param("personAd") String personAd);
    /**
     * @description 根据产品编号查询产品清零账户信息
     * @author jiangdan
     * @date 2019/5/24 10:06
     * @param[cpCode]
     * @return com.longfor.gf.lc.account.dto.ZeroAccountDto
     */
    ZeroAccountDto selectZeroCpAccNoByCpCode(@Param("cpCode") String cpCode);
    /**
     * @description 根据其他编号查询其他清零账户信息
     * @author jiangdan
     * @date 2019/5/24 10:06
     * @param[qtCode]
     * @return com.longfor.gf.lc.account.dto.ZeroAccountDto
     */
    ZeroAccountDto selectZeroQtAccNoByQtCode(@Param("qtCode") String qtCode);

    /**
     * @description 根据主键查询个人账户余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:26
     * @param[accNo]
     * @return com.longfor.gf.lc.account.dto.AccountDto
     */
    AccountDto selectGrAccByAccNo(@Param("accNo") String accNo);
    /**
     * @description 根据主键查询产品账户余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:26
     * @param[accNo]
     * @return com.longfor.gf.lc.account.dto.AccountDto
     */
    AccountDto selectCpAccByAccNo(@Param("accNo") String accNo);
    /**
     * @description 根据主键查询激励账户余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:26
     * @param[accNo]
     * @return com.longfor.gf.lc.account.dto.AccountDto
     */
    AccountDto selectJlAccByAccNo(@Param("accNo") String accNo);
    /**
     * @description 根据主键查询其他账户余额、冻结金额
     * @author jiangdan
     * @date 2019/5/24 10:26
     * @param[accNo]
     * @return com.longfor.gf.lc.account.dto.AccountDto
     */
    AccountDto selectQtAccByAccNo(@Param("accNo") String accNo);

    /**
     * Method: 余额增加
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 17:18
     * @param
     * @return
     */
    int incrAccBalance(@Param("accNo") String accNo, @Param("changeAmount") BigDecimal changeAmount, @Param("tableName") String tableName);
    /**
     * Method: 余额减少
     * Description:
     * Author guoguangxiao
     * Data 2019/5/29 17:19
     * @param
     * @return
     */
    int decrAccBalance(@Param("accNo") String accNo, @Param("changeAmount") BigDecimal changeAmount, @Param("tableName") String tableName);



}
