package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.recommend.RecommendGoods;
import com.little.xmall.mapper.goods.GoodsImageInfoMapper;
import com.little.xmall.mapper.goods.GoodsInfoMapper;
import com.little.xmall.service.RecommendService;
import com.little.xmall.utils.MapUtil;
import com.little.xmall.utils.RecommendUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推荐服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_goods")
public class RecommendServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements RecommendService {

    private final GoodsInfoMapper goodsInfoMapper;
    private final GoodsImageInfoMapper goodsImageInfoMapper;
    private final RecommendUtil recommendUtil;

    @Override
    public Response<List<Map<String, Object>>> recommendByUserId(int user_id) {

        // 获取用户商品购买记录
        List<Integer> userOrderGoodsIds = recommendUtil.getUserOrderGoodsIds(user_id);
        List<GoodsInfo> userOrderGoodsInfo;
        if (!userOrderGoodsIds.isEmpty())
            userOrderGoodsInfo = goodsInfoMapper.selectBatchIds(userOrderGoodsIds);
        else
            userOrderGoodsInfo = new ArrayList<>();

        // 提取用户购买商品的推荐要素
        List<RecommendGoods> userOrderGoods = new ArrayList<>();
        for (GoodsInfo goodsInfo : userOrderGoodsInfo)
            userOrderGoods.add(new RecommendGoods(goodsInfo));

        // 获取全部商品信息
        List<GoodsInfo> goodsList = goodsInfoMapper.selectList(null);
        List<RecommendGoods> allGoods = new ArrayList<>();

        // 去除用户已购买商品，并提取推荐要素
        for (GoodsInfo goodsInfo : goodsList) {
            if (!userOrderGoodsIds.contains(goodsInfo.getGoods_id()))
                allGoods.add(new RecommendGoods(goodsInfo));
        }

        // 根据推荐算法获取推荐商品
        List<Integer> recommendGoodsIdsOrder = recommendUtil.recommendByUserId(user_id, userOrderGoods, allGoods);

        List<GoodsInfo> goodsInfos = new ArrayList<>();
        for (Integer recommendGoodsId : recommendGoodsIdsOrder) {
            GoodsInfo goodsInfo = goodsInfoMapper.selectById(recommendGoodsId);
            LambdaQueryWrapper<GoodsImageInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(GoodsImageInfo::getGoods_id, recommendGoodsId);
            List<String> images = goodsImageInfoMapper.selectList(queryWrapper).stream().map(GoodsImageInfo::getImage_url).toList();
            goodsInfo.setImages(images);
            goodsInfos.add(goodsInfo);
        }

        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(goodsInfos));
    }

}
