package com.spring.security.demo.aspact;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ：miaoqs
 * @date ：2019-06-01 15:12
 * @description：测试时间的切面
 * TODO 这是一个切片类
 */

@Aspect
@Component
@Slf4j
public class TimeAspect {

    /**
     *  TODO 切入点【注解】   1、在那些方法上起作用  2、在什么时候起作用
     *
     *  第一个* 代表任何的返回值都可以
     *  第二个.*代表该controller下的任何的方法都可以
     *  第三个 (..) 代表每个方法中的任何的参数列表都可以的
     */
    @Around("execution(* com.spring.security.demo.web.UserController.*(..))")
    public Object handleControllermethod(ProceedingJoinPoint point) throws Throwable{
        System.out.println("==================time aspect start");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("arg is:  " + arg);
        }
        long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        log.info("===========时间切面的执行时间为：============{}", System.currentTimeMillis() - startTime);
        System.out.println("==================time aspect end");
        return obj;
    }

}
