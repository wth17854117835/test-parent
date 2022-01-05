package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test03_String {

    public static void main(String[] args) {
        // 1. new Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //常用的API
        System.out.println("增加数据："+jedis.set("k1", "v1"));
        System.out.println("增加数据："+jedis.set("k2", "v2"));
        System.out.println("增加数据："+jedis.set("k3", "v3"));
        System.out.println("删除键k2："+jedis.del("k2"));
        System.out.println("获取键k2："+jedis.get("k2"));
        System.out.println("修改k1："+jedis.set("k1","new"));
        System.out.println("获取键k1："+jedis.get("k1"));
        System.out.println("在k3后加入值："+jedis.append("k3","End"));
        System.out.println("获取键k3："+jedis.get("k3"));
        System.out.println("增加多个键值对："+jedis.mset("key01","value01","key02","value02","key03","value03"));
        System.out.println("获取多个键值对："+jedis.mget("key01","key02","key03"));
        System.out.println("删除多个键值对："+jedis.del("key01","key02","key03"));
        System.out.println("获取多个键值对："+jedis.mget("key01","key02","key03"));
        System.out.println("删除当前数据库中的所有key："+jedis.flushDB());
        System.out.println("返回当前数据库中key的数目："+jedis.dbSize());

        System.out.println("=========新增键值对防止覆盖原先值=========");
        System.out.println(jedis.setnx("key01", "value01"));
        System.out.println(jedis.setnx("key02", "value02"));
        System.out.println(jedis.setnx("key02", "value02-new"));
        System.out.println(jedis.get("key01"));
        System.out.println(jedis.get("key02"));

        System.out.println("=========新增键值对并设置有效时间=========");
        System.out.println(jedis.setex("k1", 2, "v1"));
        System.out.println(jedis.get("k1"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("k1"));

        System.out.println("=========获取原值，更新为新值=========");
        System.out.println(jedis.getSet("k1","k1GetSet"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.getrange("k1",2,4));

    }
}
