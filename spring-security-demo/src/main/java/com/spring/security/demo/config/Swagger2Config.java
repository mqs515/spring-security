package com.spring.security.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Mqs
 * @date 2018/10/5 10:30
 * @desc 接口文档的生成工具配置类
 */
@Configuration
@EnableSwagger2
@Slf4j
public class Swagger2Config {

    @Value("${api.host}")
    private String host;
    @Bean
    public Docket petApi() {

        log.info("=====================接口文档的生成工具配置类:{}", "加载成功【petApi】");
        return new Docket(DocumentationType.SWAGGER_2)
                // api信息
                .apiInfo(apiInfo())
                .host(host)
                // 构建api选择器
                .select()
                // api选择器选择指定的包生成文档
                .apis(RequestHandlerSelectors.basePackage("com.spring.security.demo.web"))
                //api选择器选择包路径下任何api显示在文档中
                .paths(PathSelectors.any())
                // 构建文档
                .build()
                .useDefaultResponseMessages(true);


    }

    private ApiInfo apiInfo() {
        log.info("=====================接口文档的生成工具配置类:{}", "加载成功【apiInfo】");

        return new ApiInfoBuilder()
                .title("SPRING-SECURITY权限认证API接口")
                .description("提供用户等API.</br>通用说明：</br>1.后台返回的所有的列表都是分页形式，分页" +
                        "参数pageNum,pageSize非必传，不传pageNum默认是1，pageSize默认是20，" +
                        "当下拉列表时，pageNum=1,pageSize=100，当全部数据超出100条时，应该考虑分页处理.</br>2.put/patch 的资源主键id" +
                        "应该放到路径中（{id}）,当model 中也有主键时，以路径中为准。后端处理会用路径中{id}覆盖model 中id")
                .termsOfServiceUrl("")
                .contact(new Contact("Mqs", "www.baidu.com", "anshiboy@163.com"))
                .version("1.0.0")
                .build();
    }

}
