package com.spring.security.core.service;

import com.spring.security.core.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Mqs
 * @Date 2019/3/31 18:28
 * @Desc
 */
@Service
public class UserService {

    public List<UserDTO> getUserList(){
        ArrayList<UserDTO> list = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setBirthday(new Date());
        userDTO.setPassword("123456");
        userDTO.setUsername("ljt");
        userDTO.setId(1L);
        list.add(userDTO);
        return list;
    }
}
