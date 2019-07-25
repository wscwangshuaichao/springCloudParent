package springcloud.learn.wsc.dataredis.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;

/**
 * @author wangshuaichao
 * @ClassName: LFRedisCacheManager
 * @Decription TOO
 * @Date 2019/7/25 15:12
 **/
public class LFRedisCacheManager extends RedisCacheManager {
    private static final Logger log = LoggerFactory.getLogger(LFRedisCacheManager.class);

    public LFRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public Cache getCache(String name) {
        return new LFRedisCacheManager.RedisCacheWrapper(super.getCache(name));
    }

    protected static class RedisCacheWrapper implements Cache {
        private final Cache delegate;

        public RedisCacheWrapper(Cache redisCache) {
            Assert.notNull(redisCache, "delegate cache must not be null");
            this.delegate = redisCache;
        }

        public String getName() {
            try {
                return this.delegate.getName();
            } catch (Exception var2) {
                return (String)this.handleException(var2);
            }
        }

        public Object getNativeCache() {
            try {
                return this.delegate.getNativeCache();
            } catch (Exception var2) {
                return this.handleException(var2);
            }
        }

        public ValueWrapper get(Object key) {
            try {
                return this.delegate.get(key);
            } catch (Exception var3) {
                return (ValueWrapper)this.handleException(var3);
            }
        }

        public <T> T get(Object o, Class<T> aClass) {
            try {
                return this.delegate.get(o, aClass);
            } catch (Exception var4) {
                return this.handleException(var4);
            }
        }

        public <T> T get(Object o, Callable<T> callable) {
            try {
                return this.delegate.get(o, callable);
            } catch (Exception var4) {
                return this.handleException(var4);
            }
        }

        public void put(Object key, Object value) {
            try {
                this.delegate.put(key, value);
            } catch (Exception var4) {
                this.handleException(var4);
            }

        }

        public ValueWrapper putIfAbsent(Object o, Object o1) {
            try {
                return this.delegate.putIfAbsent(o, o1);
            } catch (Exception var4) {
                return (ValueWrapper)this.handleException(var4);
            }
        }

        public void evict(Object o) {
            try {
                this.delegate.evict(o);
            } catch (Exception var3) {
                this.handleException(var3);
            }

        }

        public void clear() {
            try {
                this.delegate.clear();
            } catch (Exception var2) {
                this.handleException(var2);
            }

        }

        private <T> T handleException(Exception e) {
            LFRedisCacheManager.log.error("handleException, {}", e.getMessage());
            return null;
        }
    }
}
