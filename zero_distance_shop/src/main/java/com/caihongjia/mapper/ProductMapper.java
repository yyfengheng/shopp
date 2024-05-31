package com.caihongjia.mapper;

import com.caihongjia.pojo.dto.ProductDTO;
import com.caihongjia.pojo.entity.ProductEntity;
import com.caihongjia.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 新增商品，仅商家使用
     * @param productEntity
     */
    @Insert("insert into product(name,image,category_id,price,quantity,status,user_id,create_time,update_time)" +
            "values (#{name},#{image},#{categoryId},#{price},#{quantity},#{status},#{userId},#{createTime},#{updateTime})")
    void insertProduct(ProductEntity productEntity);

    /**
     * 修改商品，仅商家使用
     * @param productEntity
     */
    void updateProduct(ProductEntity productEntity);

    /**
     * 根据商品ID删除商品
     * @param ids
     */
    void deleteProduct(Integer[] ids);

    /**
     * 根据用户ID或分类ID或商品名称查询商品信息
     * @param productEntity
     * @return
     */
    List<ProductVO> getProduct(ProductEntity productEntity);

    /**
     * 根据用户ID或分类ID或商品名称查询商品总数
     * @param productEntity
     * @return
     */
    Integer getProductCount(ProductEntity productEntity);

    /**
     * 根据商品名称和用户ID查询总数
     * @param productEntity
     * @return
     */
    @Select("select count(*) from product where name=#{name} and user_id=#{userId}")
    Integer getByNameAndUserId(ProductEntity productEntity);

    /**
     * 根据商品ID和数量查看数据是否存在
     * @param productId
     * @param quantity
     * @return
     */
    @Select("select count(*) from product where id=#{productId} and quantity>=#{quantity}")
    Integer getByIdAndquantity(Integer productId, Integer quantity);

    /**
     * 根据商品ID查询商品信息
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    ProductVO getByProductId(Integer id);

    /**
     * 根据分类ID和价格排序查询商品
     * @param productEntity
     * @return
     */
    List<ProductVO> sortByPrice(ProductEntity productEntity);
}
