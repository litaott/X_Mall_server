package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.PreferenceInfo;

import java.util.List;
import java.util.Map;

/**
 * 优惠服务接口
 * @author Little
 */
public interface PreferenceService extends IService<PreferenceInfo> {

    /**
     * 获取商品优惠信息
     * @param goods_id 商品id
     * @return Response
     */
    Response<List<Map<String,Object>>> get_preference_info(int goods_id);

    /**
     * 添加商品优惠信息
     * @param preferenceInfo 优惠信息
     * @return Response
     */
    Response<Map<String,Object>> add_preference(PreferenceInfo preferenceInfo);
}
