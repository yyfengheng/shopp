package com.caihongjia.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO implements Serializable {
    private Integer id; // 分类ID
    private String name; // 分类名称
    private Integer status; // 状态1为启用, 2为禁用

}
