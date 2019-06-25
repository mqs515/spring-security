package com.spring.security.demo;

import javafx.animation.Animation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserList")
                .param("username", "mqs")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

    }

    @Test
    public void testAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/addUser")
                .param("username", "mqs")
                .param("password", "123456")
                .param("age", "18")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetInfoSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfo/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"));
    }

    @Test
    public void whenGetInfoFailure() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfo/q")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateUserSuccess() throws Exception{
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"张丹\", \"password\":null, \"birthday\":" + date.getTime() + "}";
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/user/createUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("============" + contentAsString);
    }

    @Test
    public void whenUpdateUserSuccess() throws Exception{
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\", \"username\":\"张丹\", \"password\":null, \"birthday\":" + date.getTime() + "}";
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/user/updateUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("==============" + contentAsString);
    }

    @Test
    public void whenDeleteSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteUser/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 用来测试Redis的
     */
    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("test", "test value");

        String test = stringRedisTemplate.opsForValue().get("test");
        System.out.println(test);
    }
}
