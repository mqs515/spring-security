package com.spring.security.core.validator.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 21:08
 * @description：验证码生成接口
 */
public interface ValidateCodeGenerator {
	ImageCode generate(ServletWebRequest request);
}
