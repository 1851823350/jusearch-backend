package com.atwj.yuso.controller;

import cn.hutool.core.io.FileUtil;
import com.atwj.yuso.common.BaseResponse;
import com.atwj.yuso.common.ErrorCode;
import com.atwj.yuso.common.ResultUtils;
import com.atwj.yuso.constant.FileConstant;
import com.atwj.yuso.exception.BusinessException;
import com.atwj.yuso.exception.ThrowUtils;
import com.atwj.yuso.manager.CosManager;
import com.atwj.yuso.model.dto.file.UploadFileRequest;
import com.atwj.yuso.model.dto.picture.PictureQueryRequest;
import com.atwj.yuso.model.dto.post.PostQueryRequest;
import com.atwj.yuso.model.entity.Picture;
import com.atwj.yuso.model.entity.Post;
import com.atwj.yuso.model.entity.User;
import com.atwj.yuso.model.enums.FileUploadBizEnum;
import com.atwj.yuso.model.vo.PostVO;
import com.atwj.yuso.service.PictureService;
import com.atwj.yuso.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;

/**
 * 文件接口
 *
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    /**
     * 分页查询图片（
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> searchPostVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                          HttpServletRequest request) {

        long current = pictureQueryRequest.getCurrent(); //当前页数
        long pageSize = pictureQueryRequest.getPageSize(); //页面大小
        String searchText = pictureQueryRequest.getSearchText(); //查询字段

        //限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ErrorCode.PARAMS_ERROR);
        Page<Picture> picturePage = pictureService.searchPictureText(searchText, current, pageSize);
        return ResultUtils.success(picturePage);
    }
}
