package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.CommentInfo;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.entity.goods.GoodsInfo;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<GoodsInfo>{
    Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo);

    Response<List<Map<String, Object>>> getGoods(Integer goods_id);

    Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo);


    Response<Map<String, Object>> updateGoodsImage(GoodsImageInfo goodsImageInfo);

    Response<String> deleteGoods(Integer goods_id);

    Response<String> deleteGoodsImage(Integer image_id);

    Response<Map<String, Object>> addComment(CommentInfo commentInfo);

    Response<Map<String, List<?>>> getComment(Integer goods_id);

    Response<String> deleteComment(int comment_id);
}
