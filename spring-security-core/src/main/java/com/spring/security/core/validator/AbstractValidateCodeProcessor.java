package com.spring.security.core.validator;

import com.spring.security.core.validator.code.ValidateCode;
import com.spring.security.core.validator.code.ValidateCodeException;
import com.spring.security.core.validator.code.ValidateCodeGenerator;
import com.spring.security.core.validator.code.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


/**
 * @author ：miaoqs
 * @date ：2019-06-11 19:56
 * @description：校验码处理器，封装不同校验码的处理逻辑
 */
@Slf4j
public abstract class  AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{

    /**
     * 收集系统中所有的接口的实现 TODO 依赖查找
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 生成验证码
        C validateCode = generate(request);
        log.info("=======================这个是什么===============================: {}", validateCode);
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
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * TODO 发送验证码，抽象方法，具体业务由子类实现
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);

        C codeInSession = (C) validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }

        validateCodeRepository.remove(request, codeType);

    }
}
