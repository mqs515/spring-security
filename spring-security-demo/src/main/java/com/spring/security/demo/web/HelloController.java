package com.spring.security.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Mqs
 * @Date 2018/11/26 23:46
 * @Desc
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/test01")
    public String testHello(){
        return "Hello Spring Security";
    }
}
