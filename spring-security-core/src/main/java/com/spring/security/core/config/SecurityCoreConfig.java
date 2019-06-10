package com.spring.security.core.config;

import com.spring.security.core.commons.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 14:38
 * @description：core配置
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
