package com.longfor.gf.lc.account.service;

import com.longfor.gf.lc.account.dao.entity.Paramater;

import java.util.List;

/**
 * @Author jiangdan
 * @Date 2019/5/22 11:22
 **/
public interface ParamaterService {

    /**
     * @description 根据参数值获取数据详情
     * @author jiangdan
     * @date 2019/5/22 11:20
     * @param[paramValue]
     * @return com.longfor.gf.lc.account.dao.entity.Paramater
     */
    Paramater selectOneByParamValue(String paramValue);

    /**
     * @description 根据条件查询数据信息
     * @author jiangdan
     * @date 2019/5/22 11:20
     * @param[paramater]
     * @return
     */
    List<Paramater> selectList(Paramater paramater);
}
