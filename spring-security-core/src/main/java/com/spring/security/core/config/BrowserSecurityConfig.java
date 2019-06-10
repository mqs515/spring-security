package com.spring.security.core.config;

import com.spring.security.core.authentication.MineAuthenticationFailHandler;
import com.spring.security.core.authentication.MineAuthenticationSuccessHandler;
import com.spring.security.core.commons.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：miaoqs
 * @date ：2019-06-03 18:50
 * @description：spring安全配置类
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MineAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MineAuthenticationFailHandler authenticationFailHandler;
    /**
     * @param http
     * @throws Exception
     *
     * TODO authorizeRequests 请求授权   anyRequest 任何请求  authenticated   都需要身份认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权的权限配置   TODO formLogin【表单登陆】 这个是页面进行登陆认证    httpBasic 【http方式的登陆】弹窗认证
        //设置登录,注销，表单登录不用拦截，其他请求要拦截
//        http.httpBasic()
        // 登陆以后直接进入的登陆页面
        http.formLogin().loginPage("/authentication/require")
                // 用户登录请求的action
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .and()
                // 这个页面不进行拦截
                .authorizeRequests()
                .antMatchers("/authentication/require", securityProperties.getBrowsers().getLoginPage()).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                // 关闭默认的csrf认证
                .and().csrf().disable();
    }
}
