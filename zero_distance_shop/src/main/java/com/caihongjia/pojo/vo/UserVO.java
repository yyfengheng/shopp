package com.caihongjia.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 视图对象
 * 传递给前端的对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO implements Serializable {
    private Integer id; // 用户ID
    private String username; // 用户名
    private String name; // 姓名
    private String phone; // 手机号
    private String email; // 邮箱
    private Integer status; // 转态，商家为1，用户为2
    private Integer sex; // 男为1,女为2
    private Integer age; // 年龄
    private String image; // 头像图片路径
    private String token; // jwt令牌
}
