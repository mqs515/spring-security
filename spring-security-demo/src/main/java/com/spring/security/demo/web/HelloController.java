package com.spring.security.demo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "测试hello", notes = "测试hello")
    @GetMapping("/test01")
    public String testHello(){
        return "Hello Spring Security";
    }
}
