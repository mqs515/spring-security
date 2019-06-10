package com.spring.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.core.commons.SecurityProperties;
import com.spring.security.core.commons.SimpleResponse;
import com.spring.security.core.enums.LoginTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 16:23
 * @description：失败处理器
 */
@Component
@Slf4j
public class MineAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("===============登陆失败");
        if (LoginTypeEnum.JSON.equals(securityProperties.getBrowsers().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}

