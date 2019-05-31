package com.spring.security.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author Mqs
 * @Date 2019/4/30 21:36
 * @Desc TODO 用来做测试的不用关注
 */
public class TimerListener implements ServletContextListener {

    /**
     * 现在来说说Servlet的监听器Listener，它是实现了javax.servlet.ServletContextListener 接口的
     * 服务器端程序，它也是随web应用的启动而启动，只初始化一次，随web应用的停止而销毁。主要作用是：做一些初始化
     * 的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。
     *
     * 示例代码：使用监听器对数据库连接池DataSource进行初始化
     */

    /**
     * TODO 应用监听器的初始化方法
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // web容器销毁，清空参数
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("data");
    }

    /**
     * TODO 应用监听器的销毁方法
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 设置全局参数
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("data", "data");
    }
}
