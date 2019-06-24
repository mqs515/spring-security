package com.spring.security.demo.web;

import com.spring.security.core.validator.ValidateCodeProcessorHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：miaoqs
 * @date ：2019-06-01 17:43
 * @description：图形验证码
 */

@Slf4j
@RestController
@RequestMapping("/code")
@Api(tags = "验证码")
public class ValidatorController {

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	@GetMapping("/{type}")
	@ApiOperation(value = "图片/手机验证码", notes = "图片/手机验证码")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)throws Exception{
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

}
