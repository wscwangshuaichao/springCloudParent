package springcloud.learn.wsc.dataredis.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.health.CompositeHealthIndicatorConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.redis.RedisHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import springcloud.learn.wsc.dataredis.utils.DynamicRedisProvider;
import springcloud.learn.wsc.dataredis.utils.RedisKey;
import springcloud.learn.wsc.dataredis.utils.YamlDynamicRedisProvider;

import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: DynamicRedisAutoConfiguration
 * @Decription TOO
 * @Date 2019/7/25 14:48
 **/
@Configuration
@EnableConfigurationProperties({DynamicRedisProperties.class})
@Import({RedisKey.class})
public class DynamicRedisAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DynamicRedisAutoConfiguration.class);
    private final DynamicRedisProperties properties;

    public DynamicRedisAutoConfiguration(DynamicRedisProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicRedisProvider dynamicRedisConnectionProvider() {
        return new YamlDynamicRedisProvider(this.properties);
    }

    @Configuration
    @ConditionalOnEnabledHealthIndicator("redis")
    public static class RedisHealthIndicatorConfiguration extends CompositeHealthIndicatorConfiguration<RedisHealthIndicator, RedisConnectionFactory> {
        private DynamicRedisProvider dynamicRedisProvider;

        public RedisHealthIndicatorConfiguration(DynamicRedisProvider dynamicRedisProvider) {
            this.dynamicRedisProvider = dynamicRedisProvider;
        }

        @Bean({"redisHealthIndicator"})
        public HealthIndicator redisHealthIndicator() {
            Map<String, RedisConnectionFactory> connectionsMap = this.dynamicRedisProvider.loadRedis();
            return this.createHealthIndicator(connectionsMap);
        }
    }
}
