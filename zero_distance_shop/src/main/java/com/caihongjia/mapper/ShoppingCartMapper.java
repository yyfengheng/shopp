package com.caihongjia.mapper;

import com.caihongjia.pojo.entity.ShoppingCartEntity;
import com.caihongjia.pojo.vo.ShoppingCartVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 新增商品信息
     * @param shoppingCartEntity
     */
    @Insert("insert into shopping_cart(user_id, product_id, product_name, product_image, quantity, price, create_time, update_time) " +
            "VALUES(#{userId},#{productId},#{productName},#{productImage},#{quantity},#{price},#{createTime},#{updateTime}) ")
    void insertShoppingCart(ShoppingCartEntity shoppingCartEntity);

    /**
     * 根据用户ID和商品ID查询信息
     * @param shoppingCartEntity
     * @return
     */
    @Select("select * from shopping_cart where user_id=#{userId} and product_id=#{productId}")
    ShoppingCartVO getUserIdAndProductId(ShoppingCartEntity shoppingCartEntity);

    /**
     * 修改符合用户ID和商品ID的购物车商品信息 或 修改符合的ID购物车商品
     * @param shoppingCartEntity
     */
    void updateShoppingCart(ShoppingCartEntity shoppingCartEntity);

    /**
     * 根据id批量删除购物车商品
     * @param ids
     */
    void deleteShoppingCart(Integer[] ids);

    /**
     * 根据用户ID查询购物车商品
     * @param userId
     * @return
     */
    List<ShoppingCartVO> getByUserId(Integer userId);
}
