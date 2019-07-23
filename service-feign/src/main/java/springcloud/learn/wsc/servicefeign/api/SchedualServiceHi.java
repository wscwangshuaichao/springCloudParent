package springcloud.learn.wsc.servicefeign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springcloud.learn.wsc.servicefeign.component.SchedualServiceHiHystric;

/**
 * @author wangshuaichao
 * @ClassName: ServiceHiApi
 * @Decription TOO
 * @Date 2019/7/18 16:09
 **/
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @GetMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
