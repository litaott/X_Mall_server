package com.little.xmall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.security.UserPassword;
import com.little.xmall.entity.user.AddressInfo;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.entity.user.FollowInfo;
import com.little.xmall.mapper.user.AddressInfoMapper;
import com.little.xmall.mapper.user.FollowInfoMapper;
import com.little.xmall.mapper.user.UserInfoMapper;
import com.little.xmall.mapper.user.CartInfoMapper;

import com.little.xmall.service.UserService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_user")

public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    private final UserInfoMapper userInfoMapper;
    private final AddressInfoMapper addressInfoMapper;
    private final CartInfoMapper cartInfoMapper;
    private final FollowInfoMapper followInfoMapper;
    private final StoreServiceImpl storeServiceImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//
    @Override
    public Response<Map<String, Object>> registerUser(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();

        //查询用户输入的手机号是否重复
        queryWrapper.eq("phone_number",userInfo.getPhone_number()).eq("username",userInfo.getUsername());
        UserInfo userInfo1=userInfoMapper.selectOne(queryWrapper);

        //如果用户输入已经注册过的手机号则会返回用户已注册
        if (userInfo1!=null) {
            return Response.error(ResponseCode.USER_EXIST, null);
        } else {
            userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
            userInfoMapper.insert(userInfo);
            return Response.success(ResponseCode.USER_REGISTER_SUCCESS, Map.of("user_id", userInfo.getUser_id()));
        }
    }

    //获取用户信息
    @Override
    public Response<List<Map<String, Object>>> getUser(int user_id) {
        List<UserInfo> list = userInfoMapper.selectByMap(Map.of("user_id", user_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }

    //更新用户信息
    @Override
    public Response<Map<String, Object>> updateUser(UserInfo userInfo) {
        int result = userInfoMapper.updateById(userInfo);
        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        } else {

            return null;
        }
    }
//用户密码修改
    @Override
    public Response<Map<String, Object>> changePassword(UserPassword password){
        QueryWrapper<UserInfo>userInfoQueryWrapper=new QueryWrapper<>();
        userInfoQueryWrapper.eq("user_id",password.getUser_id());
        UserInfo userInfo=userInfoMapper.selectOne(userInfoQueryWrapper);
        if(!Objects.equals(userInfo.getUser_id(), password.getUser_id())){
            return Response.error(ResponseCode.FAIL,null);
        }
        else {
            if(bCryptPasswordEncoder.matches(userInfo.getPassword(),password.getOld_password())){
                userInfo.setPassword(bCryptPasswordEncoder.encode(password.getNew_password()));
                userInfoMapper.updateById(userInfo);
                return Response.success(ResponseCode.SUCCESS,null);
            }
            else {
                return Response.error(ResponseCode.STORE_PASSWORD_ERROR,null);
            }
        }
    }
    //用户登录
    @Override
    public Response<String> login(Integer user_id, String password) {

        //查找用户ID
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        UserInfo userid=userInfoMapper.selectById(user_id);
        String result=userInfo.getPassword();

        //用户ID不存在
        if (userid == null) {
            return Response.error(ResponseCode.USER_NOT_EXIST, null);
        } else {
            //用户密码是否正确
            if(bCryptPasswordEncoder.matches(password,result)){
                return  Response.success(ResponseCode.USER_SIGN_IN_SUCCESS,null);
            }
            else{
            return Response.error(ResponseCode.USER_PASSWORD_ERROR, null);
        }
        }
    }

    //用户注销
    @Override
    public Response<String> deleteUser(int user_id){
        userInfoMapper.deleteById(user_id);
        QueryWrapper<AddressInfo> queryWrapper = new QueryWrapper<>();
        addressInfoMapper.delete(queryWrapper.eq("user_id",user_id));
        return Response.success(ResponseCode.SUCCESS,null);
    }

    //用户添加地址
    @Override
    public Response<Map<String, Object>> addAddress(AddressInfo addressInfo) {
        addressInfoMapper.insert(addressInfo);
        return Response.success(ResponseCode.SUCCESS, Map.of("address_id", addressInfo.getAddress_id()));
    }

    //用户更新地址信息
    @Override
    public Response<Map<String, Object>> updateAddress(AddressInfo addressInfo) {
        int result = addressInfoMapper.updateById(addressInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, Map.of("user_id", addressInfo.getUser_id()));
        } else {

            return Response.error(ResponseCode.FAIL, null);
        }
    }

    //用户删除地址
    @Override
    public Response<String> deleteAddress(Integer address_id) {//测试完成
        addressInfoMapper.deleteById(address_id);
        return Response.success(ResponseCode.SUCCESS, null);
    }

    //用户获取地址信息
    @Override
    public Response<List<Map<String, Object>>> getAddress(Integer user_id) {
        List<AddressInfo> list = addressInfoMapper.selectByMap(Map.of("user_id", user_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }

    //用户添加购物车
    @Override
    public Response<Map<String, Object>> addCart(CartInfo cartInfo) {

        cartInfoMapper.insert(cartInfo);
        return Response.success(ResponseCode.SUCCESS,null);
    }

    //用户获取购物车
    @Override
    public Response<List<Map<String, Object>>> getCart(int user_id) {
        LambdaQueryWrapper<CartInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartInfo::getUser_id, user_id);
        if (cartInfoMapper.selectList(queryWrapper).isEmpty())
            return Response.success(ResponseCode.SUCCESS, null);
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(cartInfoMapper.selectList(queryWrapper)));
    }
    //用户修改购物车商品数量
    @Override
    public Response<String>changeCartGoods(int user_id, int goods_id, int quantity) {
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",goods_id).eq("user_id",user_id);
        CartInfo cartInfo=cartInfoMapper.selectOne(queryWrapper);
        cartInfo.setQuantity(quantity);
        cartInfoMapper.update(queryWrapper);
        return Response.success(ResponseCode.SUCCESS,null);
    }
    //用户删除购物车商品
    @Override
    public Response<String> deleteCart(int user_id,int goods_id) {
        QueryWrapper<CartInfo>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id).eq("goods_id",goods_id);
        cartInfoMapper.delete(queryWrapper);
        return Response.success(ResponseCode.SUCCESS, null);
    }
    //用户添加对商铺的关注
    @Override
    public Response<Map<String, Object>> addFollow(FollowInfo followInfo) {
        followInfoMapper.insert(followInfo);
        UserInfo userInfo=userInfoMapper.selectById(followInfo.getUser_id());
        //用户关注数+1
        int follow_number=userInfo.getFollow_number();
        follow_number=follow_number+1;
        userInfo.setFollow_number(follow_number);
        userInfoMapper.updateById(userInfo);
        Integer store_id=followInfo.getStore_id();
        //商铺关注数+1
        storeServiceImpl.addFans(store_id);
        return Response.success(ResponseCode.SUCCESS,null);
    }
    //用户取消关注
    @Override
    public Response<String> deleteFollow(int store_id,int user_id) {
        QueryWrapper<FollowInfo> queryWrapper = new QueryWrapper<>();
        followInfoMapper.delete(queryWrapper.eq("user_id",user_id).eq("store_id",store_id));
        //商铺关注数-1
        storeServiceImpl.deleteFans(store_id);
        //用户关注数-1
        UserInfo userInfo=userInfoMapper.selectById(user_id);
        int follow_number=userInfo.getFollow_number();
        follow_number=follow_number-1;
        userInfo.setFollow_number(follow_number);
        userInfoMapper.updateById(userInfo);
        return Response.success(ResponseCode.SUCCESS, null);
    }
//用户获取关注信息
    @Override
    public  Response<Map<String, List<?>>>getFollow(int user_id){
        Map<String, List<?>> result = new HashMap<>();
        QueryWrapper<FollowInfo> followInfoQueryWrapper = new QueryWrapper<>();
        followInfoQueryWrapper.eq("user_id",user_id);
        List<FollowInfo> list=followInfoMapper.selectList(followInfoQueryWrapper);
        result.put("followInfo",list);
        return Response.success(ResponseCode.SUCCESS, result);
    }
//商铺是否已被关注
    @Override
    public Response<String>getIfFollow(int user_id, int store_id){
        QueryWrapper<FollowInfo> followInfoQueryWrapper=new QueryWrapper<>();

        if(followInfoMapper.selectOne(followInfoQueryWrapper.eq("user_id",user_id).eq("store_id",store_id)) !=null){
            return Response.success(ResponseCode.USER_FOLLOWED, null);
        }
        else {
            return Response.error(ResponseCode.USER_NOT_FOLLOW,null);
        }
    }

    @Override
    public void declineBalance(int user_id, float price) {
        UserInfo userInfo = userInfoMapper.selectById(user_id);
        userInfo.setBalance(userInfo.getBalance() - price);
        userInfoMapper.updateById(userInfo);
    }
}




