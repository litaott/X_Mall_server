package com.little.xmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.UserInfo;
import com.little.xmall.mapper.UserInfoMapper;
import com.little.xmall.service.UserInfoService;
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
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Response<Map<String, Object>> apply(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo createUser(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoMapper.findById(userId);
    }



    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        userInfoMapper.update(userInfo);
        return userInfo;
    }

    @Override
    public void deleteUser(int userId) {
        userInfoMapper.delete(userId);
    }

    @Override
    public List<UserInfo> getUsersByCriteria(Map<String, Object> criteria) {
        return null;
    }


}

