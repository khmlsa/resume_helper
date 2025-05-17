package com.tr.resume.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 * @param <T> 泛型数据类型
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // 状态码（0=成功，非0=失败）
    private int code;
    // 提示信息
    private String message;
    // 响应数据
    private T data;
    // 时间戳
    private long timestamp = System.currentTimeMillis();

    // 成功静态方法
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    // 失败静态方法
    public static <T> Result<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> Result<T> error(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // Getter/Setter省略...
}
