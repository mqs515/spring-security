package com.spring.security.demo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Mqs
 * @Date 2018/11/26 23:46
 * @Desc
 */
@Api(tags = "测试hello")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "测试hello", notes = "测试hello")
    @GetMapping("/test01")
    public String testHello(){
        stringRedisTemplate.opsForValue().set("key", "value");
        String key = stringRedisTemplate.opsForValue().get("key");

        System.out.println(key);
        return "Hello Spring Security";

    }
}
