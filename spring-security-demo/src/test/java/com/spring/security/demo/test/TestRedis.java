package com.spring.security.demo.test;

import com.spring.security.core.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author ：miaoqs
 * @date ：2019-06-02 20:32
 * @description：测试阿里云Reddis
 */
public class TestRedis {

    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        try {
            JedisShardInfo jedisShardInfo = new JedisShardInfo("94.191.56.213", 6379);
            jedisShardInfo.setPassword("mqslovelry");
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis(jedisShardInfo);
            System.out.println("连接成功");
            //设置 redis 字符串数据
            jedis.set("emooco", "www.emooco.com");
            // 获取存储的数据并输出
            System.out.println("redis 存储的字符串为: "+ jedis.get("emooco"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        int i = 5;
        int t = 6;

        System.out.println(i%t);
        System.out.println(i/t);
    }
}
