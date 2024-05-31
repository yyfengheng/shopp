package com.caihongjia.service.impl;

import com.caihongjia.common.exception.product.NotProductException;
import com.caihongjia.common.exception.shoppingcart.AddShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.DeleteShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.UpdateShoppingCartQuantityException;
import com.caihongjia.common.util.ThreadLocalUtil;
import com.caihongjia.mapper.ProductMapper;
import com.caihongjia.mapper.ShoppingCartMapper;
import com.caihongjia.pojo.dto.ShoppingCartDTO;
import com.caihongjia.pojo.entity.ShoppingCartEntity;
import com.caihongjia.pojo.vo.ProductVO;
import com.caihongjia.pojo.vo.ShoppingCartVO;
import com.caihongjia.pojo.vo.UserVO;
import com.caihongjia.service.ShoppingCartService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 新增购物车商品信息
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) throws Exception {
        // 创建一个与数据库字段一致的实体类, 用于与数据库做交互
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        // 对象工具类, 用于类与类相同属性之间相互复制
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCartEntity);

        // 从线程中获取用户id, 是从jwt令牌中解析出来的
        LinkedHashMap o = (LinkedHashMap)ThreadLocalUtil.get();
        Integer userId = Integer.parseInt(o.get("id").toString());
        System.out.println(userId);
        // 将用户id设置到实体类中
        shoppingCartEntity.setUserId(userId);
        System.out.println(shoppingCartEntity.getUserId());

        // 获取商品id和商品购入数量
        Integer productId = shoppingCartEntity.getProductId();
        Integer quantity = shoppingCartEntity.getQuantity();

        // 查看购物车该商品是否存在,存在自增1
        ShoppingCartVO userIdAndProductId = shoppingCartMapper.getUserIdAndProductId(shoppingCartEntity);
        if(userIdAndProductId!=null){
            quantity = userIdAndProductId.getQuantity()+1;

        }

        // 查看商品是否存在,并且数量是否满足
        Integer count = productMapper.getByIdAndquantity(productId,quantity);
        if(count<=0){
            throw new NotProductException("商品数量不足");
        }

        // 设置购入数量到实体类中
        shoppingCartEntity.setQuantity(quantity);
        shoppingCartEntity.setCreateTime(LocalDateTime.now());
        shoppingCartEntity.setUpdateTime(LocalDateTime.now());

        // 查到购物车中有该商品, 就是修改
        if(quantity>1){
            try{
                shoppingCartMapper.updateShoppingCart(shoppingCartEntity);
            }catch(Exception e){
                throw new UpdateShoppingCartQuantityException();
            }
        // 如果不是就是新增
        }else{
            try{
                shoppingCartMapper.insertShoppingCart(shoppingCartEntity);
            }catch(Exception e){
                throw new AddShoppingCartException();
            }
        }


    }

    /**
     * 根据ID更新购物车数量信息
     * @param shoppingCartDTO
     */
    @Override
    public void updateShoppingCart(ShoppingCartDTO shoppingCartDTO) throws AddShoppingCartException, UpdateShoppingCartQuantityException, NotProductException {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCartEntity);

        // 查看商品是否存在,并且数量是否满足
        Integer productId = shoppingCartEntity.getProductId();
        Integer quantity = shoppingCartEntity.getQuantity();
        Integer count = productMapper.getByIdAndquantity(productId,quantity);
        if(count<=0){
            throw new NotProductException("商品数量不足");
        }

        shoppingCartEntity.setUpdateTime(LocalDateTime.now());

        try{
            shoppingCartMapper.updateShoppingCart(shoppingCartEntity);
        }catch(Exception e){
            throw new UpdateShoppingCartQuantityException();
        }
    }

    /**
     * 根据id批量删除购物车商品
     * @param ids
     */
    @Override
    public void deleteShoppingCart(Integer[] ids) throws DeleteShoppingCartException {
        try{
            shoppingCartMapper.deleteShoppingCart(ids);
        }catch(Exception e){
            throw new DeleteShoppingCartException();
        }
    }

    /**
     * 根据用户ID查看购物车商品信息
     * @return
     */
    @Override
    public List<ShoppingCartVO> getByUserId() {
        LinkedHashMap o = (LinkedHashMap)ThreadLocalUtil.get();
        Integer userId = Integer.parseInt(o.get("id").toString());
        List<ShoppingCartVO> data = shoppingCartMapper.getByUserId(userId);
        return data;
    }
}
