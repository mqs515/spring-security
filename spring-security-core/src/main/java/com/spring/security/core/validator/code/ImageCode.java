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
@ApiModel(value = "ImageCode")
public class ImageCode {

    @ApiModelProperty("图片")
    private BufferedImage image;

    @ApiModelProperty("随机数")
    private String code;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

    /**
     * 多少秒以后过期
     * @param image
     * @param code
     * @param expireIn
     */
    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime =LocalDateTime.now().plusSeconds(expireIn);
    }

    public Boolean isExpired(){
        System.out.println("=============过期时间的毫秒数=============" + expireTime);
        return LocalDateTime.now().isAfter(expireTime);
    }
}
