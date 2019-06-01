package com.spring.security.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:24
 * @Desc
 */
@ApiModel(value = "UserDTO")
@Data
public class UserDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("生日")
    private Date birthday;

}
