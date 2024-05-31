package com.caihongjia.mapper;

import com.caihongjia.pojo.entity.CategoryEntity;
import com.caihongjia.pojo.entity.ProductEntity;
import com.caihongjia.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增商品分类
     * @param categoryEntity
     */
    @Insert("insert into category(name, status, create_time, update_time) " +
            "VALUES (#{name},#{status},#{createTime},#{updateTime})")
    void insertCategory(CategoryEntity categoryEntity);

    /**
     * 修改商品分类
     * @param categoryEntity
     */
    void updateCategory(CategoryEntity categoryEntity);

    /**
     * 根据id删除商品分类信息
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    /**
     * 查询所有类别信息
     * @return
     */
    @Select("select * from category")
    List<CategoryVO> getAllCategory();

    /**
     * 根据ID和状态已启用获取分类信息
     * @param categoryId
     */
    @Select("Select count(*) from category where id=#{categoryId} and status=1")
    Integer getByIdAndStatus(Integer categoryId);



}
