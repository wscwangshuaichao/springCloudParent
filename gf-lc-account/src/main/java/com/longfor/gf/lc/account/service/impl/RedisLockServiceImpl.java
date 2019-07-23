package com.longfor.gf.lc.account.service.impl;

import com.longfor.gf.lc.account.service.RedisLockService;
import com.longfor.gf.lc.account.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author guoguangxiao
 * @date 2019/5/16 11:52:21
 */
@Slf4j
@Service
public class RedisLockServiceImpl implements RedisLockService {

    @Resource(name="redisLock")
    private RedisTemplate redisTemplate;

    @Override
    public boolean lock(String lockKey, long timeoutMsecs, long expireMsecs) {
        lockKey = Constants.LOCK_PREFIX + lockKey;
        try {
            long timeout = timeoutMsecs;
            while (timeout >= 0) {
                long expires = System.currentTimeMillis() + expireMsecs + 1;
                //锁到期时间
                String expiresStr = String.valueOf(expires);
                //获取锁 setnx
                if (redisTemplate.opsForValue().setIfAbsent(lockKey, expiresStr)) {
                    if(expireMsecs>0){
                        if(redisTemplate.expire(lockKey, expireMsecs, TimeUnit.MILLISECONDS)){
                            return true;
                        }else{
                            redisTemplate.delete(lockKey);
                        }
                    }
                    return true;
                }
                String currentValueStr = (String) redisTemplate.opsForValue().get(lockKey);
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                    String oldValueStr = (String) redisTemplate.opsForValue().getAndSet(lockKey, expiresStr);
                    //获取上一个锁到期时间，并设置现在的锁到期时间，
                    //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        return true;
                    }
                }
                timeout -= 100;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            log.error("[Exception] - [RedisLockServiceImpl] - [lock] - errorMsg:[{}]", e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void unlock(String lockKey) {

        lockKey = Constants.LOCK_PREFIX + lockKey;
        try {
//            long nowTime = System.currentTimeMillis();
//            String reidsLockTime = redisClient.getJedis().get(lockKey);
//            if (nowTime < Long.parseLong(reidsLockTime)) {
//                redisClient.getJedis().del(lockKey);
//            }
            if(redisTemplate.hasKey(lockKey)){
                redisTemplate.delete(lockKey);
            }
        } catch (Exception e) {
            log.error("[Exception] - [RedisLockServiceImpl] - [unlock] - errorMsg:[{}]", e.getMessage(), e);
        }

    }
}
