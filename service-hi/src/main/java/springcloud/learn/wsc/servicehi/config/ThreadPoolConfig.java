package springcloud.learn.wsc.servicehi.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
* @ClassName: ThreadPoolConfig.java
* @Description: 线程池配置
* @author: ChongLi
* @date: 2019/5/28 1:44
* @version V1.0
*/
@Data
@Configuration
@ConfigurationProperties(prefix = "thread")
@Component
public class ThreadPoolConfig {
    private int corePoolSize = 5 ;
    private int maxPoolSize = 20 ;
    private int maxQueue = 64;
    private String namePrefix = "lc_op_threadPool_" ;
    private int keepAlive = 180 ;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(corePoolSize);
        // 指定最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 队列中最大的数目
        executor.setQueueCapacity(maxQueue);
        // 线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(keepAlive);
        // 线程名称前缀
        executor.setThreadNamePrefix(namePrefix);//
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 加载
        executor.initialize();
        return executor;
    }

}
