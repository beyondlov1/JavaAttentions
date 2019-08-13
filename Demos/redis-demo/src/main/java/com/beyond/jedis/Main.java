package com.beyond.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);//最大连接数
        jedisPoolConfig.setMaxIdle(20);//最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(6000);//获取可用连接的最大等待时间
        JedisPool jedisPool = new JedisPool(jedisPoolConfig);
        Jedis jedis = jedisPool.getResource();

        jedis.set("jedis", "jedis");
        String jedis1 = jedis.get("jedis");
        System.out.println(jedis1);

        jedis.lpush("jedisList", "go1");
        jedis.lpush("jedisList", "go2");
        jedis.lpush("jedisList", "go3");
        List<String> jedisList = jedis.lrange("jedisList", 0, 100);
        System.out.println(jedisList);

    }
}
