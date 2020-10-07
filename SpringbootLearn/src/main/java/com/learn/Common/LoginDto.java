package com.learn.Common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

// 对请求发来对数据进行一个判断
@Data
public class LoginDto implements Serializable  {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
