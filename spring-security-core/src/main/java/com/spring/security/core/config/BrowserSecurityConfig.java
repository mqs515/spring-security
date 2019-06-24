package com.spring.security.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.security.core.authentication.MineAuthenticationFailHandler;
import com.spring.security.core.authentication.MineAuthenticationSuccessHandler;
import com.spring.security.core.commons.SecurityProperties;
import com.spring.security.core.service.MyUserDetailsService;
import com.spring.security.core.validator.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    /**
     * 记住我会将token放入数据库
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 在启动的时候会自动创建表【第一次启动的时候会自动创建表，之后就不用创建表了，可以注销掉】
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * @param http
     * @throws Exception
     *
     * TODO authorizeRequests 请求授权   anyRequest 任何请求  authenticated   都需要身份认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(authenticationFailHandler);

        // 授权的权限配置   TODO formLogin【表单登陆】 这个是页面进行登陆认证    httpBasic 【http方式的登陆】弹窗认证
        //设置登录,注销，表单登录不用拦截，其他请求要拦截
//        http.httpBasic()
        // 登陆以后直接进入的登陆页面
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    // 用户登录请求的action
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailHandler)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowsers().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                // 这个页面不进行拦截
                    .authorizeRequests()
                    .antMatchers("/authentication/require",
                            securityProperties.getBrowsers().getLoginPage(),
                            "/code/*").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout().permitAll()
                // 关闭默认的csrf认证
                .and()
                    .csrf().disable();
    }
}
