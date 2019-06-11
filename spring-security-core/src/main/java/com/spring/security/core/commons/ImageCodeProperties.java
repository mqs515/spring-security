package com.spring.security.core.commons;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties {

	@ApiModelProperty("图形验证码的宽度默认值")
	private int width = 67;

	@ApiModelProperty("图形验证码的高度默认值")
	private int height = 23;

	public ImageCodeProperties() {
		setLength(4);
	}
}
