package springcloud.learn.wsc.servicehi.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
* @ClassName: MybatisPlusConfig.java
* @Description: mybatisplus配置
* @author: ChongLi
* @date: 2019/5/17 16:08
* @version V1.0
*/
@Configuration
@MapperScan("com.longfor.gf.lc.op.endpoint.dao")
public class MybatisPlusConfig {

    /**
     * 分页配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
