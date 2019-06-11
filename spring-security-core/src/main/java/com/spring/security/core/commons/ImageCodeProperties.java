package com.spring.security.core.commons;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImageCodeProperties {

	@ApiModelProperty("图形验证码的宽度默认值")
	private int width = 67;

	@ApiModelProperty("图形验证码的高度默认值")
	private int height = 23;

	@ApiModelProperty("图形验证码的数字个数默认值")
	private int length = 4;

	@ApiModelProperty("图形验证码的宽度过期时间默认值")
	private int expireIn = 60;

	@ApiModelProperty("图形验证码的URL默认值")
	private String url;
	
}
