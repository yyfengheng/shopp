package com.caihongjia.service;

import com.caihongjia.common.exception.CategoryException;
import com.caihongjia.pojo.dto.CategoryDTO;
import com.caihongjia.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    /**
     * 新增商品分类
     * @param categoryDTO
     */
    void addCategory(CategoryDTO categoryDTO) throws CategoryException;

    /**
     * 修改商品分类
     * @param categoryDTO
     */
    void updateCategory(CategoryDTO categoryDTO) throws CategoryException;

    /**
     *根据id删除商品分类信息
     * @param id
     */
    void deleteCategory(Integer id) throws CategoryException;

    /**
     * 查询所有类别信息
     * @return
     */
    List<CategoryVO> queryAllCategory() throws CategoryException;
}
