package com.sitech.wth.service.impl;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: wangth
 * @create: 2022-02-19 17:17
 * @description：
 */
@RestController
@RequestMapping(value = "/redis")
public class IndexController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    /**
     * 减库存，synchronized在集群部署情况下是有问题的，需要使用分布式锁，setnx实现
     * @return
     */
    @RequestMapping("/deductStock")
    public String deductStock() {
        String lockKey = "product_101";
//        String clientId = UUID.randomUUID().toString();
        //setnx 分布式锁
        //如果系统宕机，重启不会执行 finally 的释放锁。解决方法：设置超时时间 10s
//        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId,10,TimeUnit.SECONDS);
//        stringRedisTemplate.expire(lockKey,10, TimeUnit.SECONDS);

//        if (!result) {
//            return "error end";
//        }

        RLock redissonLock = redisson.getLock(lockKey);

//        synchronized (this) {
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            //如果是自己加的锁，就可以释放锁，否则不能删除锁
//            if(clientId.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(lockKey))){
//                stringRedisTemplate.delete(lockKey);
//            }
            redissonLock.unlock();
        }
//        }
        return "end";
    }
}
