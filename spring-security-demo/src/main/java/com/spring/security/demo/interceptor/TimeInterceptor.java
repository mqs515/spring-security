package com.spring.security.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Mqs
 * @Date 2019/4/10 22:58
 * @Desc
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 方法执行之前被调用
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("=======preHandle=================方法执行之前被调用类名: {}", ((HandlerMethod)handler).getBean().getClass().getName());
        log.info("=======preHandle=================方法执行之前被调用方法名: {}", ((HandlerMethod)handler).getMethod().getName() );
        request.setAttribute("startTime", System.currentTimeMillis());

        // 为false 就是不执行请求方法
        return true;
    }

    /**
     * 方法执行中被调用 TODO 有异常就不会被调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 有异常就不会被调用
        log.info("=======postHandle=================方法执行中被调用: {}", ((HandlerMethod)handler).getBean().getClass().getName());
        long startTime = (long)request.getAttribute("startTime");
        log.info("=================postHandle===执行耗时： {}", System.currentTimeMillis() - startTime);
    }

    /**
     * 方法执行之后会被调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("=======afterCompletion=================方法执行之后会被调用: {}", ((HandlerMethod)handler).getBean().getClass().getName());
        long startTime = (long)request.getAttribute("startTime");
        log.info("=================afterCompletion===执行耗时： {}", System.currentTimeMillis() - startTime);
        // 打印异常 TODO 有异常postHandle不执行
        log.info("=================afterCompletion===异常信息： {}", ex.getMessage());


    }
}
