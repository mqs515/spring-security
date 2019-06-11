package com.spring.security.core.validator;

import com.spring.security.core.validator.code.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


/**
 * @author ：miaoqs
 * @date ：2019-06-11 19:56
 * @description：校验码处理器，封装不同校验码的处理逻辑
 */
public abstract class  AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor{

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的接口的实现 TODO 依赖查找
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 生成验证码
        C validateCode = generate(request);
        // 保存验证码
        save(request, validateCode);
        // 发送验证码
        send(request, validateCode);
    }

    /**
     * 生成校验码
     * @param request
     * @return
     */
    private C generate(ServletWebRequest request){
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 根据请求路径的URL获取校验码的类型，也就是请求路径的最后一段 image 或者是 sms
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/api/code/");
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * TODO 发送验证码，抽象方法，具体业务由子类实现
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

}
