package com.little.xmall.utils;

import com.little.xmall.service.RecommendService;
import com.little.xmall.service.UserService;
import com.little.xmall.service.impl.RecommendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 推荐算法工具类
 *
 * @author Little
 */
@Component
public class RecommendUtil {

    /**
     * 根据用户相似度矩阵推荐商品给目标用户
     */
    public static List<Integer> recommendGoodsToUser(int targetUserId, int k, Map<Integer, Map<Integer, Double>> userSimilarityMatrix) {

        // 计算用户相似度矩阵,选取目标用户对应行
        Map<Integer, Double> similarityRow = userSimilarityMatrix.get(targetUserId);

        // 依据余弦相似度进行排序
        List<Map.Entry<Integer, Double>> sortedSimilarities = new ArrayList<>(similarityRow.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        // 选择与目标用户兴趣最相似的K个用户
        List<Integer> similarUserIds = new ArrayList<>();
        for (int i = 0; i < Math.min(k, sortedSimilarities.size()); i++) {
            int similarUserId = sortedSimilarities.get(i).getKey();
            similarUserIds.add(similarUserId);
        }

        return similarUserIds;
    }

}
