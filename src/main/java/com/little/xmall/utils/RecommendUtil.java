package com.little.xmall.utils;

import com.little.xmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 推荐算法工具类
 *
 * @author Little
 */
@Component
@RequiredArgsConstructor
public class RecommendUtil {

    private final UserService userService;

    /**
     * 计算余弦相似度
     */
    public double calculateCosineSimilarity(int userIdA, int userIdB) {
//        List<Integer> postIdsA = getLikedPostsByUserId(userIdA);
//        List<Integer> postIdsB = getLikedPostsByUserId(userIdB);
//        // 计算用户A和用户B共同喜欢的帖子数量
//        int commonLikedPosts = countCommonLikedPosts(postIdsA, postIdsB);
//
//        // 计算用户A和用户B分别喜欢帖子的个数
//        int likedPostsA = postIdsA.size();
//        int likedPostsB = postIdsB.size();
//
//        // 计算余弦相似度
//        double similarity = commonLikedPosts / Math.sqrt(likedPostsA * likedPostsB);
//        return similarity;
        return 0;
    }




}
