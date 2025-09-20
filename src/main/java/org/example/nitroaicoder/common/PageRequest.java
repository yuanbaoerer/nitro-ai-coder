package org.example.nitroaicoder.common;

import lombok.Data;

/**
 * 请求包装类
 * 对于分页、删除某条数据这类通用的请求，可以封装统一的请求包装类，用于接受前端传来的参数，之后相同的参数的请求就不用专门再新建一个类了
 *
 * 包含当前页号、页面大小、排序字段、排序顺序参数
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}

