package com.caihongjia.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Integer id; // 分类ID
    private String name; // 分类名称
    private Integer status; // 状态1为启用, 2为禁用
}
