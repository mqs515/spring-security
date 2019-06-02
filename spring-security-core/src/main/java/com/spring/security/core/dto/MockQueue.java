package com.spring.security.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ：miaoqs
 * @date ：2019-06-02 10:32
 * @description：模拟下单和下单完的消息队列
 */
@ApiModel(value = "MockQueue")
@Component
@Slf4j
public class MockQueue {

    /**
     * 下订单
     */
    @ApiModelProperty("placeOrder")
    private String placeOrder;

    /**
     * 订单完成
     */
    @ApiModelProperty("completeOrder")
    private String completeOrder;


    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() ->{
            try {
                log.info("=============接到下单请求， " + placeOrder);
                Thread.sleep(1000);
                this.completeOrder = placeOrder;
                log.info("==============下单请求处理完成， " + placeOrder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
