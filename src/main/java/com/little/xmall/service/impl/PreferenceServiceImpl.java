package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.PreferenceInfo;
import com.little.xmall.mapper.PreferenceMapper;
import com.little.xmall.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 优惠服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_preference")
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, PreferenceInfo> implements PreferenceService {

    private final PreferenceMapper preferenceMapper;

    @Override
    public Response<List<Map<String, Object>>> get_preference_info(int goods_id) {
        return null;
    }

    @Override
    public Response<List<Map<String, Object>>> get_preference_info(PreferenceInfo preferenceInfo) {
        return null;
    }
}
