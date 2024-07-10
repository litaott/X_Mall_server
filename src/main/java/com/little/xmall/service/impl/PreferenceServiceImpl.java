package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.preference.PreferenceInfo;
import com.little.xmall.mapper.preference.PreferenceMapper;
import com.little.xmall.service.PreferenceService;
import com.little.xmall.utils.MapUtil;
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
        LambdaQueryWrapper<PreferenceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PreferenceInfo::getGoods_id, goods_id);
        List<PreferenceInfo> list = preferenceMapper.selectObjs(queryWrapper);

        if (list.size() == 0)
            return Response.success(ResponseCode.PREFERENCE_GET_SUCCESS, null);

        for (PreferenceInfo pref:list) {
            String category = pref.getCategory();
            int pref_id = pref.getPref_id();
            switch (category) {
                case "打折" -> {
                    float discount = preferenceMapper.getDiscount(pref_id);
                    pref.setDiscount(discount);
                }
                case "降价" -> {
                    float reduction = preferenceMapper.getReduction(pref_id);
                    pref.setReduction(reduction);
                }
                case "赠品" -> {
                    String gift = preferenceMapper.getGift(pref_id);
                    pref.setGift(gift);
                }
            }
        }

        return Response.success(ResponseCode.PREFERENCE_GET_SUCCESS, MapUtil.getMapList(list));
    }

    @Override
    public Response<Map<String, Object>> add_preference(PreferenceInfo preferenceInfo) {

        if (preferenceInfo == null)
            return Response.error(ResponseCode.FAIL, null);

        preferenceMapper.insert(preferenceInfo);

        String category = preferenceInfo.getCategory();
        switch (category) {
            case "打折" -> {
            }
            case "降价" -> {
            }
            case "赠品" -> {
            }
        }

        int id = preferenceInfo.getPreference_id();
        return Response.success(ResponseCode.PREFERENCE_ADD_SUCCESS, Map.of("preference_id",id));
    }

}
