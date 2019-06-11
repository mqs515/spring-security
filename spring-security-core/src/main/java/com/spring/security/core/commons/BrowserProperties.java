package com.spring.security.core.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 14:33
 * @description：读取配置文件
 */
@Data
@ApiModel(value = "BrowserProperties")
public class BrowserProperties {

    @ApiModelProperty("跳转登陆页面")
    private String loginPage;

    @ApiModelProperty("跳转登陆类型")
    private String loginType;

    @ApiModelProperty("记住我记住的秒数")
    private int rememberMeSeconds;
}
