package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.user.AddressInfo;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.FollowInfo;
import com.little.xmall.entity.user.UserInfo;


import java.util.Map;
import java.util.List;

/**
 * 用户信息Service接口
 * @author Little
 */

public interface UserService extends IService<UserInfo>{
    /**
     * 创建新的用户信息
     * @param userInfo 用户信息
     * @return Response
     */
    Response<Map<String, Object>> registerUser(UserInfo userInfo);

    Response<List<Map<String, Object>>> getUser(int user_id);

    Response<Map<String, Object>> updateUser(UserInfo userInfo);

    Response<Map<String, Object>> login(Integer user_id, String password);

    Response<Map<String, Object>> addAddress(AddressInfo addressInfo);
    Response<Map<String, Object>> updateAddress(AddressInfo addressInfo);


    Response<String> deleteAddress(Integer address_id);


    Response<Map<String, Object>> addCart(CartInfo cartInfo);

    Response<String> deleteCart(int goods_id);

    Response<Map<String, Object>> addFollow(FollowInfo followInfo);

    Response<String> deleteFollow(int store_id);
}
