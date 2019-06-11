package com.spring.security.core.validator.code;

/**
 * @author ：miaoqs
 * @date ：2019-06-11 16:30
 * @description：短信验证码接口
 */
public interface SmsCodeSender {

    void send(String mobile, String code );
}
