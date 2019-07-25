package springcloud.learn.wsc.dataredis.utils;

import com.google.common.base.Preconditions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import springcloud.learn.wsc.dataredis.conf.DynamicRedisProperties;

/**
 * @author wangshuaichao
 * @ClassName: RedisKey
 * @Decription TOO
 * @Date 2019/7/25 14:45
 **/
@Component
@Lazy(false)
public class RedisKey implements ApplicationContextAware {
    private static String namespace;

    public RedisKey() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        namespace = ((DynamicRedisProperties)applicationContext.getBean(DynamicRedisProperties.class)).getNamespace();
        Preconditions.checkNotNull(namespace, String.format("%s.namespace should not be empty", "longfor.data.redis"));
    }

    public static String join(Object... args) {
        StringBuilder key = new StringBuilder(namespace);
        Object[] var2 = args;
        int var3 = args.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Object arg = var2[var4];
            key.append(":").append(arg);
        }

        return key.toString();
    }
}

