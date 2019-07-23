package com.longfor.gf.lc.account.service;

/**
 * @author guoguangxiao
 * @date 2019/5/21 15:09:19
 */
public interface RedisService {

    /**
     * Method: 频次拦截
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 15:10
     * @param key redis 键
     * @param limitTime 有效期 单位s
     * @param limitCount 有效期内拦截次数
     * @return
     */
    boolean rateLimit(String key, int limitTime, int limitCount);

}
