package com.learn.Controller;

import com.learn.Common.LoginDto;
import com.learn.Common.Result;
import com.learn.Mapper.UserMapper;
import com.learn.Services.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RegisterController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/doReg")
    public Result RegisterCheck(@Validated @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        // 首先查询一下 有没有注册
        User user = userMapper.queryByName(loginDto.getUsername());
        try{
            user.getUsername();
            return Result.fail("用户名已存在！");
        } catch (NullPointerException e){
            userMapper.InsertUser(loginDto.getUsername(),loginDto.getPassword());
            return Result.succ("注册成功");
        }
//        if(user.getUsername() == null){
//            System.out.println(1);
//            userMapper.InsertUser(loginDto.getUsername(),loginDto.getPassword());
//            return Result.succ("注册成功");
//        } else {
//            System.out.println(2);
//            return Result.fail("用户名已存在！");
//        }
    }
}
