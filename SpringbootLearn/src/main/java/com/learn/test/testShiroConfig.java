package com.learn.test;

import com.learn.Shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class testShiroConfig {
    // shiro的工厂 在这里可以配置内置的过滤器
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("getdefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 利用linkedmap 来进行添加
        Map<String,String> filtermap = new LinkedHashMap<>();
//        filtermap.put("/index","anon");
        // 登录进行校验有没有 jwt
        filtermap.put("/index","authc");
        // 添加过滤器规则
        bean.setFilterChainDefinitionMap(filtermap);
        // 添加安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
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
