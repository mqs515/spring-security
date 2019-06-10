package com.spring.security.core.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 14:24
 * @description：跳转返回错误内容
 */
@Data
@AllArgsConstructor
@ApiModel(value = "SimpleResponse")
public class SimpleResponse {

    @ApiModelProperty("跳转返回错误内容")
    private Object content;
}
