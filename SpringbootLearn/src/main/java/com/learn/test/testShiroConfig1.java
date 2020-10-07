package com.learn.test;

import com.learn.Shiro.JwtFilter;
import com.learn.Shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class testShiroConfig1 {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("getdefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 添加我们自己的jwt过滤器
        // 添加自定义的过滤器规则  在shiro的过滤器中添加我们自己的jwt过滤器
        // 对jwt进行一个过滤
        Map<String, Filter> jwtFilters = new HashMap<>();
        jwtFilters.put("jwt",jwtFilter);
        shiroFilter.setFilters(jwtFilters);

        // 这里是shiro自己的一个过滤器
        Map<String,String> filtermap = new LinkedHashMap<>();
        filtermap.put("/index","authc");
        shiroFilter.setFilterChainDefinitionMap(filtermap);
        shiroFilter.setSecurityManager(defaultWebSecurityManager);
        return shiroFilter;
    }
    // securitymanager 调用默认的安全管理器
    @Bean
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 将我们的userrealm 加入进去
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 通过和用户交互进行参数的获取
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
