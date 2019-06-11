package com.spring.security.core.validator.code;

import com.spring.security.core.commons.Conts;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author ：miaoqs
 * @date ：2019-06-11 16:33
 * @description：默认短信验证码实现
 */
@Component(value = "DefaultSmsCodeSender")
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code);
        String sms = Conts.SMS_CODE;
        for (String key : map.keySet()) {
            sms = sms.replace("{" + key + "}", map.get(key));
        }
        System.out.println("=======================: " + sms);
    }
};
