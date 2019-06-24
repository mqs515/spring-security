package com.spring.security.core.validator.code;

import com.spring.security.core.commons.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 21:08
 * @description：短信验证码
 */
@Component("smsValidateCodeGenerator")
@Slf4j
public class SmsCodeGenerator implements ValidateCodeGenerator{

	@Autowired
    @Getter
    @Setter
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        log.info("==============短信验证码============  {}", code);
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
