package com.caihongjia.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据传输对象
 * 接收前端传来的用户数据
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {
    private Integer id; // 用户ID
    private String userIdentifier; // 用于标识用户的身份: 用户名,手机号,邮箱
    private String username; // 用户名
    private String name; // 姓名
    private String phone; // 手机号
    private String email; // 邮箱
    private String password; // 密码
    private String newPassword; // 新密码
    private Integer status; // 转态，商家为1，用户为2
    private Integer sex; // 男为1,女为2
    private Integer age; // 年龄
    private String image; // 头像图片路径


}
