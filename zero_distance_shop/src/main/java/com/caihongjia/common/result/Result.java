package com.caihongjia.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回数据结果
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private String code; // 返回转态码成功200,失败500
    private String msg; // 错误信息
    private T data; //数据

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.code = "200";
        result.msg = "success";
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = "200";
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.code = "500";
        result.msg = msg;
        return result;
    }


}
