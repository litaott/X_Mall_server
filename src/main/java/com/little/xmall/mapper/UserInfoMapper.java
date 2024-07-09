package com.little.xmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.xmall.entity.UserInfo;

/**
 * 用户信息Mapper
 * @author Little
 */

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo findById(Integer userId);

    void update(UserInfo userInfo);

    void delete(int userId);
}