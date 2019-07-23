package springcloud.learn.wsc.servicehi.config;

import com.google.common.base.Charsets;
import com.longfor.gaia.gfs.data.redis.DynamicRedisProvider;
import com.longfor.gaia.gfs.data.redis.JacksonSerializer;
import com.longfor.gaia.gfs.data.redis.RedisKey;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author ChongLi
 * @date 2018-08-31 16:14
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Resource
    private DynamicRedisProvider dynamicRedisProvider;


    @Bean(name = "redisTemplateOne")
    public RedisTemplate redisTemplateOne() {
        StringRedisTemplate template = new StringRedisTemplate(dynamicRedisProvider.loadRedis().get("redisTemplateOne"));
        JacksonSerializer.setJacksonSerializer(template);
        return template;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplateOne());
        cacheManager.setUsePrefix(true);
        cacheManager.setCachePrefix(cacheName -> RedisKey.join(cacheName + ":").getBytes(Charsets.UTF_8)); // 这里就是我们配置的 namespace 生成统一的 prefix
        return cacheManager;
    }
}
