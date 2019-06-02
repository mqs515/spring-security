package com.spring.security.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringSecurityCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCoreApplication.class, args);
    }
}
