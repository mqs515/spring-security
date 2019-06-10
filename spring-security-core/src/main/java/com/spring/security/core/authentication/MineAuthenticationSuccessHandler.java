package com.spring.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.core.commons.SecurityProperties;
import com.spring.security.core.enums.LoginTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 16:09
 * @description：请求成功跳转页
 */
@Component
@Slf4j
public class MineAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //Authentication接口封装认证信息
        log.info("===============登录成功");
        if (LoginTypeEnum.JSON.equals(securityProperties.getBrowsers().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            //将authentication认证信息转换为json格式的字符串写到response里面去
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
