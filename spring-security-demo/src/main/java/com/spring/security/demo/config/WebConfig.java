package com.spring.security.demo.config;

import com.spring.security.core.filter.TimeFilter;
import com.spring.security.core.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.ArrayList;

/**
 * @Author Mqs
 * @Date 2019/4/10 22:44
 * @Desc 方便加载第三方过滤器
 */
@Configuration
public class   WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    /**
     * spring容器初始化加载该bean
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        // 被加载的过滤器
        TimeFilter timeFilter = new TimeFilter();
        filterFilterRegistrationBean.setFilter(timeFilter);

        // 指定哪些路径会被过滤 TODO hello/test01这个路径会被过滤
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/hello/test01");
        filterFilterRegistrationBean.setUrlPatterns(urls);
        return filterFilterRegistrationBean;
    }
}

