package com.spring.security.core.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author Mqs
 * @Date 2019/3/31 18:21
 * @Desc TODO 这里不用使用@Component
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConst, Object> {

    @Override
    public void initialize(MyConst constraintAnnotation) {
        System.out.println("======================: 初始化方法");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("======================: 校验方法");
        System.out.println("===========================value: " + value);
        return false;
    }
}
