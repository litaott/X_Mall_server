package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.GoodsInfo;

import java.util.List;
import java.util.Map;

public interface GoodsInfoService extends IService<GoodsInfo>{
    Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo);

    Response<List<Map<String, Object>>> getGoods(Integer goods_id);

    Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo);

    Response<String> deleteGoods(Integer goods_id);
}
