package com.little.xmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.user.AddressInfo;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.mapper.user.AddressInfoMapper;
import com.little.xmall.mapper.user.UserInfoMapper;
import com.little.xmall.mapper.user.CartInfoMapper;
import com.little.xmall.service.UserInfoService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 用户信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_user_info")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    private final UserInfoMapper userInfoMapper;
    private final AddressInfoMapper addressInfoMapper;
    private final CartInfoMapper cartInfoMapper;

    @Override
    public Response<Map<String, Object>> registerUser(UserInfo userInfo) {
        if (userInfoMapper.selectByMap(Map.of("phone_number", userInfo.getUsername())).size() > 0) {
            return Response.error(ResponseCode.USER_EXIST, null);
        } else {
            userInfoMapper.insert(userInfo);
            return Response.success(ResponseCode.USER_REGISTER_SUCCESS, Map.of("user_id", userInfo.getUser_id()));
        }
    }

    @Override
    public Response<List<Map<String, Object>>> getUser(int user_id) {
        List<UserInfo> list = userInfoMapper.selectByMap(Map.of("user_id", user_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }

    @Override
    public Response<Map<String, Object>> updateUser(UserInfo userInfo) {
        int result = userInfoMapper.updateById(userInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        } else {

            return null;
        }
    }

    @Override
    public Response<Map<String, Object>> login(Integer user_id, String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id).eq("password", password);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if (userInfo != null) {
            return Response.success(ResponseCode.STORE_SIGN_IN_SUCCESS, null);
        } else {
            return Response.error(ResponseCode.USER_PASSWORD_ERROR, null);
        }
    }

    @Override
    public Response<Map<String, Object>> addAddress(AddressInfo addressInfo) {
        addressInfoMapper.insert(addressInfo);
        return Response.success(ResponseCode.SUCCESS, Map.of("address_id", addressInfo.getAddress_id()));
    }

    @Override
    public Response<Map<String, Object>> updateAddress(AddressInfo addressInfo) {
        int result = addressInfoMapper.updateById(addressInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, Map.of("user_id", addressInfo.getUser_id()));
        } else {

            return Response.error(ResponseCode.FAIL, null);
        }
    }

    @Override
    public Response<String> deleteAddress(Integer address_id) {
        addressInfoMapper.deleteById(address_id);;
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public Response<Map<String, Object>> addCart(CartInfo cartInfo) {
        cartInfoMapper.insert(cartInfo);
        return Response.success(ResponseCode.SUCCESS,null);
    }

}




