package com.caihongjia.service.impl;

import com.caihongjia.common.exception.CategoryException;
import com.caihongjia.common.exception.product.*;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.result.PageResult;
import com.caihongjia.common.util.CalculatorUtil;
import com.caihongjia.common.util.ThreadLocalUtil;
import com.caihongjia.mapper.CategoryMapper;
import com.caihongjia.mapper.ProductMapper;
import com.caihongjia.mapper.UserMapper;
import com.caihongjia.pojo.dto.ProductDTO;
import com.caihongjia.pojo.entity.ProductEntity;
import com.caihongjia.pojo.entity.UserEntity;
import com.caihongjia.pojo.vo.ProductVO;
import com.caihongjia.pojo.vo.UserVO;
import com.caihongjia.service.ProductService;
import org.apache.catalina.startup.AddPortOffsetRule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 新增商品，仅商家使用
     * @param productDTO
     */
    @Override
    public void addProduct(ProductDTO productDTO) throws Exception {

        // 从线程中获取用户id, 后面会使用
        LinkedHashMap o = (LinkedHashMap)ThreadLocalUtil.get();
        Integer userId = Integer.parseInt(o.get("id").toString());
        System.out.println(userId);

        // 查询当前新增分类是否存在,或启用
        Integer categoryId = productDTO.getCategoryId();
        Integer count = categoryMapper.getByIdAndStatus(categoryId);
        if(count<0){
            // 如果不存在就抛出异常
            throw new CategoryException();
        }

        // 创建一个与数据库字段一致的实体类, 用于与数据库交互
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(productDTO,productEntity);
        productEntity.setUserId(userId);

        // 查询给用户是否为商家, 如果不为商家抛出异常, 商家为1, 普通用户为2
        UserEntity user = userMapper.getById(userId);
        Integer status = user.getStatus();
        if(status == 2){
            throw new LoginException("非商家无法新增");
        }

        // 查询该用户的商品是否存在, 如果存在则不添加返回错误信息
        Integer count1 = 0;
        try{
            count1 = productMapper.getByNameAndUserId(productEntity);
        }catch(Exception e){
            throw new AddProductException();
        }
        if(count1>0){
            throw new AddProductException();
        }


        // 新增商品信息, 设置创建时间和更新时间
        productEntity.setCreateTime(LocalDateTime.now());
        productEntity.setUpdateTime(LocalDateTime.now());
        try{
            productMapper.insertProduct(productEntity);
        }catch(Exception e){
            throw new AddProductException();
        }
    }

    /**
     * 修改商品，仅商家使用
     * @param productDTO
     */
    @Override
    public void updateProduct(ProductDTO productDTO) throws UpdateProductException, LoginException {
        // 创建一个与数据库字段一致的实体类, 用于与数据库交互
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDTO,productEntity);
        productEntity.setUpdateTime(LocalDateTime.now());

        // 查询给用户是否为商家, 如果不为商家抛出异常, 商家为1, 普通用户为2
        UserEntity user = userMapper.getById(productEntity.getUserId());
        Integer status = user.getStatus();
        if(status == 2){
            throw new LoginException("非商家无法新增");
        }

        try{
            // 根据id更新商品信息
            productMapper.updateProduct(productEntity);
        }catch(Exception e){
            throw new UpdateProductException();
        }
    }

    /**
     * 根据商品ID删除商品
     * @param ids
     */
    @Override
    public void deleteProduct(Integer[] ids) throws DeleteProductException {
        try{
            productMapper.deleteProduct(ids);
        }catch(Exception e){
            throw new DeleteProductException();
        }
    }

    /**
     * 根据用户ID查询商品信息, 仅商家操作
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getByUserId(Integer userId, Integer page, Integer pageSize) throws GetProductException {
        // 查询商品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setUserId(userId);
        // 计算出起始索引
        Integer index = CalculatorUtil.calculatorStartIndex(page, pageSize);
        productEntity.setIndex(index);
        productEntity.setPageSize(pageSize);
        // 总数
        Integer total = null;
        // data数据
        List<ProductVO> productVO = null;

        // 获取用户ID
        LinkedHashMap o = (LinkedHashMap)ThreadLocalUtil.get();
        System.out.println("从token令牌中解析出来的参数" + o.get("id"));

        // 将用户id设置在实体类中
        productEntity.setUserId(Integer.parseInt(o.get("id").toString()));

        try{
            // 获取总数和该用户商品信息
            total = productMapper.getProductCount(productEntity);
            productVO = productMapper.getProduct(productEntity);
        }catch(Exception e){
            throw new GetProductException();
        }

        // 分页统一返回数据
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(productVO);
        return pageResult;
    }

    /**
     * 根据分类ID查询商品信息
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getByCategoryId(Integer categoryId, Integer page, Integer pageSize) throws GetProductException {
        // 查询商品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryId(categoryId);
        // 计算起始索引
        Integer index = CalculatorUtil.calculatorStartIndex(page, pageSize);
        productEntity.setIndex(index);
        productEntity.setPageSize(pageSize);
        // 初始化总数变量
        Integer total = null;
        // 初始化data数据变量
        List<ProductVO> product = null;

        try {
            // 获取总数和该分类商品信息
            total = productMapper.getProductCount(productEntity);
            product = productMapper.getProduct(productEntity);
        }catch(Exception e){
            throw new GetProductException();
        }
        // 分页统一返回数据
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(product);
        return pageResult;
    }

    /**
     * 根据商品名称查询商品信息
     * @param productName
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getByProductName(String productName, Integer page, Integer pageSize) throws GetProductException {
        // 查询商品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        // 计算起始索引
        Integer index = CalculatorUtil.calculatorStartIndex(page, pageSize);
        productEntity.setIndex(index);
        productEntity.setPageSize(pageSize);

        //总数
        Integer total = null;
        // data数据
        List<ProductVO> product = null;

        try {
            total = productMapper.getProductCount(productEntity);
            product = productMapper.getProduct(productEntity);
        }catch(Exception e){
            throw new GetProductException();
        }
        // 分页统一返回数据
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(product);
        return pageResult;
    }

    /**
     * 根据商品ID查询商品信息
     * @param id
     * @return
     * @throws NotProductException
     */
    @Override
    public ProductVO getByProductId(Integer id) throws NotProductException {
        ProductVO productVO = null;
        try{
            productVO = productMapper.getByProductId(id);
        }catch(Exception e){
            throw new NotProductException("商品不存在");
        }

        return productVO;
    }

    /**
     * 根据分类ID和价格排序查询商品
     * @param categoryId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult sortByPrice(Integer categoryId, Integer sort, Integer page, Integer pageSize) throws GetProductException {
        // 查询商品信息

        System.out.println(sort);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategoryId(categoryId);
        productEntity.setSort(sort);
        // 计算起始索引
        Integer index = CalculatorUtil.calculatorStartIndex(page, pageSize);
        productEntity.setIndex(index);
        productEntity.setPageSize(pageSize);
        // 初始化总数变量
        Integer total = null;
        // 初始data数据变量
        List<ProductVO> product = null;

        try {
            // 获取总数和商品信息
            total = productMapper.getProductCount(productEntity);
            product = productMapper.sortByPrice(productEntity);
        }catch(Exception e){
            throw new GetProductException();
        }
        // 分页统一返回数据
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(product);
        return pageResult;
    }


}
