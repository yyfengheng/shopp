package com.caihongjia.service;

import com.caihongjia.common.exception.CategoryException;
import com.caihongjia.common.exception.product.DeleteProductException;
import com.caihongjia.common.exception.product.GetProductException;
import com.caihongjia.common.exception.product.NotProductException;
import com.caihongjia.common.exception.product.UpdateProductException;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.result.PageResult;
import com.caihongjia.pojo.dto.ProductDTO;
import com.caihongjia.pojo.vo.ProductVO;

import java.util.List;

public interface ProductService {
    /**
     * 新增商品，仅商家使用
     * @param productDTO
     */
    void addProduct(ProductDTO productDTO) throws CategoryException, Exception;

    /**
     * 修改商品，仅商家使用
     * @param productDTO
     */
    void updateProduct(ProductDTO productDTO) throws UpdateProductException, LoginException;

    /**
     * 根据商品ID删除商品
     * @param ids
     */
    void deleteProduct(Integer[] ids) throws DeleteProductException;

    /**
     * 根据用户ID查询商品信息, 仅商家操作
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageResult getByUserId(Integer userId, Integer page, Integer pageSize) throws GetProductException;

    /**
     * 根据分类ID查询商品信息
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    PageResult getByCategoryId(Integer categoryId, Integer page, Integer pageSize) throws GetProductException;

    /**
     * 根据商品名称查询商品信息
     * @param productName
     * @param page
     * @param pageSize
     * @return
     */
    PageResult getByProductName(String productName, Integer page, Integer pageSize) throws GetProductException;

    /**
     * 根据商品ID查询商品信息
     * @param id
     * @return
     */
    ProductVO getByProductId(Integer id) throws NotProductException;

    /**
     * 根据分类ID和价格排序查询商品
     * @param categoryId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PageResult sortByPrice(Integer categoryId, Integer sort, Integer page, Integer pageSize) throws GetProductException;
}
