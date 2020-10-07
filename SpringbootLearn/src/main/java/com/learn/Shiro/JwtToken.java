package com.learn.Shiro;

import org.apache.shiro.authc.AuthenticationToken;

// 对我们对token 进行一个重写
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }

    // 对这个类进行一个重写 AuthenticationToken
    @Override
    public Object getPrincipal() {

        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
