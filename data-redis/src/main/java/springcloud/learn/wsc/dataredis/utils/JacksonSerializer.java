package springcloud.learn.wsc.dataredis.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wangshuaichao
 * @ClassName: JacksonSerializer
 * @Decription TOO
 * @Date 2019/7/25 14:45
 **/
public class JacksonSerializer {
    private JacksonSerializer() {
    }

    public static void setJacksonSerializer(RedisTemplate<String, String> template) {
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }
}
