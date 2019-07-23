package springcloud.learn.wsc.servicehi.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

/**
 * @author xupeng
 * @date 2019/03/25
 */
@Configuration
public class RedisListenerConfig {

    @Resource
    private RedisExpiredListener redisExpiredListener;

    @Value("${gf-lc-jobs.listenter.db}")
    private String db;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisExpiredListener, new PatternTopic("__keyevent@" + db + "__:expired"));
        return container;
    }
}
