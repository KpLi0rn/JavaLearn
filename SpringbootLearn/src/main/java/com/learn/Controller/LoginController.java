package com.learn.Controller;

import cn.hutool.core.lang.Assert;
import com.learn.Common.LoginDto;
import com.learn.Common.Result;
import com.learn.Mapper.UserMapper;
import com.learn.Services.User;
import com.learn.Utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/doLogin")
    public Result LoginCheck(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 输出 通过名字查询出来的结果
        User user = userMapper.queryByName(loginDto.getUsername());
        // 防止黑客进行爆破
        Assert.notNull(user, "用户名或密码错误");
        if(!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("用户名或密码错误！");
        }
        // 如果密码正确 创建一个 jwt token
        String jwt = jwtUtil.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 返回userid
        return Result.succ(user.getId());
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result Index(){
        return Result.succ("Hello,World");
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result Logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

}
