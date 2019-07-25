package springcloud.learn.wsc.dataredis.utils;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import springcloud.learn.wsc.dataredis.conf.DynamicRedisProperties;
import springcloud.learn.wsc.dataredis.conf.JedisConnectionFactoryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: YamlDynamicRedisProvider
 * @Decription TOO
 * @Date 2019/7/25 14:47
 **/
public class YamlDynamicRedisProvider implements DynamicRedisProvider {
    private DynamicRedisProperties properties;

    public YamlDynamicRedisProvider(DynamicRedisProperties properties) {
        this.properties = properties;
    }

    public Map<String, RedisConnectionFactory> loadRedis() {
        Map<String, RedisProperties> connection = this.properties.getConnection();
        Map<String, RedisConnectionFactory> connectionMap = new HashMap(connection.size());
        connection.forEach((key, value) -> {
            RedisConnectionFactory var10000 = (RedisConnectionFactory)connectionMap.put(key, JedisConnectionFactoryBuilder.build(key, value));
        });
        return connectionMap;
    }
}
