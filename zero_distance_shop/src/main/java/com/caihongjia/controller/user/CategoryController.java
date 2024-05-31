package com.caihongjia.controller.user;

import com.caihongjia.common.exception.CategoryException;
import com.caihongjia.common.result.Result;
import com.caihongjia.pojo.dto.CategoryDTO;
import com.caihongjia.pojo.vo.CategoryVO;
import com.caihongjia.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/zero/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增商品分类，仅商家可用
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result<CategoryVO> addCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryException {
        log.info("新增商品信息: {}",categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 修改商品分类，仅商家可用
     * @param categoryDTO
     * @return
     * @throws CategoryException
     */
    @PutMapping
    public Result<CategoryVO> updateCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryException {
        log.info("修改商品分类信息: {}",categoryDTO);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 根据id删除商品分类信息，仅商家可用
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<CategoryVO> deleteCategory(Integer id) throws CategoryException {
        log.info("根据id删除商品分类信息: {}",id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    /**
     * 查询所有分类信息
     * @param
     * @return
     */
    @GetMapping
    public Result<List<CategoryVO>> queryAllCategory() throws CategoryException {
        log.info("查询所有分类信息");
        List<CategoryVO> data = categoryService.queryAllCategory();
        return Result.success(data);
    }
}
