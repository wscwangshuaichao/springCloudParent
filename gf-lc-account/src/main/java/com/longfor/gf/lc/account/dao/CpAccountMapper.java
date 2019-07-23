package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.CpAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CpAccountMapper extends LFMySQLMapper<CpAccount> {
}
