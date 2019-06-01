package com.spring.security.core.param;

import com.spring.security.core.validator.MyConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:38
 * @Desc
 */
@Data
@ApiModel(value = "UserParam")
public class UserParam {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    @MyConst(message = "这个是我自定义的注解")
    private String username;

    /**
     * 使用hibernate的valid校验
     */
    @NotBlank
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("生日")
    private Date birthday;

}
