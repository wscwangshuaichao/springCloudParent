package springcloud.learn.wsc.servicehi.listener;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.data.redis.RedisKey;
import com.longfor.gf.lc.account.api.ApiTransferService;
import com.longfor.gf.lc.jobs.constants.HandlerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author xupeng
 * @date 2019/03/25
 */
@Slf4j
@Component
public class RedisExpiredListener implements MessageListener {
    @Resource
    private ApiTransferService transferService;

    @Resource
    private RedisTemplate cacheRedis;

    //超时时间 30s
    private static final int TIMEOUT = 30 * 1000;

    /**
     * @description 监听事件
     * @param message
     * @param bytes
     * @author xupeng13
     * @date 2019/6/3 11:24
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        String key = new String(body);
        log.info("接收过期消息--->key={},val={}",new String(message.getChannel()), key);
        if (key.startsWith(HandlerConstants.RedPackage.RED_PACKAGE_PREFIX)) {
            String redisKey = RedisKey.join(HandlerConstants.RedPackage.RED_PACKAGE_GIVEBACK_KEY, key);
            //加锁
            long time = System.currentTimeMillis() + TIMEOUT;
            String value = String.valueOf(time);
            if(!lock(redisKey, value)){
                log.info("红包过期退还接口重复调用调用。参数：{}", key);
                return;
            }

            // 调用退红包服务
            String batchNo = key.replaceAll(HandlerConstants.RedPackage.RED_PACKAGE_BATCH_NO_PREFIX, "");
            log.info("红包过期退还接口调用开始。参数：{}", batchNo);
            BaseResponse baseResponse = transferService.refund(batchNo);
            log.info("红包过期退还接口调用结束。参数：{}，返回值：{}", batchNo, baseResponse.toString());

            // 解锁
            unlock(redisKey, value);
        }
    }

    /**
     * @description 加锁
     * @param key key
     * @param value 时间戳
     * @return
     * @author xupeng13
     * @date 2019/6/3 10:59
     */
    public boolean lock(String key, String value){
        // 对应setnx命令
        if(cacheRedis.opsForValue().setIfAbsent(key, value)){
            //可以成功设置,也就是key不存在
            return true;
        }

        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = (String) cacheRedis.opsForValue().get(key);
        //如果锁过期 currentValue不为空且小于当前时间
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value 对应getset，如果key存在
            String oldValue = (String) cacheRedis.opsForValue().getAndSet(key,value);

            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
        }
        return false;
    }

    /**
     * @description 解锁
     * @param key key
     * @param value 时间戳
     * @return
     * @author xupeng13
     * @date 2019/6/3 10:58
     */
    public void unlock(String key,String value){
        try {
            String currentValue = (String)cacheRedis.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
                //删除key
                cacheRedis.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[Redis分布式锁] 解锁出现异常了。key: {}，e: {}", key, e);
        }
    }
}
