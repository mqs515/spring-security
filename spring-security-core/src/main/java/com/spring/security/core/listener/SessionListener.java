package com.spring.security.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author Mqs
 * @Date 2019/5/1 18:43
 * @Desc 监听器在java web中应用的较多，比如：统计当前在线人数、自定义session扫描器。
 */
public class SessionListener implements HttpSessionListener {

    /**
     * TODO  web容器启动以后初始化当前的在线人数为0
     */
    public static int TOTAL_ONLINE_USERS = 0;
    /**
     * TODO 监听器的初始化
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO 用户登录
        ServletContext servletContext = se.getSession().getServletContext();
        TOTAL_ONLINE_USERS = (Integer) servletContext.getAttribute("TOTAL_ONLINE_USERS");
        if (TOTAL_ONLINE_USERS == 0){
            servletContext.setAttribute("TOTAL_ONLINE_USERS", 1);
        }else {
            TOTAL_ONLINE_USERS++;
            servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
        }
    }

    /**
     * TODO 监听器的销毁
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO 用户退出
        ServletContext servletContext = se.getSession().getServletContext();
        TOTAL_ONLINE_USERS = (Integer) servletContext.getAttribute("TOTAL_ONLINE_USERS");
        if (TOTAL_ONLINE_USERS == 0){
            servletContext.setAttribute("TOTAL_ONLINE_USERS", 1);
        }else {
            TOTAL_ONLINE_USERS--;
            servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
        }
    }
}
