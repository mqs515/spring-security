package com.spring.security.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author Mqs
 * @Date 2019/4/10 22:17
 * @Desc
 */
@Slf4j
//@Component  TODO 第一种方式就是使用这个注解服务启动直接扫描这个文件但是要放到要使用的磨块才有效果，第二种方式不适用注解直接使用配置文件
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO 可以获取IOC中的各种bean
//        ServletContext servletContext = filterConfig.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        Object bean = webApplicationContext.getBean("bean");
        log.info("========================Time Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("=======================Time Filter Start");
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("===========这个请求的执行时间为：============{}", System.currentTimeMillis() - startTime);
        log.info("=======================Time Filter Finish");
    }

    @Override
    public void destroy() {
        log.info("========================Time Filter Destory");
    }
}
