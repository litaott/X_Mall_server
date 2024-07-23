package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.security.UserPassword;
import com.little.xmall.entity.user.*;


import java.util.Map;
import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author Little
 */

public interface UserService extends IService<UserInfo> {
    /**
     * 创建新的用户信息
     *
     * @param userInfo 用户信息
     * @return Response
     */
    Response<Map<String, Object>> registerUser(UserInfo userInfo);

    /**
     * 获取的用户信息
     *
     * @param user_id 用户ID
     * @return Response
     */
    Response<List<Map<String, Object>>> getUser(int user_id);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return Response
     */
    Response<Map<String, Object>> updateUser(UserInfo userInfo);

    //用户密码修改
    Response<Map<String, Object>> changePassword(UserPassword password);

    /**
     * 验证用户信息
     *
     * @param user_id  用户名
     * @param password 用户密码
     * @return Response
     */
    Response<String> login(Integer user_id, String password);

    /**
     * 删除用户信息
     *
     * @param user_id 用户ID
     * @return Response
     */
    Response<String> deleteUser(int user_id);

    /**
     * 创建新的用户地址信息
     *
     * @param addressInfo 用户地址信息
     * @return Response
     */
    Response<Map<String, Object>> addAddress(AddressInfo addressInfo);

    /**
     * 更新用户地址信息
     *
     * @param addressInfo 用户地址信息
     * @return Response
     */
    Response<Map<String, Object>> updateAddress(AddressInfo addressInfo);

    /**
     * 删除用户地址信息
     *
     * @param address_id 用户地址ID
     * @return Response
     */
    Response<String> deleteAddress(Integer address_id);

    /**
     * 获取用户地址信息
     *
     * @param user_id 用户ID
     * @return Response
     */
    Response<List<Map<String, Object>>> getAddress(Integer user_id);

    /**
     * 添加购物车信息
     *
     * @param cartInfo 购物车信息
     * @return Response
     */
    Response<Map<String, Object>> addCart(CartInfo cartInfo);

    /**
     * 获取购物车信息
     *
     * @param user_id 用户ID
     * @return Response
     */
    Response<List<Map<String, Object>>> getCart(int user_id);

    /**
     * 修改用户购物车信息
     *
     * @param user_id  用户ID
     * @param goods_id 商品ID
     * @param quantity 商品数量
     * @return Response
     */
    Response<String> changeCartGoods(int user_id, int goods_id, int quantity);

    /**
     * 删除用户购物车商品信息
     *
     * @param user_id  用户ID
     * @param goods_id 商品ID
     * @return Response
     */
    Response<String> deleteCart(int user_id, int goods_id);

    /**
     * 新建用户关注信息
     *
     * @param followInfo 关注信息
     * @return Response
     */
    Response<Map<String, Object>> addFollow(FollowInfo followInfo);

    /**
     * 删除用户关注信息
     *
     * @param user_id  用户ID
     * @param store_id 商店ID
     * @return Response
     */
    Response<String> deleteFollow(int store_id, int user_id);

    /**
     * 获取用户关注信息
     *
     * @param user_id 用户ID
     */
    Response<Map<String, List<?>>> getFollow(int user_id);

    /**
     * 获取商户关注状态信息
     *
     * @param user_id  用户ID
     * @param store_id 商店ID
     * @return Response
     */
    Response<String> getIfFollow(int user_id, int store_id);

    /**
     * 扣减用户余额
     *
     * @param user_id 用户ID
     */
    void declineBalance(int user_id, float price);

    Response<Map<String, Object>> addRecord(HistoryInfo historyInfo);

    Response<List<Map<String, Object>>> getRecord(Integer user_id);

    Response<String> deleteRecord(Integer user_id, Integer goods_id);
}
