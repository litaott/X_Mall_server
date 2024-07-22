package com.little.xmall.utils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.order.OrderInfo;
import com.little.xmall.entity.order.OrderItemInfo;
import com.little.xmall.entity.recommend.RecommendGoods;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.mapper.user.CartInfoMapper;
import com.little.xmall.mapper.user.UserInfoMapper;
import com.little.xmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 推荐算法工具类
 *
 * @author Little
 */
@Component
@RequiredArgsConstructor
@DS("db_XMall_user")
public class RecommendUtil {

    private final UserInfoMapper userInfoMapper;
    private final CartInfoMapper cartInfoMapper;
    private final OrderService orderService;



    /**
     * 综合目标用户信息推荐商品
     */
    public List<Integer> recommendByUserId(int user_id, List<RecommendGoods> userOrderGoods, List<RecommendGoods> goodsList) {

        // 设置推荐算法权重
        final int nameFactor = 10;
        final int categoryFactor = 4;
        final int priceFactor = 10;
        final int saleNumFactor = 10;
        final int similarFactor = 10;

        // 获取商品总数
        int goodsNum = goodsList.size();

        // 临时存储商品推荐得分
        double score;

        // 依据商品名与用户历史购买商品名匹配度赋分
        int[] result;
        for (RecommendGoods goods : goodsList){
            goods.setScore(100);
            for (RecommendGoods userGoods : userOrderGoods){
                result = SearchUtil.fuzzyMatch(goods.getGoods_name(), userGoods.getGoods_name());
                if (result[0] == 1)
                    goods.setScore(Math.min(goods.getScore(), result[1]));
            }
        }
        goodsList.sort(Comparator.comparingDouble(RecommendGoods::getScore));
        System.out.println(goodsList);
        for (int i = 0; i < goodsNum; i++) {
            if (goodsList.get(i).getScore() != 100) {
                score = (1 - 1.0 / goodsNum * i) * nameFactor;
                goodsList.get(i).setScore(score);
            }
            goodsList.get(i).setScore(0);
        }

        // 依据商品类别与用户历史购买商品类别匹配度赋分
        for (RecommendGoods goods : goodsList){
            for (RecommendGoods userGoods : userOrderGoods){
                if (goods.getCategory().equals(userGoods.getCategory())){
                    score = goods.getScore() + categoryFactor;
                    goods.setScore(score);
                }
            }
        }

        // 依据价格赋分
        goodsList.sort(Comparator.comparingDouble(RecommendGoods::getPrice));
        System.out.println(goodsList);
        for (int i = 0; i < goodsNum; i++) {
            score = goodsList.get(i).getScore() + (1 - 1.0 / goodsNum * i) * priceFactor;
            goodsList.get(i).setScore(score);
        }

        // 依据销量赋分
        goodsList.sort(Comparator.comparingInt(RecommendGoods::getSale_number));
        System.out.println(goodsList);
        for (int i = 0; i < goodsNum; i++) {
            score = goodsList.get(i).getScore() + (1.0 / goodsNum * i) * saleNumFactor;
            goodsList.get(i).setScore(score);
        }

//        // 依据相似用户购物车赋分
//        List<Integer> recommendGoodsIdsSimilar = recommendBySimilarUser(user_id);
//        for (RecommendGoods goods : goodsList){
//            if (recommendGoodsIdsSimilar.contains(goods.getGoods_id())){
//                score = goods.getScore() + similarFactor;
//                goods.setScore(score);
//            }
//        }

        // 依据最终得分排序
        goodsList.sort(Comparator.comparingDouble(RecommendGoods::getScore).reversed());
        System.out.println(goodsList);
        return goodsList.stream().map(RecommendGoods::getGoods_id).toList();
    }

    /**
     * 根据用户相似度矩阵推荐商品给目标用户
     */
    public List<Integer> recommendBySimilarUser(int user_id) {

        int k = getCartGoodsIds(user_id).size();

        // 计算用户相似度矩阵,选取目标用户对应行
        Map<Integer, Double> similarityRow = calculateUserSimilarityMatrix().get(user_id);

        // 依据余弦相似度进行排序
        List<Map.Entry<Integer, Double>> sortedSimilarities = new ArrayList<>(similarityRow.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        // 选择与目标用户兴趣最相似的K个用户,并获取其购物车商品id
        List<Integer> recommendGoodsIds = new ArrayList<>();

        for (int i = 0; i < Math.min(k, sortedSimilarities.size()); i++) {
            int similarUserId = sortedSimilarities.get(i).getKey();
            recommendGoodsIds.addAll(getCartGoodsIds(similarUserId));
        }

        return recommendGoodsIds;
    }

    /**
     * 获取用户订单商品id
     */
    public List<Integer> getUserOrderGoodsIds(int user_id) {

        List<OrderInfo> orderInfos = orderService.getUserOrder(user_id).getData();
        List<Integer> goodsIds = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfos) {
            goodsIds.addAll(orderInfo.getGoods_list().stream().map(OrderItemInfo::getGoods_id).toList());
        }
        return goodsIds;
    }

    /**
     * 计算用户相似度矩阵
     */
    public Map<Integer, Map<Integer, Double>> calculateUserSimilarityMatrix() {

        // 获取所有用户id
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        List<Integer> userIds = userInfos.stream().map(UserInfo::getUser_id).toList();

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
        System.out.println(similarityMatrix);
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
        for (Integer goodsId : goodsIdsA) {
            if (goodsIdsB.contains(goodsId))
                commonLikedGoods++;
        }

        // 计算用户A和用户B分别加入购物车的商品数量
        int likedPostsA = goodsIdsA.size();
        int likedPostsB = goodsIdsB.size();

        // 计算余弦相似度
        return commonLikedGoods / Math.sqrt(likedPostsA * likedPostsB);
    }

    /**
     * 获取用户购物车商品id
     */
    public List<Integer> getCartGoodsIds(int user_id) {
        LambdaQueryWrapper<CartInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartInfo::getUser_id, user_id);

        List<CartInfo> cartInfos = cartInfoMapper.selectList(queryWrapper);

        return cartInfos.stream().map(CartInfo::getGoods_id).toList();
    }

}
