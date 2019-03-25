package com.spring.security.demo.web;

import com.spring.security.core.dto.UserDTO;
import com.spring.security.core.param.UserParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mqs
 * @Date 2019/3/25 23:20
 * @Desc
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
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
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Boolean getUserList( UserParam param){
        // 通过反射获取参数
        System.out.println(ReflectionToStringBuilder.toString(param, ToStringStyle.MULTI_LINE_STYLE));
        return true;
    }
}
