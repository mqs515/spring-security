package com.spring.security.core.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:38
 * @Desc
 */
@Data
public class UserParam {

    private Long id;

    private String username;

    @NotBlank
    private String password;

    private Integer age;

    private Date birthday;

}
