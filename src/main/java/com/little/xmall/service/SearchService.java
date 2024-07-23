package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.GoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * 搜索服务接口
 *
 * @author Little
 */
public interface SearchService extends IService<GoodsInfo> {

    /**
     * 获取商品优惠信息
     *
     * @param keyword 关键词
     * @return Response
     */
    Response<List<List<Map<String, Object>>>> searchByKeyword(String keyword);

}
