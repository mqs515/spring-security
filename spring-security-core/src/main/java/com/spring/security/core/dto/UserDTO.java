package com.spring.security.core.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:24
 * @Desc
 */
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private Date birthday;

}
