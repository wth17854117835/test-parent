package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test02_Key {

    public static void main(String[] args) {
        // 1. new Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //常用的API
        System.out.println("清空当前库的数据："+jedis.flushDB());
        System.out.println("清空所有库的数据："+jedis.flushAll());
        System.out.println("判断某个键是否存在："+jedis.exists("username"));
        System.out.println("新增<'username','wangth'>的键值对："+jedis.set("username","wangth"));
        System.out.println("新增<'password','xxx'>的键值对："+jedis.set("password","xxx"));
        System.out.println("系统中所有的键值对如下："+  jedis.keys("*"));
        System.out.println("删除键password："+jedis.del("password"));
        System.out.println("判断password键是否存在："+jedis.exists("password"));
        System.out.println("查看username键所存储值的类型："+jedis.type("username"));
        System.out.println("随机返回key空间的一个："+jedis.randomKey());
        System.out.println("重命名key："+jedis.rename("username","name"));
        System.out.println("取出改后的name："+jedis.get("name"));
        System.out.println("按索引查询："+jedis.select(0));
        System.out.println("删除当前数据库中的所有key："+jedis.flushDB());
        System.out.println("返回当前数据库中key的数目："+jedis.dbSize());
    }
}
