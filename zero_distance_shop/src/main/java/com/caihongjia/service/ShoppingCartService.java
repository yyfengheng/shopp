package com.caihongjia.service;

import com.caihongjia.common.exception.product.NotProductException;
import com.caihongjia.common.exception.shoppingcart.AddShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.DeleteShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.UpdateShoppingCartQuantityException;
import com.caihongjia.pojo.dto.ShoppingCartDTO;
import com.caihongjia.pojo.vo.ShoppingCartVO;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 新增购物车商品
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO) throws Exception;

    /**
     * 根据ID更新购物车数量信息
     * @param shoppingCartDTO
     */
    void updateShoppingCart(ShoppingCartDTO shoppingCartDTO) throws AddShoppingCartException, UpdateShoppingCartQuantityException, NotProductException;

    /**
     * 根据id批量删除购物车商品
     * @param ids
     */
    void deleteShoppingCart(Integer[] ids) throws DeleteShoppingCartException;

    /**
     * 根据用户ID查看购物车商品信息
     * @return
     */
    List<ShoppingCartVO> getByUserId();

}
