package com.little.xmall.utils;


import com.little.xmall.entity.search.SearchGoods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 搜索算法工具类
 *
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
    public static int[] fuzzyMatch(String query, String text) {

        // 完全匹配成功，距离为零
        if (text.contains(query) || query.contains(text))
            return new int[]{1, 0};

        int queryLength = query.length();
        int textLength = text.length();

        int min_length = Math.min(queryLength, textLength);

        int[][] distance = new int[queryLength + 1][textLength + 1];

        for (int i = 0; i <= queryLength; i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= textLength; j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= queryLength; i++) {
            for (int j = 1; j <= textLength; j++) {
                int cost = (query.charAt(i - 1) == text.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(distance[i - 1][j] + 1,             // deletion
                        Math.min(distance[i][j - 1] + 1,     // insertion
                                distance[i - 1][j - 1] + cost)); // substitution
            }
        }

        int d = distance[queryLength][textLength] - Math.abs(queryLength - textLength);

        // 模糊匹配成功，返回距离
        if (d < min_length)
            return new int[]{1, d};

        // 匹配失败
        return new int[]{0, 0};
    }

    public static List<List<SearchGoods>> search(List<SearchGoods> list, String keyword) {

        List<SearchGoods> listByGoods = new ArrayList<>();
        List<SearchGoods> listByStore = new ArrayList<>();

        int[] result;
        for (SearchGoods goods : list) {

            // 关键词与商品名匹配
            result = fuzzyMatch(keyword, goods.getGoods_name());
            if (result[0] == 1) {
                goods.setDistance(result[1]);
                listByGoods.add(goods);
                continue;
            }

            // 关键词与店铺名匹配
            result = fuzzyMatch(keyword, goods.getStore_name());
            if (result[0] == 1) {
                goods.setDistance(result[1]);
                listByStore.add(goods);
            }
        }

        // 按距离排序
        listByGoods.sort(Comparator.comparingInt(SearchGoods::getDistance));
        listByStore.sort(Comparator.comparingInt(SearchGoods::getDistance));

        System.out.println("列表：" + listByGoods);
        System.out.println("列表：" + listByStore);

        List<List<SearchGoods>> resultList = new ArrayList<>();
        resultList.add(listByGoods);
        resultList.add(listByStore);
        return resultList;
    }

}
