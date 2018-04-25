package com.example.demo;

import redis.clients.jedis.Jedis;

/**
 * Created by xin on 4/20/2018.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("正在运行"+jedis.ping());
        jedis.set("name","www");
        System.out.println("输出"+jedis.get("name"));
    }
}
