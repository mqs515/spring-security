package com.spring.security.core.validator.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 20:52
 * @description：验证码
 */
@Data
@ApiModel(value = "ImageCode")
public class ImageCode extends ValidateCode {

    @ApiModelProperty("图片")
    private BufferedImage image;

    /**
     * 构造方法
     * @param image
     * @param code
     * @param expireIn
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireIn){
        super(code, expireIn);
        this.image = image;
    }

    /**
     * 多少秒以后过期
     * @param image
     * @param code
     * @param expireIn
     */
    public ImageCode(BufferedImage image,String code,int expireIn){
        super(code, expireIn);
        this.image = image;
    }

}
