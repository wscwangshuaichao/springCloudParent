package com.longfor.gf.lc.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author guoguangxiao
 * @date 2019/5/15 11:50:32
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class GfLcAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(GfLcAccountApplication.class, args);
    }

}
