package com.learn.test;

import com.learn.Mapper.UserMapper;
import com.learn.Services.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Springboot 的单元测试
@SpringBootTest
class TestApplicationTests {

    // 将我们需要调用到方法方剂哪里
    @Autowired
    UserMapper userMapper;

    @Test
    public void testQueryByName(){
        User test = userMapper.queryByName("asdfasdfasdf");
        try{
            test.getUsername();
        }
        catch(NullPointerException e){
            System.out.println(1);
        }
    }

}
