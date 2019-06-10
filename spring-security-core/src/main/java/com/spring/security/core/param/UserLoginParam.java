package com.spring.security.core.param;

import com.spring.security.core.validator.MyConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 10:56
 * @description：用户登录的请求参数
 */
@Data
@ApiModel(value = "UserParam")
public class UserLoginParam {

    @ApiModelProperty("用户名")
    @MyConst(message = "这个是我自定义的注解")
    private String username;

    /**
     * 使用hibernate的valid校验
     */
    @NotBlank
    @ApiModelProperty("密码")
    private String password;
}
