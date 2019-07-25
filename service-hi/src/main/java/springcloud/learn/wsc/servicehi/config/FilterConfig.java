package springcloud.learn.wsc.servicehi.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author wangshuaichao
 * @Description //TODO
 * @Date 17:28 2019/7/24
 * @Param
 * @return
 * @throws
 **/
@Configuration
public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean registFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new LcOpFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("lcOpFilter");
//        registration.setOrder(1);
//        return registration;
//    }

//    /**
//    * @description: 第二种：过滤器跨域配置
//    * @date: 2019/5/24 13:47
//    */
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setMaxAge(3600L);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");//("POST, GET, PUT, OPTIONS, DELETE");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
}
