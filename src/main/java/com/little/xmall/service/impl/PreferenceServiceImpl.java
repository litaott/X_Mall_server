package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.preference.DiscountInfo;
import com.little.xmall.entity.preference.GiftInfo;
import com.little.xmall.entity.preference.PreferenceInfo;
import com.little.xmall.entity.preference.ReductionInfo;
import com.little.xmall.mapper.preference.DiscountMapper;
import com.little.xmall.mapper.preference.GiftMapper;
import com.little.xmall.mapper.preference.PreferenceMapper;
import com.little.xmall.mapper.preference.ReductionMapper;
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
    private final DiscountMapper discountMapper;
    private final ReductionMapper reductionMapper;
    private final GiftMapper giftMapper;

    @Override
    public Response<List<Map<String, Object>>> get_preference_info(int goods_id) {

        // 查询优惠信息
        LambdaQueryWrapper<PreferenceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PreferenceInfo::getGoods_id, goods_id);
        List<PreferenceInfo> list = preferenceMapper.selectObjs(queryWrapper);

        // 优惠信息为空
        if (list.isEmpty())
            return Response.success(ResponseCode.PREFERENCE_GET_SUCCESS, null);

        // 添加具体优惠信息
        for (PreferenceInfo pref:list) {
            int category_index = pref.getCategory_index();
            int pref_id = pref.getPref_id();
            switch (category_index) {
                case 0 -> pref.setReduction(reductionMapper.selectById(pref_id).getReduction());
                case 1 -> pref.setDiscount(discountMapper.selectById(pref_id).getDiscount());
                case 2 -> pref.setGift(giftMapper.selectById(pref_id).getGift());
            }
        }

        return Response.success(ResponseCode.PREFERENCE_GET_SUCCESS, MapUtil.getMapList(list));
    }

    @Override
    public Response<Map<String, Object>> add_preference(PreferenceInfo preferenceInfo) {

        // 参数为空
        if (preferenceInfo == null)
            return Response.error(ResponseCode.FAIL, null);

        // 添加具体优惠信息
        int category_index = preferenceInfo.getCategory_index();
        switch (category_index) {
            case 0 -> {
                ReductionInfo reductionInfo = new ReductionInfo();
                reductionInfo.setReduction(preferenceInfo.getReduction());
                reductionMapper.insert(reductionInfo);
                preferenceInfo.setPref_id(reductionInfo.getReduction_id());
            }
            case 1 -> {
                DiscountInfo discountInfo = new DiscountInfo();
                discountInfo.setDiscount(preferenceInfo.getDiscount());
                discountMapper.insert(discountInfo);
                preferenceInfo.setPref_id(discountInfo.getDiscount_id());
            }
            case 2 -> {
                GiftInfo giftInfo = new GiftInfo();
                giftInfo.setGift(preferenceInfo.getGift());
                giftMapper.insert(giftInfo);
                preferenceInfo.setPref_id(giftInfo.getGift_id());
            }
        }

        // 添加优惠信息
        preferenceMapper.insert(preferenceInfo);

        int id = preferenceInfo.getPreference_id();
        return Response.success(ResponseCode.PREFERENCE_ADD_SUCCESS, Map.of("preference_id",id));
    }
}
