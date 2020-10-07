package com.learn.Shiro;

import com.learn.Mapper.UserMapper;
import com.learn.Services.User;
import com.learn.Utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    // 我们需要去解析jwt
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserMapper userMapper;
    //告诉realm支持的是我们jwt的token
    // 返回jwttoken
    @Override
    public boolean supports(AuthenticationToken token) {
        // 判断返回的 token 是不是我们的token
        return token instanceof JwtToken;
    }


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 给用户授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 拿到当前用户的对象
        Subject subject = SecurityUtils.getSubject();
        // 这个currentUser就是我们当前用户的权限
        User currentUser = (User) subject.getPrincipal();// 获取的数据是object 我们将数据类型进行一个转换
        // 设置当前用户的权限
        authorizationInfo.addStringPermission(currentUser.getPerms());
        return authorizationInfo;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        // 获取出来的userid
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userMapper.queryById(Integer.parseInt(userId));
        // 不存在的账户
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(user,jwtToken.getCredentials(),getName());
    }
}
