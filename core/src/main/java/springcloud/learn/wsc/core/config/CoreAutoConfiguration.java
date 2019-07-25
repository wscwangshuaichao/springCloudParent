package springcloud.learn.wsc.core.config;


import org.springframework.context.annotation.Import;

/**
 * @author wangshuaichao
 * @ClassName: CoreAutoConfiguration
 * @Decription TOO
 * @Date 2019/7/25 16:27
 **/
@Import({
        SpringContextHolder.class,
})
public class CoreAutoConfiguration {
}
