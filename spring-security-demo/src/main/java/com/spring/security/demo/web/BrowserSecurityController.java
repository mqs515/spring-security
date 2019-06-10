package com.spring.security.demo.web;

import com.spring.security.core.commons.SecurityProperties;
import com.spring.security.core.commons.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 14:05
 * @description：登陆
 */
@Api(tags = "测试区分登陆的是HTML还是CON")
@RestController
@RequestMapping("/authentication")
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ApiOperation(value = "当需要身份认证时，跳转到这里", notes = "当需要身份认证时，跳转到这里")
    public SimpleResponse require(HttpServletRequest request, HttpServletResponse response)throws Exception{
        // 判断引发跳转的时
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("=================引发跳转的请求是： {}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")){
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowsers().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证， 请引导用户到登陆页");

    }
}
