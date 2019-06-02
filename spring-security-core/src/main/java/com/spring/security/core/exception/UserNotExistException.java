package com.spring.security.core.exception;

import lombok.Data;

/**
 * @Author Mqs
 * @Date 2019/4/10 21:55
 * @Desc
 */
@Data
public class UserNotExistException extends RuntimeException {

    private Integer id;

    public UserNotExistException(Integer id) {
        super("========================user not exist");
        this.id = id;
    }
}
