package com.atwj.yuso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.atwj.yuso.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




