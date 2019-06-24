package com.spring.security.core.validator;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：miaoqs
 * @date ：2019-06-11 19:56
 * @description：校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
