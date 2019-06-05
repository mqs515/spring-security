package com.spring.security.core.config;

import com.alibaba.fastjson.JSON;
import com.spring.security.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author ：miaoqs
 * @date ：2019-06-05 11:18
 * @description：异步方式的配置
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(5000);
        executor.setThreadNamePrefix("FansYoufan-AsyncThread-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                log.error(
                        "Async method: {} has uncaught exception,params:{}",
                        method.getName(),
                        JSON.toJSONString(params));

                if (ex instanceof ServiceException) {
                    ServiceException exception = (ServiceException) ex;
                    log.error(
                            "service asyncException: code={}, message={}", exception.getStatus(), exception.getStatus());
                } else {
                    log.error("other asyncException: ", ex);
                }
            }
        };
    }
}
