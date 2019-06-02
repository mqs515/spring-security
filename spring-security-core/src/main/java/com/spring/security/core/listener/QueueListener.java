package com.spring.security.core.listener;

import com.spring.security.core.async.DeferredResultHolder;
import com.spring.security.core.dto.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author ：miaoqs
 * @date ：2019-06-02 10:54
 * @description：下单完成的监听器
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(() ->{
            while (true){
                if (!StringUtils.isEmpty(mockQueue.getCompleteOrder())){
                    String completeOrder = mockQueue.getCompleteOrder();
                    log.info("================返回订单处理结果： " + completeOrder);
                    deferredResultHolder.getMap().get(completeOrder).setResult("==================place order success");


                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
