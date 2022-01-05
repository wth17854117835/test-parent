package com.sitech.wth.practice.kuangshen.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 11:20
 * @description:
 **/
public class Test04_List {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        System.out.println("添加一个List："+jedis.lpush("collections", "ArrayList","Vector","Stack","HashMap","HashMap","LinkedHashMap"));
        System.out.println(jedis.lpush("collections", "HashSet"));
        System.out.println(jedis.lpush("collections", "TreeSet"));
        System.out.println(jedis.lpush("collections", "TreeMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("collections区间0-3的内容：" + jedis.lrange("collections", 0,3));

        System.out.println("删除指定元素个数："+jedis.lrem("collections", 2, "HashMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("截取collections区间0-3的内容：" + jedis.ltrim("collections", 0,3));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("collections列表出栈（左端）：" + jedis.lpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("collections添加元素，从列表右端"+jedis.rpush("collections", "EnumMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("collections列表出栈（右端）：" + jedis.rpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));
        System.out.println("修改指定下标1的内容：" + jedis.lset("collections", 1,"LinkedArrayList"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0,-1));

        System.out.println("collections的长度：" + jedis.llen("collections"));
        System.out.println("获取下标2的元素：" + jedis.lindex("collections", 2));

        System.out.println(jedis.lpush("sortedList", "3","6","2","0","7","4"));
        System.out.println("sortedList排序前：" + jedis.lrange("sortedList", 0,-1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后：" + jedis.sort("sortedList"));

    }
}
