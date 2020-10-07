package com.learn.test;

import com.learn.Mapper.UserMapper;
import com.learn.Services.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testSQL {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/test")
    public String queryByusername(){
        User users = userMapper.queryByName("test");
        return users.getPassword();
    }
}
