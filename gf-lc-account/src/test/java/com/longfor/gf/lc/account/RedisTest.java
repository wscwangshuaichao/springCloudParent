//package com.longfor.gf.lc.account;
//
//import com.longfor.gf.lc.account.service.RedisLockService;
//import com.longfor.gf.lc.account.service.RedisService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
///**
// * @author guoguangxiao
// * @date 2019/5/16 15:17:31
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RedisTest {
//
//    @Resource
//    private RedisLockService redisLockService;
//    @Resource
//    private RedisService redisService;
//
//    @Test
//    public void testLock(){
//        boolean result = redisLockService.lock("aaaa", 2000, 30000);
//        System.out.println("==========="+result);
//        redisLockService.unlock("aaaa");
//        System.out.println("==========="+result);
//
//        boolean bbb = redisService.rateLimit("bbb", 200, 4);
//        System.out.println("==========="+bbb);
//
//
//    }
//
//
//
//}
