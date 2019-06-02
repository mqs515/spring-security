package com.spring.security.core.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：miaoqs
 * @date ：2019-06-02 10:42
 * @description：处理多线程
 */
@Component
public class DeferredResultHolder {

    /**
     * 范性为  每个订单号   对应一个订单处理结果
     */
    private Map<String, DeferredResult<String>> map = new HashMap<>();


    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
