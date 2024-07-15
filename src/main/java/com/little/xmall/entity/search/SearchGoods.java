package com.little.xmall.entity.search;

import lombok.Data;


/**
 * 商品搜索要素实体类
 * @author Little
 */
@Data
public class SearchGoods {

    private int goods_id;
    private String goods_name;
    private String store_name;
    private String category;
    private float price;
    private int sale_number;
    public int score;
}
