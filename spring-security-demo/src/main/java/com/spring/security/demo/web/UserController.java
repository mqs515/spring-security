package com.spring.security.demo.web;

import com.spring.security.core.dto.UserDTO;
import com.spring.security.core.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
     * 添加用户
     * @return
     */
    @PostMapping("/createUser")
    public UserDTO createUser(@Valid @RequestBody UserParam param, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername(param.getUsername());
        userDTO.setPassword(param.getPassword());
        userDTO.setBirthday(param.getBirthday());
        log.info("=========================" + userDTO.toString());
        return userDTO;
    }
}
