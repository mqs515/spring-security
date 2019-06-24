package com.spring.security.core.validator.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 20:52
 * @description：验证码
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ValidateCode")
public class ValidateCode {

    @ApiModelProperty("随机数")
    private String code;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

    /**
     * 多少秒以后过期
     * @param code
     * @param expireIn
     */
    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime =LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
