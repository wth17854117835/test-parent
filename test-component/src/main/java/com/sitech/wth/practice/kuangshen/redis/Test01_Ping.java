package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test01_Ping {

    public static void main(String[] args) {
        // 1. new Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //jedis所有的命令都是redis所有的指令
        String ping = jedis.ping();
        System.out.println(ping); //PONG
        //常用的API
        //1.String

        //2.List

        //3.Hash

        //4.Set

        //5.Zset
    }
}
