package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.ZeroCpAccount;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZeroCpAccountMapper extends LFMySQLMapper<ZeroCpAccount> {
}