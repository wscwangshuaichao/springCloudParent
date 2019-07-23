package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.Paramater;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author jiangdan
 * @Date 2019/5/22 11:00
 **/
@Mapper
public interface ParamaterMapper extends LFMySQLMapper<Paramater> {

    /**
     * @description 根据参数值获取数据详情
     * @author jiangdan
     * @date 2019/5/22 11:20
     * @param[paramValue]
     * @return com.longfor.gf.lc.account.dao.entity.Paramater
     */
    Paramater selectOneByParamValue(@Param("paramValue") String paramValue);

    /**
     * @description 根据条件查询数据信息
     * @author jiangdan
     * @date 2019/5/22 11:20
     * @param[paramater]
     * @return java.util.List<com.longfor.gf.lc.account.dao.entity.Paramater>
     */
    List<Paramater> selectList(Paramater paramater);
}
