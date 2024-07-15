package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.store.StoreInfo;
import com.little.xmall.mapper.store.StoreInfoMapper;
import com.little.xmall.service.StoreService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 商品信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_store")
public class StoreServiceImpl extends ServiceImpl<StoreInfoMapper, StoreInfo> implements StoreService {

    private final StoreInfoMapper storeInfoMapper;
    @Override
    public Response<Map<String, Object>> registerStore(StoreInfo storeInfo) {
        if (!storeInfoMapper.selectByMap(Map.of("phone_number", storeInfo.getPhone_number())).isEmpty()) {
            return Response.error(ResponseCode.STORE_HAS_EXIST, null);
        } else {
            storeInfoMapper.insert(storeInfo);
            return Response.success(ResponseCode.STORE_REGISTER_SUCCESS, Map.of("store_id", storeInfo.getStore_id()));
        }
    }
    @Override
    public Response<List<Map<String, Object>>> getStore(Integer store_id) {
        List<StoreInfo> list = storeInfoMapper.selectByMap(Map.of("store_id", store_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
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
        StoreInfo storeid=storeInfoMapper.selectById(store_id);
        if(storeid==null){
            return Response.error(ResponseCode.STORE_NOT_EXIST,null);
        }
        else{
        if (storeInfo != null) {
            return Response.success(ResponseCode.STORE_SIGN_IN_SUCCESS, null);
        } else {
            return Response.error(ResponseCode.STORE_PASSWORD_ERROR, null);
        }
    }
    }
    @Override
    public Response<String> deleteStore(Integer store_id) {
        storeInfoMapper.deleteById(store_id);
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public Response<Map<String, Object>> getReputation(Integer store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_reputation",storeInfo.getReputation()));
            }
    @Override
    public Response<Map<String, Object>> getFans(Integer store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_fans",storeInfo.getFans_number()));
    }

    @Override
    public void addFans(Integer store_id){
        StoreInfo storeInfo=storeInfoMapper.selectById(store_id);
        int fans_number=storeInfo.getFans_number();
        fans_number=fans_number+1;
        storeInfo.setFans_number(fans_number);
        storeInfoMapper.updateById(storeInfo);
    }
    @Override
    public  void deleteFans(Integer store_id){
        StoreInfo storeInfo=storeInfoMapper.selectById(store_id);
        int fans_number=storeInfo.getFans_number();
        fans_number=fans_number-1;
        storeInfo.setFans_number(fans_number);
        storeInfoMapper.updateById(storeInfo);
    }
}
