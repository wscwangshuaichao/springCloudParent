package springcloud.learn.wsc.servicehi.config;

import com.longfor.gf.lc.h5.endpoint.interceptor.H5EndPointInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhangjianbing
 * time 2019/5/22
 */
@Configuration
public class H5EndPointWebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public H5EndPointInterceptor h5EndPointInterceptor() {
        return new H5EndPointInterceptor();
    }

    /**
     * 拦截器(拦截器方式)
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(h5EndPointInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui.html/**", "/error");
        super.addInterceptors(registry);
    }

}
