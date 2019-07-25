package springcloud.learn.wsc.dataredis.conf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author wangshuaichao
 * @ClassName: JedisConnectionFactoryBuilder
 * @Decription TOO
 * @Date 2019/7/25 15:10
 **/
public class JedisConnectionFactoryBuilder {
    private static final Logger log = LoggerFactory.getLogger(JedisConnectionFactoryBuilder.class);

    private JedisConnectionFactoryBuilder() {
    }

    public static JedisConnectionFactory build(String name, RedisProperties properties) {
        log.debug("build redis {}", ToStringBuilder.reflectionToString(properties, ToStringStyle.SHORT_PREFIX_STYLE));
        JedisConnectionFactory jedisConnectionFactory = applyProperties(createJedisConnectionFactory(properties), name, properties);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    private static JedisConnectionFactory applyProperties(JedisConnectionFactory factory, String name, RedisProperties properties) {
        configureConnection(factory, name, properties);
        if (properties.isSsl()) {
            factory.setUseSsl(true);
        }

        factory.setDatabase(properties.getDatabase());
        if (properties.getTimeout() > 0) {
            factory.setTimeout(properties.getTimeout());
        }

        return factory;
    }

    private static JedisConnectionFactory createJedisConnectionFactory(RedisProperties properties) {
        JedisPoolConfig poolConfig = properties.getPool() != null ? jedisPoolConfig(properties) : new JedisPoolConfig();
        Optional<RedisSentinelConfiguration> sentinelConfig = getSentinelConfig(properties);
        if (sentinelConfig.isPresent()) {
            return new JedisConnectionFactory((RedisSentinelConfiguration)sentinelConfig.get(), poolConfig);
        } else {
            Optional<RedisClusterConfiguration> clusterConfig = getClusterConfig(properties);
            return clusterConfig.isPresent() ? new JedisConnectionFactory((RedisClusterConfiguration)clusterConfig.get(), poolConfig) : new JedisConnectionFactory(poolConfig);
        }
    }

    private static void configureConnection(JedisConnectionFactory factory, String name, RedisProperties properties) {
        if (StringUtils.hasText(properties.getUrl())) {
            configureConnectionFromUrl(factory, name, properties);
        } else {
            factory.setHostName(properties.getHost());
            factory.setPort(properties.getPort());
            if (properties.getPassword() != null) {
                factory.setPassword(properties.getPassword());
            }
        }

    }

    private static void configureConnectionFromUrl(JedisConnectionFactory factory, String name, RedisProperties properties) {
        String url = properties.getUrl();
        if (url.startsWith("rediss://")) {
            factory.setUseSsl(true);
        }

        try {
            URI uri = new URI(url);
            factory.setHostName(uri.getHost());
            factory.setPort(uri.getPort());
            if (uri.getUserInfo() != null) {
                String password = uri.getUserInfo();
                int index = password.lastIndexOf(58);
                if (index >= 0) {
                    password = password.substring(index + 1);
                }

                factory.setPassword(password);
            }

        } catch (URISyntaxException var7) {
            throw new IllegalArgumentException(String.format("Malformed 'longfor.data.dynamic.redis.connection.%s.url' %s", name, url), var7);
        }
    }

    private static Optional<RedisClusterConfiguration> getClusterConfig(RedisProperties properties) {
        if (properties.getCluster() == null) {
            return Optional.empty();
        } else {
            RedisProperties.Cluster clusterProperties = properties.getCluster();
            RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());
            if (clusterProperties.getMaxRedirects() != null) {
                config.setMaxRedirects(clusterProperties.getMaxRedirects());
            }

            return Optional.of(config);
        }
    }

    private static JedisPoolConfig jedisPoolConfig(RedisProperties properties) {
        JedisPoolConfig config = new JedisPoolConfig();
        RedisProperties.Pool props = properties.getPool();
        config.setMaxTotal(props.getMaxActive());
        config.setMaxIdle(props.getMaxIdle());
        config.setMinIdle(props.getMinIdle());
        config.setMaxWaitMillis((long)props.getMaxWait());
        return config;
    }

    private static Optional<RedisSentinelConfiguration> getSentinelConfig(RedisProperties properties) {
        Sentinel sentinelProperties = properties.getSentinel();
        if (sentinelProperties != null) {
            RedisSentinelConfiguration config = new RedisSentinelConfiguration();
            config.master(sentinelProperties.getMaster());
            config.setSentinels(createSentinels(sentinelProperties));
            return Optional.of(config);
        } else {
            return Optional.empty();
        }
    }

    private static List<RedisNode> createSentinels(Sentinel sentinel) {
        List<RedisNode> nodes = new ArrayList();
        String[] var2 = StringUtils.commaDelimitedListToStringArray(sentinel.getNodes());
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String node = var2[var4];

            try {
                String[] parts = StringUtils.split(node, ":");
                Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                nodes.add(new RedisNode(parts[0], Integer.valueOf(parts[1])));
            } catch (RuntimeException var7) {
                throw new IllegalStateException(String.format("Invalid redis sentinel property %s", node), var7);
            }
        }

        return nodes;
    }
}
