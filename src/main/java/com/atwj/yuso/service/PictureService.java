package com.atwj.yuso.service;

import com.atwj.yuso.model.entity.Picture;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 帖子服务
 *
 */
public interface PictureService {

    Page<Picture> searchPictureText(String searchText, long pageNum, long pageSize);



}
