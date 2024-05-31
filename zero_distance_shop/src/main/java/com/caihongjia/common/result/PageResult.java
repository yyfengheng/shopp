package com.caihongjia.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {

    private Integer total; // 总数量
    private Object records; // 信息数据
}
