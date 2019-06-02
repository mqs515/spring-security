package com.spring.security.demo.web;

import com.spring.security.core.dto.UserDTO;
import com.spring.security.core.enums.ResultErrorEnum;
import com.spring.security.core.exception.UserNotExistException;
import com.spring.security.core.param.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

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
@Api(tags = "用户测试")
public class UserController {

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/getUserList")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
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
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public Boolean addUser(@RequestBody UserParam param){
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
    @ApiOperation(value = "查看用户详情", notes = "查看用户详情")
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
    @ApiOperation(value = "创建用户", notes = "创建用户")
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
    @ApiOperation(value = "修改用户", notes = "修改用户")
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

    @DeleteMapping("/deleteUser/{id:\\d+}")
    public UserDTO deleteUser(@PathVariable String id){
        log.info("=====================id: " + id);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("tom");
        return userDTO;
    }

    @GetMapping("/getInfo/{id:\\d+}")
    @ApiOperation(value = "测试自定义异常[该异常会被捕获]", notes = "测试自定义异常[该异常会被捕获]")
    public UserDTO getInfo(@PathVariable Integer id){
        throw new UserNotExistException(id);
    }

    @GetMapping("/getRunInfo/{id:\\d+}")
    @ApiOperation(value = "测试运行时异常", notes = "测试运行时异常")
    public UserDTO getRunInfo(){
        throw new RuntimeException("user is not exist ");
    }

    @GetMapping("/testAspect/{id:\\d+}")
    @ApiOperation(value = "测试运行时异常", notes = "测试运行时异常")
    public UserDTO testAspect(@PathVariable Long id){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("tom");
        userDTO.setId(id);
        return userDTO;
    }
}
