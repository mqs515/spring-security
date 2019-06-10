package com.spring.security.core.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 14:31
 * @description：读取配置文件
 */
@ConfigurationProperties(prefix = "spring.security")
@Data
public class SecurityProperties {

    private BrowserProperties browsers = new BrowserProperties();

    private ValidateCodeProperties code= new ValidateCodeProperties();

//    private SocialProperties social = new SocialProperties();
}
