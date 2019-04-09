package com.spring.security.demo.web;

import com.spring.security.core.dto.UserDTO;
import com.spring.security.core.enums.ResultErrorEnum;
import com.spring.security.core.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:20
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/getUserList")
    public List<UserDTO> getUserList(@RequestParam("username") String nickname){
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO());
        list.add(new UserDTO());
        list.add(new UserDTO());
        System.out.println(nickname);
        return list;
    }

    /**
     * 添加用户
     * @return
     */
    @GetMapping("/addUser")
    public Boolean addUser(UserParam param){
        // 通过反射获取参数
        System.out.println(ReflectionToStringBuilder.toString(param, ToStringStyle.MULTI_LINE_STYLE));
        return true;
    }

    /**
     * 查看用户详情
     * @return
     * TODO  :\d+ 代表的是只能添加一个数字
     */
    @GetMapping("/getUserInfo/{id:\\d+}")
    public UserDTO getUserInfo(@PathVariable String id){
        log.info("=====================id: " + id);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("tom");
        return userDTO;
    }

    /**
     * 创建用户
     * @return
     * TODO 使用hibernate的valid校验
     * TODO @Valid这个注解会自动去校验传的参数中的注解
     * TODO 这个是获取校验结果 ，如果校验结果为错误的则会打印错误
     */
    @PostMapping("/createUser")
    public UserDTO createUser(@Valid @RequestBody UserParam param, BindingResult errors){
        // 获取校验结果为错误的，并打印错误结果
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(e -> {
                System.out.println("======================:" + e.getDefaultMessage());
                if (e.getDefaultMessage().equals(ResultErrorEnum.NOT_BLANK_ERROR.getErrorTypeByIndex(1))){
                    log.info("==================参数为空===================校验结果为错误");
                }
            });
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername(param.getUsername());
        userDTO.setPassword(param.getPassword());
        userDTO.setBirthday(param.getBirthday());
        log.info("=========================" + userDTO.toString());
        return userDTO;
    }

    /**
     * 修改用户
     * @return
     */
    @PutMapping("/updateUser")
    public UserDTO updateUser(@Valid @RequestBody UserParam param, BindingResult errors){
        // 获取校验结果为错误的，并打印错误结果
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(e -> {
//                FieldError fieldError = (FieldError)e;
//                System.out.println("==============" + fieldError.getField() + "============:" + e.getDefaultMessage());
                System.out.println("==========================:" + e.getDefaultMessage());
                if (e.getDefaultMessage().equals(ResultErrorEnum.NOT_BLANK_ERROR.getErrorTypeByIndex(1))){
                    log.info("==============参数为空=================校验结果为错误");
                }
            });
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setPassword(param.getPassword());
        userDTO.setUsername(param.getUsername());
        userDTO.setBirthday(param.getBirthday());
        log.info("=========================" + userDTO.toString());
        return userDTO;
    }

}