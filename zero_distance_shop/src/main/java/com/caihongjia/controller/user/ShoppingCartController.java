package com.caihongjia.controller.user;

import com.caihongjia.common.exception.product.NotProductException;
import com.caihongjia.common.exception.shoppingcart.AddShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.DeleteShoppingCartException;
import com.caihongjia.common.exception.shoppingcart.UpdateShoppingCartQuantityException;
import com.caihongjia.common.result.Result;
import com.caihongjia.pojo.dto.ShoppingCartDTO;
import com.caihongjia.pojo.vo.ShoppingCartVO;
import com.caihongjia.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 购物车管理
@Controller
@ResponseBody
@RequestMapping("/zero/cart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 新增购物车商品
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping
    public Result<ShoppingCartVO> addShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
        log.info("新增购物车商品信息: {}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 根据ID更新购物车数量信息
     * @param shoppingCartDTO
     * @return
     */
    @PutMapping
    public Result<ShoppingCartVO> updateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) throws AddShoppingCartException, UpdateShoppingCartQuantityException, NotProductException {
        log.info("根据ID更新购物车数量信息: {}",shoppingCartDTO.getId());
        shoppingCartService.updateShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 根据id批量删除购物车商品
     * @param ids
     * @return
     * @throws DeleteShoppingCartException
     */
    @DeleteMapping
    public Result<ShoppingCartVO> deleteShoppingCart(Integer[] ids) throws DeleteShoppingCartException {
        log.info("根据ID批量删除购物车商品: {}", ids);
        shoppingCartService.deleteShoppingCart(ids);
        return Result.success();
    }

    /**
     * 根据用户ID查看购物车信息
     * @return
     */
    @GetMapping
    public Result<List<ShoppingCartVO>> getByUserId(){
        log.info("根据用户ID查看购物车信息");
        List<ShoppingCartVO> data = shoppingCartService.getByUserId();
        return Result.success(data);
    }

}
