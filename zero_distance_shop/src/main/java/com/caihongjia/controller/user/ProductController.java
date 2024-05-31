package com.caihongjia.controller.user;

import com.caihongjia.common.exception.product.DeleteProductException;
import com.caihongjia.common.exception.product.GetProductException;
import com.caihongjia.common.exception.product.NotProductException;
import com.caihongjia.common.exception.product.UpdateProductException;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.result.PageResult;
import com.caihongjia.common.result.Result;
import com.caihongjia.pojo.dto.ProductDTO;
import com.caihongjia.pojo.vo.ProductVO;
import com.caihongjia.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 */
@Controller
@ResponseBody
@RequestMapping("/zero/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 新增商品，仅商家使用
     * @param productDTO
     * @return
     */
    @PostMapping("/merchant")
    public Result<ProductVO> addProduct(@RequestBody ProductDTO productDTO) throws Exception {
        log.info("新增商品信息: {}",productDTO);
        productService.addProduct(productDTO);
        return Result.success();
    }

    /**
     * 修改商品信息，仅商家使用
     * @param productDTO
     * @return
     */
    @PutMapping("/merchant")
    public Result<ProductVO> updateProduct(@RequestBody ProductDTO productDTO) throws UpdateProductException, LoginException {
        log.info("修改商品信息: {}",productDTO);
        productService.updateProduct(productDTO);
        return Result.success();
    }

    /**
     * 根据商品ID查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ProductVO> getByProductId(@PathVariable Integer id) throws NotProductException {
        log.info("根据商品ID查询商品信息: {}",id);
        ProductVO productVO = productService.getByProductId(id);
        return Result.success(productVO);
    }


    /**
     * 根据商品ID删除商品
     * @param ids
     * @return
     */
    @DeleteMapping("/merchant")
    public Result<ProductVO> deleteProduct(Integer[] ids) throws DeleteProductException {
        log.info("根据ID删除商品: {}",ids);
        productService.deleteProduct(ids);
        return Result.success();
    }

    /**
     * 根据用户ID查询商品信息, 仅商家操作
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/merchant")
    public Result<PageResult> getByUserId(Integer userId, Integer page, Integer pageSize) throws GetProductException {
        log.info("根据用户ID查询商品: {}",userId);
        PageResult data = productService.getByUserId(userId,page,pageSize);
        return Result.success(data);
    }

    /**
     * 根据分类ID查询商品信息或所有信息
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<PageResult> getByCategoryId(Integer categoryId, Integer page, Integer pageSize) throws GetProductException {
        log.info("根据分类ID查询商品信息: {}",categoryId);
        PageResult data = productService.getByCategoryId(categoryId,page,pageSize);
        return Result.success(data);
    }

    /**
     * 根据商品名称查询商品
     * @param productName
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public Result<PageResult> getByProductName(String productName,Integer page, Integer pageSize) throws GetProductException {
        log.info("根据商品名称查询商品信息: {}",productName);
        PageResult data = productService.getByProductName(productName,page,pageSize);
        return Result.success(data);
    }

    /**
     * 根据分类ID和价格排序查询商品
     * @param categoryId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/sort-by-price")
    public Result<PageResult> sortByPrice(Integer categoryId, Integer sort, Integer page, Integer pageSize) throws GetProductException {
        log.info("根据分类ID和价格排序查询商品: {},{}",categoryId,sort);
        PageResult data = productService.sortByPrice(categoryId,sort,page,pageSize);
        return Result.success(data);
    }

}
