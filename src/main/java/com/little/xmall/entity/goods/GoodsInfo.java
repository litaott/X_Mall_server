package com.little.xmall.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.little.xmall.constant.OptionMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 商品实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods_info")
public class GoodsInfo implements Serializable {
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goods_id;
    private int store_id;
    private String goods_name;
    @TableField(exist =false)
    private List<String> images;
    private  float price;
    private int quantity;
    private int sale_number;
    private int category_index;
    private String create_time;
    private String insurance;
    private String transportation;
    private float trans_price;
    @TableField(exist = false)
    private String category;
    public void setCategory(String category) {
        this.category = category;
        this.category_index = OptionMap.GOODS_CATEGORY.getInt(category);
    }
    public void setCategory_index(int category_index){
        this.category=OptionMap.GOODS_CATEGORY.getStr(category_index);
        this.category_index=category_index;
    }
}
