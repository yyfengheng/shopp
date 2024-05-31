package com.caihongjia.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接收前端数据: 地址表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    private Integer id; // 主键,地址ID
    private Integer user_id; // 用户ID
    private String name; // 收件人姓名
    private String phone; // 手机号
    private String province; // 省份
    private String city; // 城市
    private String detailed_address; // 详细地址

}
