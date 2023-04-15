package com.atwj.yuso.service;

import com.atwj.yuso.model.entity.Picture;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 帖子服务
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface PictureService {

    Page<Picture> searchPictureText(String searchText, long pageNum, long pageSize);



}
