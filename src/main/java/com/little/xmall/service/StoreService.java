package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.security.StorePassword;
import com.little.xmall.entity.store.StoreInfo;

import java.util.List;
import java.util.Map;
/**
 * 商店信息Service接口
 * @author Little
 */

public interface StoreService extends IService<StoreInfo>{
    /**
     * 创建新的商店信息
     * @param storeInfo 商店信息
     * @return Response
     */
    Response<Map<String, Object>> registerStore(StoreInfo storeInfo);

    /**
     * 获取商店信息
     * @param store_id 商店ID
     * @return Response
     */
    Response<List<Map<String, Object>>> getStore(Integer store_id);

    /**
     * 修改商店信息
     * @param storeInfo 商店信息
     * @return Response
     */
    Response<Map<String, Object>> updateStore(StoreInfo storeInfo);

    /**
     * 登录商店
     * @param store_id 商店ID
     * @param password 商店I密码
     * @return Response
     */
    Response<Map<String, Object>> login(Integer store_id, String password);

    Response<Map<String, Object>> changePassword(StorePassword password);

    /**
     * 删除商店信息
     * @param store_id 商店ID
     * @return Response
     */
    Response<String> deleteStore(Integer store_id);

    /**
     * 获取商店信誉信息
     * @param store_id 商店ID
     * @return Response
     */
    Response<Map<String, Object>> getReputation(Integer store_id);

    /**
     * 获取商店粉丝信息
     * @param store_id 商店ID
     * @return Response
     */
    Response<Map<String, Object>> getFans(Integer store_id);

    /**
     * 商店粉丝增加方法
     * @param store_id 商店ID
     */
    void addFans(Integer store_id);

    /**
     * 商店粉丝减少方法
     * @param store_id 商店ID
     */
    void deleteFans(Integer store_id);

    /**
     * 获取商店名称
     * @param store_id 商店ID
     * @return String
     */
    String getStoreName(Integer store_id);
}
