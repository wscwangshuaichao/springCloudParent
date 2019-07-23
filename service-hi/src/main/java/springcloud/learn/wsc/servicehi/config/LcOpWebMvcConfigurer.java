package springcloud.learn.wsc.servicehi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @ClassName: LcOpWebMvcConfigurer.java
* @Description: 配置
* @author: ChongLi
* @date: 2019/5/24 13:08
* @version V1.0
*/
@Configuration
public class LcOpWebMvcConfigurer  extends WebMvcConfigurerAdapter {
    @Bean
    public LcOpHandlerInterceptor lcOpHandlerInterceptor(){
        return new LcOpHandlerInterceptor() ;
    }
    @Bean
    public LcOpLoginInterceptor lcOpLoginInterceptor(){
        return new LcOpLoginInterceptor() ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lcOpHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(lcOpLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/toLogin*/**").
                excludePathPatterns("/toLoginTest*/**").
                excludePathPatterns("/swagger*/**").
                excludePathPatterns("/health").
                excludePathPatterns("/opApi/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600)
                .allowCredentials(true);
    }


}
