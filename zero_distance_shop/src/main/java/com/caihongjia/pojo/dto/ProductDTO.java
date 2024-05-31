package com.caihongjia.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Integer id; // 商品ID
    private String name; // 商品名称
    private String image; // 商品图片路径
    private Integer categoryId; // 分类ID
    private Double price; // 商品单价
    private Integer quantity; // 商品数量
    private Integer status; // 状态: 1为启用, 2为禁用
    private Integer userId; // 用户ID

}
