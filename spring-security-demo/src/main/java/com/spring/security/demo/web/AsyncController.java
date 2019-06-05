package com.spring.security.demo.web;

import com.spring.security.core.async.DeferredResultHolder;
import com.spring.security.core.dto.MockQueue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author ：miaoqs
 * @date ：2019-06-01 17:43
 * @description：同步/异步线程处理
 */
@Api(tags = "同步/异步线程处理")
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 主线程执行以后会立刻返回
     * 副线程会慢慢执行具体的业务逻辑
     * @return
     * @throws Exception
     */
    @GetMapping("/order")
    @ApiOperation(value = " 使用Runnable异步处理", notes = "使用Runnable异步处理")
    public Callable<String> order() throws Exception{
        log.info("===========主线程=============开始执行");

        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("===========副线程===========开始执行");
                Thread.sleep(1000);
                log.info("===========副线程============执行结束");
                return "SUCCESS";
            }
        };
        log.info("===========主线程执行===============结束");
        return result;
    }

    @GetMapping("/orderDr")
    @ApiOperation(value = " 使用DeferredResult异步处理", notes = "使用DeferredResult异步处理")
    public DeferredResult<String> orderDr() throws Exception{
        log.info("===========主线程=============开始执行");
        String orderNumb = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumb);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumb, result);

        log.info("===========主线程执行===============结束");
        return result;
    }

    /**
     * TODO    @Async 使用这个注解要配合@EnableAsync一起使用
     * @return
     * @throws Exception
     */
    @GetMapping("/asyncAnna")
    @ApiOperation(value = " 使用注解异步处理", notes = "使用注解异步处理")
    @Async
    public String asyncAnna() throws Exception{
        log.info("===========主线程=============开始执行");
        try {
            // TODO 这个会重新开辟一个线程执行以下的输出方法 10s以后会打印到控制台
            Thread.sleep(10000);
            System.out.println("45678909765678");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("===========主线程执行===============结束");
        return "SUCCESS";
    }

}
