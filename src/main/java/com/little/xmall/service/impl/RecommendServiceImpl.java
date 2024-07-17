package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.mapper.goods.GoodsInfoMapper;
import com.little.xmall.mapper.user.CartInfoMapper;
import com.little.xmall.mapper.user.UserInfoMapper;
import com.little.xmall.service.RecommendService;
import com.little.xmall.utils.MapUtil;
import com.little.xmall.utils.RecommendUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class RecommendServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements RecommendService {

    private final GoodsInfoMapper goodsInfoMapper;
    private final UserInfoMapper userInfoMapper;
    private final CartInfoMapper cartInfoMapper;

    @DS("db_XMall_goods")
    @Override
    public Response<List<Map<String, Object>>> recommendByUserId(int user_id) {

//        List<Integer> recommendGoodsIds = RecommendUtil.recommendGoodsToUser(user_id, 10, calculateUserSimilarityMatrix(getUserIds()));
//
//        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectBatchIds(recommendGoodsIds);

//        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(goodsInfos));
        return null;
    }

    @DS("db_XMall_user")
    @Override
    public List<Integer> getUserIds() {

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(UserInfo::getUser_id);

        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);

        return userInfos.stream().map(UserInfo::getUser_id).toList();
    }

    @DS("db_XMall_user")
    @Override
    public List<Integer> getCartGoodsIds(int user_id) {

        LambdaQueryWrapper<CartInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartInfo::getUser_id, user_id);

        List<CartInfo> cartInfos = cartInfoMapper.selectList(queryWrapper);

        return cartInfos.stream().map(CartInfo::getGoods_id).toList();
    }

    /**
     * 计算用户相似度矩阵
     */
    public Map<Integer, Map<Integer, Double>> calculateUserSimilarityMatrix(List<Integer> userIds) {

        // 创建相似度矩阵
        Map<Integer, Map<Integer, Double>> similarityMatrix = new HashMap<>();

        // 计算用户间的相似度
        for (int userIdA : userIds) {

            // 创建相似度矩阵行
            Map<Integer, Double> similarityRow = new HashMap<>();

            // 添加两个用户之间的相似度
            for (int userIdB : userIds) {
                if (userIdA != userIdB) {
                    double similarity = calculateCosineSimilarity(userIdA, userIdB);
                    similarityRow.put(userIdB, similarity);
                }
            }
            similarityMatrix.put(userIdA, similarityRow);
        }
        return similarityMatrix;
    }

    /**
     * 计算余弦相似度
     */
    public double calculateCosineSimilarity(int userIdA, int userIdB) {

        // 获取用户A和用户B的购物车商品id
        List<Integer> goodsIdsA = getCartGoodsIds(userIdA);
        List<Integer> goodsIdsB = getCartGoodsIds(userIdB);

        // 计算用户A和用户B共同加入购物车的商品数量
        int commonLikedGoods = 0;
        for (Integer goodsId : goodsIdsA){
            if (goodsIdsB.contains(goodsId))
                commonLikedGoods++;
        }

        // 计算用户A和用户B分别加入购物车的商品数量
        int likedPostsA = goodsIdsA.size();
        int likedPostsB = goodsIdsB.size();

        // 计算余弦相似度
        return commonLikedGoods / Math.sqrt(likedPostsA * likedPostsB);
    }
}
