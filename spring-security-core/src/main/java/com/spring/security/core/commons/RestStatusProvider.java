package com.spring.security.core.commons;

/**
 * @author ：miaoqs
 * @date ：2019-06-05 11:25
 * @description：状态码的统一规范
 */
public interface RestStatusProvider {

    int value();

    String getMsg();
}
