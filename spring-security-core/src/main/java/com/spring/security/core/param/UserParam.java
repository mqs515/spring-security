package com.spring.security.core.param;

import com.spring.security.core.validator.MyConst;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:38
 * @Desc
 */
@Data
public class UserParam {

    private Long id;

    @MyConst(message = "这个是我自定义的注解")
    private String username;

    /**
     * 使用hibernate的valid校验
     */
    @NotBlank
    private String password;

    private Integer age;

    private Date birthday;

}
