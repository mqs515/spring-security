package com.spring.security.demo.web;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.security.core.commons.Conts;
import com.spring.security.core.validator.ValidateCodeProcessor;
import com.spring.security.core.validator.code.ImageCode;
import com.spring.security.core.validator.code.SmsCodeSender;
import com.spring.security.core.validator.code.ValidateCode;
import com.spring.security.core.validator.code.ValidateCodeGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

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
	
//	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//	@Autowired
//	private ValidateCodeGenerator imageCodeGenerator;
//
//	@Qualifier("SmsCodeGenerator")
//	@Autowired
//	private ValidateCodeGenerator smsCodeGenerator;
//
//	@Autowired
//	private SmsCodeSender smsCodeSender;

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	@GetMapping("/{type}")
	@ApiOperation(value = "图片/手机验证码", notes = "图片/手机验证码")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)throws Exception{
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}
	
//	@GetMapping("/image")
//	@ApiOperation(value = "图片验证码", notes = "图片验证码")
//	public void createCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		//生成图形验证码
//		ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
//		//将图形验证码保存到session中
//		sessionStrategy.setAttribute(new ServletWebRequest(request), Conts.SESSION_KEY, imageCode);
//		//将生成的图形验证码回写到页面上
//		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//	}
//
//	@GetMapping("/sms")
//	@ApiOperation(value = "短信验证码", notes = "短信验证码")
//	public void createSmsCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		//生成短信验证码
//		ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
//		//将手机验证码保存到session中
//		sessionStrategy.setAttribute(new ServletWebRequest(request), Conts.SESSION_KEY, smsCode);
//		//连接短信商发短信验证码
//		String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
//		smsCodeSender.send(mobile, smsCode.getCode());
//
//
//	}
	
	@GetMapping("/user")
	public void helloWorld(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("hello world");
	}
}
