package springcloud.learn.wsc.servicehi.config;

import com.longfor.gaia.gfs.data.redis.DynamicRedisProvider;
import com.longfor.gaia.gfs.data.redis.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author guoguangxiao
 * @date 2019/5/24 11:13:08
 */
@Configuration
@EnableCaching
public class LFRedisConfig extends CachingConfigurerSupport {

    @Autowired
    private DynamicRedisProvider dynamicRedisProvider;

    /**
     * 名字要和配置文件里面的connection保持一致
     *
     * @return template
     */
    @Bean(name = "cacheRedis")
    public RedisTemplate cacheRedis() {
        StringRedisTemplate template = new StringRedisTemplate(dynamicRedisProvider.loadRedis().get("cacheRedis"));
        JacksonSerializer.setJacksonSerializer(template);
        return template;
    }

}
