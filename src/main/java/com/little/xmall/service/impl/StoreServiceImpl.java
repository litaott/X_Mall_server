package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.security.StorePassword;
import com.little.xmall.entity.store.StoreInfo;
import com.little.xmall.mapper.store.StoreInfoMapper;
import com.little.xmall.service.StoreService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final GoodsServiceImpl goodsServiceImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //商店注册
    @Override
    public Response<Map<String, Object>> registerStore(StoreInfo storeInfo) {
        if (!storeInfoMapper.selectByMap(Map.of("phone_number", storeInfo.getPhone_number())).isEmpty()) {
            return Response.error(ResponseCode.STORE_HAS_EXIST, null);
        } else {
            //商店密码加密
            storeInfo.setPassword(bCryptPasswordEncoder.encode(storeInfo.getPassword()));
            storeInfoMapper.insert(storeInfo);
            return Response.success(ResponseCode.STORE_REGISTER_SUCCESS, Map.of("store_id", storeInfo.getStore_id()));
        }
    }

    //获取商店信息
    @Override
    public Response<List<Map<String, Object>>> getStore(Integer store_id) {
        List<StoreInfo> list = storeInfoMapper.selectByMap(Map.of("store_id", store_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }

    //修改商店信息
    @Override
    public Response<Map<String, Object>> updateStore(StoreInfo storeInfo) {
        int result = storeInfoMapper.updateById(storeInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        } else {

            return null;
        }
    }

    //商店登录
    @Override
    public Response<Map<String, Object>> login(Integer store_id, String password) {
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_id", store_id);
        StoreInfo storeInfo = storeInfoMapper.selectOne(queryWrapper);
        StoreInfo storeid=storeInfoMapper.selectById(store_id);
        String result=storeInfo.getPassword();
        if(storeid==null){
            return Response.error(ResponseCode.STORE_NOT_EXIST,null);
        }
        else{
        if (bCryptPasswordEncoder.matches(password,result)) {
            return Response.success(ResponseCode.STORE_SIGN_IN_SUCCESS, null);
        } else {
            return Response.error(ResponseCode.STORE_PASSWORD_ERROR, null);
        }
    }
    }
    @Override
    public Response<Map<String, Object>> changePassword(StorePassword password){
        QueryWrapper<StoreInfo>storeInfoQueryWrapper=new QueryWrapper<>();
        storeInfoQueryWrapper.eq("store_id",password.getStore_id());
        StoreInfo storeInfo=storeInfoMapper.selectOne(storeInfoQueryWrapper);
        if(!Objects.equals(storeInfo.getStore_id(), password.getStore_id())){
            return Response.error(ResponseCode.FAIL,null);
        }
        else {
            if(bCryptPasswordEncoder.matches(storeInfo.getPassword(),password.getOld_password())){
                storeInfo.setPassword(bCryptPasswordEncoder.encode(password.getNew_password()));
                storeInfoMapper.updateById(storeInfo);
                return Response.success(ResponseCode.SUCCESS,null);
            }
            else {
                return Response.error(ResponseCode.STORE_PASSWORD_ERROR,null);
            }
        }
    }
    //商店注销
    @Override
    public Response<String> deleteStore(Integer store_id) {
        storeInfoMapper.deleteById(store_id);
        goodsServiceImpl.deleteStoreGoods(store_id);
        return Response.success(ResponseCode.SUCCESS, null);
    }

    //获取商店信誉信息
    @Override
    public Response<Map<String, Object>> getReputation(Integer store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_reputation",storeInfo.getReputation()));
            }

    //获取商店粉丝信息
    @Override
    public Response<Map<String, Object>> getFans(Integer store_id) {
        StoreInfo storeInfo = storeInfoMapper.selectById(store_id);
        if(storeInfo ==null){
            return Response.error(ResponseCode.FAIL,null);
        }
        return Response.error(ResponseCode.SUCCESS,Map.of("store_fans",storeInfo.getFans_number()));
    }

    //商店粉丝数增加方法
    @Override
    public void addFans(Integer store_id){
        StoreInfo storeInfo=storeInfoMapper.selectById(store_id);
        int fans_number=storeInfo.getFans_number();
        fans_number=fans_number+1;
        storeInfo.setFans_number(fans_number);
        storeInfoMapper.updateById(storeInfo);
    }
    //商店粉丝数减少方法
    @Override
    public  void deleteFans(Integer store_id){
        StoreInfo storeInfo=storeInfoMapper.selectById(store_id);
        int fans_number=storeInfo.getFans_number();
        fans_number=fans_number-1;
        storeInfo.setFans_number(fans_number);
        storeInfoMapper.updateById(storeInfo);
    }
    //获取商店名方法
    @Override
    public String getStoreName(Integer store_id) {
        return storeInfoMapper.selectById(store_id).getStore_name();
    }
}
