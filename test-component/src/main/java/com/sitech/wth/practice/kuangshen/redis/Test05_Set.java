package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test05_Set {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        System.out.println("添加一个Set（不重复）："+jedis.sadd("set", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("set", "e6"));
        System.out.println(jedis.sadd("set", "e6"));
        System.out.println("set所有元素为：" + jedis.smembers("set"));
        System.out.println("删除一个元素e0：" + jedis.srem("set", "e0"));
        System.out.println("set所有元素为：" + jedis.smembers("set"));
        System.out.println("删除两个元素e7,e8：" + jedis.srem("set", "e7","e8"));
        System.out.println("set所有元素为：" + jedis.smembers("set"));
        System.out.println("随机移除一个元素：" + jedis.spop("set"));
        System.out.println("随机移除一个元素：" + jedis.spop("set"));
        System.out.println("set所有元素为：" + jedis.smembers("set"));
        System.out.println("set包含元素的个数为：" + jedis.scard("set"));
        System.out.println("e1是否在set中：" + jedis.sismember("set","e1"));
        System.out.println("e8是否在set中：" + jedis.sismember("set","e8"));

        System.out.println("添加一个set1："+jedis.sadd("set1", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println("添加一个set2："+jedis.sadd("set2", "e1","e2","e3","e0","e8"));
        System.out.println("将set1中删除e5并保存到set2中："+jedis.smove("set1", "set2","e5"));
        System.out.println("将set1中删除e7并保存到set2中："+jedis.smove("set1", "set2","e7"));
        System.out.println("set1和set2的交集："+jedis.sinter("set1", "set2"));
        System.out.println("set1和set2的并集："+jedis.sunion("set1", "set2"));
        System.out.println("set1和set2的差集："+jedis.sdiff("set1", "set2"));
        System.out.println("set1和set2的交集，并将交集保存到新的集合中："+jedis.sinterstore("set4","set1", "set2"));
        System.out.println("set4所有元素为："+jedis.smembers("set4"));
        jedis.flushDB();
    }
}
