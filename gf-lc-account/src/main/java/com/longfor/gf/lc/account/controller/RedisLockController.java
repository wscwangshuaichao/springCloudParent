package com.longfor.gf.lc.account.controller;

import com.longfor.gf.lc.account.api.ApiRedisLockService;
import com.longfor.gf.lc.account.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guoguangxiao
 * @date 2019/5/30 10:15:24
 */
@Slf4j
@RestController
public class RedisLockController implements ApiRedisLockService {

    @Resource
    private RedisLockService redisLockService;

    @Override
    public boolean lock(@RequestParam(value = "lockKey") String lockKey,
                        @RequestParam(value = "timeoutMsecs") long timeoutMsecs,
                        @RequestParam(value = "expireMsecs") long expireMsecs) {
        log.info("[RequestParameter] - [RedisLockController] - method:[lock] - req:[{}-{}-{}]", lockKey, timeoutMsecs, expireMsecs);
        long curTime = System.currentTimeMillis();
        boolean lock = redisLockService.lock(lockKey, timeoutMsecs, expireMsecs);
        log.info("[ResponseParameter] - [RedisLockController] - method:[lock] - req:[{}-{}-{}] - result:[{}] - duration:[{}]",
                lockKey, timeoutMsecs, expireMsecs, lock, System.currentTimeMillis()-curTime);
        return lock;
    }

    @Override
    public void unlock(@RequestParam(value = "lockKey") String lockKey) {
        log.info("[RequestParameter] - [RedisLockController] - method:[RequestParameter] - req:[{}]", lockKey);
        long curTime = System.currentTimeMillis();
        redisLockService.unlock(lockKey);
        log.info("[ResponseParameter] - [RedisLockController] - method:[RequestParameter] - req:[{}] - duration:[{}]",
                lockKey,System.currentTimeMillis()-curTime);
    }
}
