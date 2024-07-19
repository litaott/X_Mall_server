package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.GoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * 推荐服务接口
 * @author Little
 */
public interface RecommendService extends IService<GoodsInfo> {

    /**
     * 获取用户推荐商品信息
     *
     * @param user_id 用户id
     * @return Response
     */
    Response<List<Map<String, Object>>> recommendByUserId(int user_id);

    /**
     * 获取所有用户id
     *
     * @return List
     */
    List<Integer> getUserIds();

    /**
     * 获取用户购物车中的商品ID列表
     *
     * @param user_id 用户id
     * @return List
     */
    List<Integer> getCartGoodsIds(int user_id);

}