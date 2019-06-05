package com.spring.security.core.service;

import com.spring.security.core.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ：miaoqs
 * @date ：2019-06-02 17:41
 * @description：获取用户的恶详情信息
 *
 * TODO 使用@Component的作用就是让MyUserDetailsService这个类变成一个Bean
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO 根据用户名查询用户信息 第一参数 代表用户的名称  第二个参数代表用户的密码  第三个参数代表用户的权限集合
        UserDTO user = userService.getUserByUserName(username);
        log.info("============user: username:{}, password: {}", user.getUsername(), user.getPassword());

        // TODO 这里的密码应该是加密以后的密码，这里是没有连接数据库，模拟数据库， 连接以后我们获取的密码是加密以后的
        String pwd = passwordEncoder.encode(user.getPassword());
        log.info("=============加密以前密码： {}===============加密以后密码： {}", user.getPassword(), pwd);
        return new User(user.getUsername(), pwd, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
