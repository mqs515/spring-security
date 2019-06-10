package com.spring.security.core.validator.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 21:08
 * @description：处理验证码异常
 */
public class ValidateCodeException extends AuthenticationException{

	private static final long serialVersionUID = -3293874279425052878L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
