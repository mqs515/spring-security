package com.spring.security.demo.web;

import com.spring.security.core.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Mqs
 * @Date 2019/4/10 22:02
 * @Desc
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", ex.getId());
        map.put("message", ex.getMessage());
        return map;
    }
}
