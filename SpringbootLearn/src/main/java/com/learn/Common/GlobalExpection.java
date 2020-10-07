package com.learn.Common;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 对异常进行一个全局捕获
@RestControllerAdvice
public class GlobalExpection {

    // 对shiro 异常对一个捕获
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException .class)
    public Result handler(ShiroException e){
        return Result.status(401,e.getMessage(),null);
    }

    // 对其余对异常进行一个捕获
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(Exception e){
        return Result.fail(e.getMessage());
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        return Result.fail(e.getMessage());
    }
}
