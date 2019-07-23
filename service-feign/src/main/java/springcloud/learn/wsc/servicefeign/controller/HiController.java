package springcloud.learn.wsc.servicefeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.learn.wsc.servicefeign.api.SchedualServiceHi;

/**
 * @author wangshuaichao
 * @ClassName: HiController
 * @Decription TOO
 * @Date 2019/7/18 16:16
 **/
@RestController
public class HiController {

    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping(value="/hi/{name}")
    public String sayHiFromClientOne(@PathVariable String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
