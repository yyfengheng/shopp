package com.caihongjia.service.impl;

import com.caihongjia.common.exception.CategoryException;
import com.caihongjia.mapper.CategoryMapper;
import com.caihongjia.pojo.dto.CategoryDTO;
import com.caihongjia.pojo.entity.CategoryEntity;
import com.caihongjia.pojo.vo.CategoryVO;
import com.caihongjia.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增分类商品
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryDTO categoryDTO) throws CategoryException {
        // 创建一个与数据库字段一致的类,用于与数据库做交互
        CategoryEntity categoryEntity = new CategoryEntity();
        // spring中自带的对象工具类
        // 该方法是用于对象属性拷贝,将参数一中与参数二相同的属性名，拷贝到参数二中的属性里
        BeanUtils.copyProperties(categoryDTO,categoryEntity);
        // 设置创建时间和修改时间
        categoryEntity.setCreateTime(LocalDateTime.now());
        categoryEntity.setUpdateTime(LocalDateTime.now());

        try {
            // 数据库新增信息
            categoryMapper.insertCategory(categoryEntity);
        }catch(Exception e){
            // 出现异常抛出自定义异常
            throw new CategoryException();
        }
    }

    /**
     * 修改商品分类
     * @param categoryDTO
     * @throws CategoryException
     */
    @Override
    public void updateCategory(CategoryDTO categoryDTO) throws CategoryException {
        // 创建一个与数据库字段一致的类,用于与数据库做交互
        CategoryEntity categoryEntity = new CategoryEntity();
        // spring中自带的对象工具类
        // 该方法是用于对象属性拷贝,将参数一中与参数二相同的属性名，拷贝到参数二中的属性里
        BeanUtils.copyProperties(categoryDTO, categoryEntity);
        // 设置修改时间
        categoryEntity.setUpdateTime(LocalDateTime.now());

        try{
            // 数据库更新信息
            categoryMapper.updateCategory(categoryEntity);
        }catch (Exception e){
            // 出现异常抛出自定义异常
            throw new CategoryException();
        }
    }

    /**
     * 根据id删除商品分类信息
     * @param id
     */
    @Override
    public void deleteCategory(Integer id) throws CategoryException {
        try{// 数据库删除信息
            categoryMapper.delete(id);
        }catch (Exception e){
            throw new CategoryException();
        }
    }

    /**
     * 查询所有类别信息
     * @return
     */
    @Override
    public List<CategoryVO> queryAllCategory() throws CategoryException {
        List<CategoryVO> data = null;
        try{
            data = categoryMapper.getAllCategory();
        }catch(Exception e){
            throw new CategoryException();
        }
        return data;
    }
}
