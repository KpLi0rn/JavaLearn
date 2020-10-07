package com.learn.Mapper;

import com.learn.Services.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

// 接口是用来定义方法的
// 将该接口注入到我们到springboot中
@Mapper
@Repository
public interface UserMapper{
    // 通过username进行查找

    User queryByName(String username);

    User queryById(int id);

    void InsertUser(String username,String password);
}
