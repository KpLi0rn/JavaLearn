package com.learn.Common;

import lombok.Data;

import java.io.Serializable;

// 统一结果封装,接受我们前端传过来的数值
@Data
public class Result implements Serializable {

    // 获取返回信息 表示是否成功获取
    private int code;
    private String message;
    private Object data;

    // 可以通过调用这个来直接返回我们的数据
    public static Result status (int code,String message,Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMessage(message);
        res.setData(data);
        return res;
    }

    public static Result succ (Object data){
        return status(200,"操作成功",data);
    }

    public static Result fail (Object data){
        return status(400,"操作失败",data);
    }
}
