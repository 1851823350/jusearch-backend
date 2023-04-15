package com.atwj.yuso.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 数据源接口（新接入的数据源必须实现，这里的数据源是指想要接入搜索平台的数据种类，例如：视频、图片等数据类型）
 * @param <T>
 */
public interface DataSource <T>{


    /**
     * 定义统一的搜索规范（如果数据源支持搜索，但参数和规范不一致，那么需要进行转换）
     * @param searchText
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<T> doSearch(String searchText, long pageSize, long pageNum);
}
