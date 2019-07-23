package springcloud.learn.wsc.servicehi.config;

import com.longfor.gf.lc.report.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author xupeng
 * time 2019/5/15
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Resource
    private AuthInterceptor authInterceptor;

    /**
     * 拦截器(拦截器方式)
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**")
                .excludePathPatterns("/error");
    }
}

