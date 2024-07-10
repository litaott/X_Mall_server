package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.UserInfo;

import java.util.Map;
import java.util.List;

/**
 * 用户信息Service接口
 * @author Little
 */

public interface UserInfoService extends IService<UserInfo>{
    /**
     * 创建新的用户信息
     * @param userInfo 用户信息
     * @return Response
     */
    Response<Map<String, Object>> registerUser(UserInfo userInfo);

    Response<List<Map<String, Object>>> getUser(int user_id);

    Response<Map<String, Object>> updateUser(UserInfo userInfo);

    Response<Map<String, Object>> login(Integer user_id, String password);
}
