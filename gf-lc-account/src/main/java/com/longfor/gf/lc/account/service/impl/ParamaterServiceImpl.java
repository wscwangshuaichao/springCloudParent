package com.longfor.gf.lc.account.service.impl;

import com.longfor.gf.lc.account.dao.ParamaterMapper;
import com.longfor.gf.lc.account.dao.entity.Paramater;
import com.longfor.gf.lc.account.service.ParamaterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ParamaterServiceImpl
 * @Author jiangdan
 * @Date 2019/5/22 11:23
 **/
@Slf4j
@Service
public class ParamaterServiceImpl implements ParamaterService {

    @Resource
    private ParamaterMapper paramaterMapper;

    @Override
    public Paramater selectOneByParamValue(String paramValue) {
        if(StringUtils.isEmpty(paramValue)){
            return null;
        }
        return paramaterMapper.selectOneByParamValue(paramValue);
    }

    @Override
    public List<Paramater> selectList(Paramater paramater) {
        return paramaterMapper.selectList(paramater);
    }
}
