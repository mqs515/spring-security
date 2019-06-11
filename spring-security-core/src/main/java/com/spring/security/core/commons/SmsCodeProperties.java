package com.spring.security.core.commons;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SmsCodeProperties {

	@ApiModelProperty("短信验证码的数字个数默认值")
	private int length = 6;

	@ApiModelProperty("短信验证码的宽度过期时间默认值")
	private int expireIn = 60;

	@ApiModelProperty("短信验证码的URL默认值")
	private String url;
	
}
