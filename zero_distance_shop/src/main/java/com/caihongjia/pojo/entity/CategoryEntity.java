package com.caihongjia.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity implements Serializable {
    private Integer id; // 分类ID
    private String name; // 分类名称
    private Integer status; // 状态1为启用, 2为禁用
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
