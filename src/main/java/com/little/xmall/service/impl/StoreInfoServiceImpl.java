package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.store.StoreInfo;
import com.little.xmall.mapper.store.StoreInfoMapper;
import com.little.xmall.service.StoreInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * 商品信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_store_info")
public class StoreInfoServiceImpl extends ServiceImpl<StoreInfoMapper, StoreInfo> implements StoreInfoService {

    private final StoreInfoMapper storeInfoMapper;
    @Override
    public Response<Map<String, Object>> registerStore(StoreInfo storeInfo) {
        storeInfoMapper.insert(storeInfo);
        return Response.success(ResponseCode.STORE_REGISTER_SUCCESS, Map.of("store_id", storeInfo.getStore_id()));
    }
    @Override
    public Response<Map<String, Object>> updateStore(StoreInfo storeInfo) {
        int result = storeInfoMapper.updateById(storeInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        } else {

            return null;
        }
    }
    @Override
    public Response<Map<String, Object>> login(Integer store_id, String password) {
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_id", store_id).eq("password", password);
        StoreInfo storeInfo = storeInfoMapper.selectOne(queryWrapper);

        if (storeInfo != null) {
            return Response.success(ResponseCode.STORE_SIGN_IN_SUCCESS, null);
        } else {
            return Response.error(ResponseCode.STORE_PASSWORD_ERROR, null);
        }
    }
    @Override
    public Response<String> deleteStore(Integer store_id) {
        storeInfoMapper.deleteById(store_id);;
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public Response<Map<String, Object>> getReputation(StoreInfo store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_reputation",storeInfo.getReputation()));
            }
    @Override
    public Response<Map<String, Object>> getFans(StoreInfo store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_fans",storeInfo.getFans_number()));
    }
}
