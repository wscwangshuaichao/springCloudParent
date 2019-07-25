package springcloud.learn.wsc.dataredis.conf;

import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: DynamicRedisProperties
 * @Decription TOO
 * @Date 2019/7/25 14:49
 **/
@ConfigurationProperties(
        prefix = "longfor.data.redis",
        ignoreUnknownFields = false
)
public class DynamicRedisProperties {
    public static final String PREFIX = "longfor.data.redis";
    private String namespace;
    private Map<String, RedisProperties> connection = Maps.newLinkedHashMap();

    public DynamicRedisProperties() {
    }

    public String getNamespace() {
        return this.namespace;
    }

    public Map<String, RedisProperties> getConnection() {
        return this.connection;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setConnection(Map<String, RedisProperties> connection) {
        this.connection = connection;
    }
}