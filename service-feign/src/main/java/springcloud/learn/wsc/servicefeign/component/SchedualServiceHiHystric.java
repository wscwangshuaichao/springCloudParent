package springcloud.learn.wsc.servicefeign.component;

import springcloud.learn.wsc.servicefeign.api.SchedualServiceHi;

/**
 * @author wangshuaichao
 * @ClassName: SchedualServiceHiHystric
 * @Decription TOO
 * @Date 2019/7/18 18:06
 **/
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
