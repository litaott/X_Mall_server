package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.store.StoreInfo;

import java.util.List;
import java.util.Map;
public interface StoreService extends IService<StoreInfo>{
    Response<Map<String, Object>> registerStore(StoreInfo storeInfo);

    Response<List<Map<String, Object>>> getStore(Integer store_id);

    Response<Map<String, Object>> updateStore(StoreInfo storeInfo);

    Response<Map<String, Object>> login(Integer store_id, String password);

    Response<String> deleteStore(Integer store_id);


    Response<Map<String, Object>> getReputation(Integer store_id);

    Response<Map<String, Object>> getFans(Integer store_id);
}
