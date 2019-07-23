package com.longfor.gf.lc.account.service;

/**
 * @author guoguangxiao
 * @date 2019/5/16 11:51:03
 */
public interface RedisLockService {

    /**
     * Method: redis加锁
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 11:53
     * @param lockKey 锁名
     * @param timeoutMsecs  锁等待超时
     * @param lockKey 锁持有超时 0永不超时
     * @return
     */
    boolean lock(String lockKey, long timeoutMsecs, long expireMsecs);

    /**
     * Method: redis 释放锁
     * Description:
     * Author guoguangxiao
     * Data 2019/5/16 11:53
     * @param
     * @return
     */
    void unlock(String lockKey);

}
