package com.longfor.gf.lc.account.config;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
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
 * @author guoguangxiao
 * @date 2019/6/3 13:54:31
 */
@Configuration
@EnableCaching
public class LFRedisCacheConfig  extends CachingConfigurerSupport {

    @Resource
    private DynamicRedisProvider dynamicRedisProvider;
    @Resource(name="cacheRedis")
    private RedisTemplate cacheRedis;

    @Bean(name = "cacheRedis")
    public RedisTemplate cacheRedis() {
        StringRedisTemplate template = new StringRedisTemplate(dynamicRedisProvider.loadRedis().get("cacheRedis"));
        JacksonSerializer.setJacksonSerializer(template);
        return template;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(this.cacheRedis);
        cacheManager.setUsePrefix(true);
        // 这里就是我们配置的 namespace 生成统一的 prefix
        cacheManager.setCachePrefix(cacheName -> RedisKey.join(cacheName + ":").getBytes(Charsets.UTF_8));
        //这里定义不同的 cacheName 对应的超时时间
        cacheManager.setExpires(ImmutableMap.<String, Long>builder()
                .put("CACHE_YWACCOUNT",7 * 24 * 60 * 60L)
                .put("CACHE_CPACCOUNT", 7 * 24 * 60 * 60L)
                .put("CACHE_GRACCOUNT", 7 * 24 * 60 * 60L)
                .put("CACHE_QTACCOUNT", 7 * 24 * 60 * 60L)
                .put("CACHE_JLACCOUNT", 7 * 24 * 60 * 60L)
                .put("CACHE_ACCOUNT", 7 * 24 * 60 * 60L)
                .put("CACHE_JLACCOUNT_AD", 7 * 24 * 60 * 60L)
                .put("CACHE_GRACCOUNT_AD", 7 * 24 * 60 * 60L)
                .put("CACHE_GRACCOUNT_PAGE", 7 * 24 * 60 * 60L)
                .put("CACHE_JLACCOUNT_PAGE", 7 * 24 * 60 * 60L)
                .put("CACHE_CPACCOUNT_PAGE", 7 * 24 * 60 * 60L)
                .put("CACHE_YWACCOUNT_PAGE", 7 * 24 * 60 * 60L)
                .put("CACHE_QTACCOUNT_PAGE", 7 * 24 * 60 * 60L)
                .build());
        //配置所有默认超时时间 单位是秒
//        cacheManager.setDefaultExpiration(7 * 24 * 60 * 60L);
        return cacheManager;
    }

}
