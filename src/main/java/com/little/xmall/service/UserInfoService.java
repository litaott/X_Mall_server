package com.little.xmall.service;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.UserInfo;
import com.little.xmall.constant.Response;
import java.util.Map;
import java.util.List;

/**
 * 用户信息Service接口
 * @author Little
 */

public interface UserInfoService {
    Response<Map<String, Object>> apply(UserInfo userInfo);
    /**
     * 创建新的用户信息
     * @param userInfo 用户信息
     * @return 创建后的用户信息
     */
    UserInfo createUser(UserInfo userInfo);

    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo getUserById(Integer userId);


    UserInfo updateUser(UserInfo userInfo);
    void deleteUser(int userId);
    List<UserInfo> getUsersByCriteria(Map<String, Object> criteria);

}
