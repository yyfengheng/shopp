package com.caihongjia.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity implements Serializable {
    private Integer id; // 购物车ID
    private Integer userId; // 用户ID
    private Integer productId; // 商品ID
    private String productName; // 商品名称
    private String productImage; // 商品图片路径
    private Integer quantity; // 商品购入数量
    private Double price; // 商品单价
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
