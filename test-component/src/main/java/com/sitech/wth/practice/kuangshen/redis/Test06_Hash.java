package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test06_Hash {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.put("k4","v4");
        System.out.println("添加一个Hash："+jedis.hmset("hash", map));
        System.out.println("添加一个Hash："+jedis.hset("hash", "k5","v5"));
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));
        System.out.println("散列hash的所有键为："+jedis.hkeys("hash"));
        System.out.println("散列hash的所有值为："+jedis.hvals("hash"));
        System.out.println("删除一个或多个键值对："+jedis.hdel("hash", "k5","k4"));
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));
        System.out.println("散列hash的个数为："+jedis.hlen("hash"));
        System.out.println("判断hash中是否存在k2："+jedis.hexists("hash","k2"));
        System.out.println("判断hash中是否存在k3："+jedis.hexists("hash","k3"));
        System.out.println("散列hash中的值："+jedis.hmget("hash","k3"));
        jedis.flushDB();
    }
}
