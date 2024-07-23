package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.CommentInfo;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.entity.goods.GoodsImageTestInfo;
import com.little.xmall.entity.goods.GoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * 商品信息Service接口
 *
 * @author Little
 */

public interface GoodsService extends IService<GoodsInfo> {
    /**
     * 创建新的商品信息
     *
     * @param goodsInfo 商品信息
     * @return Response
     */
    Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo);

    /**
     * 获取商品信息
     *
     * @param goods_id 商品ID
     * @return Response
     */
    Response<Map<String, Object>> getGoods(Integer goods_id);

    /**
     * 修改商品信息
     *
     * @param goodsInfo 商品信息
     * @return Response
     */
    Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo);

    Response<List<Map<String, Object>>> getAllGoods();

    //添加商品图片
    Response<Map<String, Object>> addGoodsImage(GoodsImageInfo goodsImageInfo);

    /**
     * 修改商品图片信息
     *
     * @param goodsImageInfo 商品图片信息
     * @return Response
     */
    Response<Map<String, Object>> updateGoodsImage(GoodsImageInfo goodsImageInfo);

    /**
     * 删除商品信息
     *
     * @param goods_id 商品ID
     * @return Response
     */
    Response<String> deleteGoods(Integer goods_id);

    /**
     * 删除商品图片信息
     *
     * @param image_id 商品图片ID
     * @return Response
     */
    Response<String> deleteGoodsImage(Integer image_id);

    /**
     * 新建商品评论信息
     *
     * @param commentInfo 商品评论ID
     * @return Response
     */
    Response<Map<String, Object>> addComment(CommentInfo commentInfo);

    /**
     * 获取商品评论信息
     *
     * @param goods_id 商品评论ID
     * @return Response
     */
    Response<Map<String, List<?>>> getComment(Integer goods_id);

    /**
     * 删除商品评论信息
     *
     * @param comment_id 商品评论ID
     * @return Response
     */
    Response<String> deleteComment(int comment_id);

    void deleteStoreGoods(int store_id);

    /**
     * 减少商品库存
     *
     * @param goods_id 商品ID
     * @param quantity 减少数量
     */
    void declineQuantity(int goods_id, int quantity);

    Response<List<Map<String, Object>>> addManyImages(GoodsImageTestInfo goodsImageTestInfo);

}
