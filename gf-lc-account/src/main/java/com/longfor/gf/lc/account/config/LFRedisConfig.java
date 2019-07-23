package com.longfor.gf.lc.account.config;

import com.longfor.gaia.gfs.data.redis.DynamicRedisProvider;
import com.longfor.gaia.gfs.data.redis.JacksonSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author guoguangxiao
 * @date 2019/5/24 11:13:08
 */
@Configuration
@EnableCaching
public class LFRedisConfig{

    @Resource
    private DynamicRedisProvider dynamicRedisProvider;

    @Bean(name = "redisLock")
    public RedisTemplate demoRedis1() {
        StringRedisTemplate template = new StringRedisTemplate(dynamicRedisProvider.loadRedis().get("redisLock"));
        JacksonSerializer.setJacksonSerializer(template);
        return template;
    }

}
