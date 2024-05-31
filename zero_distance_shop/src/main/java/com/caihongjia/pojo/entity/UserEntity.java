package com.caihongjia.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实体
 * 与数据库中的表字段对应
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements Serializable {
    private Integer id; // 用户ID
    private String username; // 用户名
    private String name; // 姓名
    private String phone; // 手机号
    private String email; // 邮箱
    private String password; // 密码
    private Integer status; // 转态，商家为1，用户为2
    private Integer sex; // 男为1,女为2
    private Integer age; // 年龄
    private String image; // 头像图片路径
}
