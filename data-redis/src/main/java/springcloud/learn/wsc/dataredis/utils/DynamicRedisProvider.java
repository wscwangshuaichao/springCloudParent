package springcloud.learn.wsc.dataredis.utils;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: DynamicRedisProvider
 * @Decription TOO
 * @Date 2019/7/25 14:44
 **/
public interface DynamicRedisProvider {
    Map<String, RedisConnectionFactory> loadRedis();
}
