package com.little.xmall.utils;


import com.little.xmall.entity.search.SearchGoods;

import java.util.List;

/**
 * 搜索算法工具类
 * @author Little
 */
public class SearchUtil {

    /**
     * 处理用户输入关键词
     */
    public static String handleKeyword(String keyword) {
        return keyword;
    }

    /**
     * 字符串匹配距离
     */
    public static boolean fuzzyMatch(String query, String text, int maxDistance) {
        int[][] distance = new int[query.length() + 1][text.length() + 1];

        for (int i = 0; i <= query.length(); i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= text.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= query.length(); i++) {
            for (int j = 1; j <= text.length(); j++) {
                int cost = (query.charAt(i - 1) == text.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(distance[i - 1][j] + 1,             // deletion
                        Math.min(distance[i][j - 1] + 1,     // insertion
                                distance[i - 1][j - 1] + cost)); // substitution
            }
        }

        return distance[query.length()][text.length()] <= maxDistance;
    }

    public static List<Integer> search(List<SearchGoods> list, String keyword) {

        keyword = handleKeyword(keyword);
        for (SearchGoods goods : list) {

        }

        return null;
    }

}
