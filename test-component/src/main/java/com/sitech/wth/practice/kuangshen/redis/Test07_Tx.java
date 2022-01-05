package com.sitech.wth.practice.kuangshen.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description: 事务
 **/
public class Test07_Tx {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "wangth");
        jsonObject.put("age", 18);
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("user1",jsonObject.toJSONString());
            multi.set("user2",jsonObject.toJSONString());
            //模拟运行时异常
            int i = 1/0;

            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();//关闭连接
        }

        jedis.flushDB();
    }
}
