package com.beyond.jedisson;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        RList<Object> redissonList = redisson.getList("redisson");
        redissonList.add("sdfdf");
//        RList<String> jedisList = redisson.getList("jedisList");
//        List<String> list = jedisList.range(0,10);
//        System.out.println(list);
        RList<String> jedisList = redisson.getList("redisson");
        List<String> list = jedisList.range(0, 10);
        System.out.println(list);
        redisson.shutdown();
    }
}
