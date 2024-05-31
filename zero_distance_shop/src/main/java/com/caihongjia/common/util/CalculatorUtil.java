package com.caihongjia.common.util;

// 用于计算使用的类
public class CalculatorUtil {

    // 计算起始索引
    public static Integer calculatorStartIndex(Integer page,Integer pageSize){
        Integer index = (page-1) * pageSize;
        return index;
    }
}
