package com.longfor.gf.lc.account.service.impl;

import com.longfor.gf.lc.account.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author guoguangxiao
 * @date 2019/5/21 15:11:52
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Resource(name="redisLock")
    private RedisTemplate redisTemplate;

    @Override
    public boolean rateLimit(String key, int limitTime, int limitCount) {

        log.info("[RequestParameter] - [RedisService] - method:[rateLimit] - key:[{}] - limitTime:[{}] - limitCount:[{}]", key, limitTime, limitCount);
        long curTime = System.currentTimeMillis();
        if(limitTime<=0 || limitCount<=0 ){
            return false;
        }
        boolean flag = true;
        try{
            Long count = redisTemplate.opsForValue().increment(key,1L);
            if (count == 1) {
                //设置有效期
                redisTemplate.expire(key, limitTime, TimeUnit.SECONDS);
            }
            if (count > limitCount) {
                flag = false;
            }
        }catch (Exception e){
            flag = true;
            log.error("[Exception] - [RedisService] - method:[rateLimit] - key:[{}] - flag:[{}]", key, flag, e);
        }
        log.info("[ResponseParameter] - [RedisService] - method:[rateLimit] - key:[{}] - flag:[{}] - duration:[{}]]", key, flag, System.currentTimeMillis()-curTime);
        return flag;

    }


}
