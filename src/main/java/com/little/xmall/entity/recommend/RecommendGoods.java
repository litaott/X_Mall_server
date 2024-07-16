package com.little.xmall.entity.recommend;

import lombok.Data;

/**
 * 商品推荐要素实体类
 * @author Little
 */
@Data
public class RecommendGoods {

    private int goods_id;
    private String goods_name;
    private String category;
    private float price;
    private int sale_number;

}
