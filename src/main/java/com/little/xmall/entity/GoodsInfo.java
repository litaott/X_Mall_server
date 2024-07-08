package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * 商品实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods_info")
public class GoodsInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer goods_id;
    private int store_id;
    private int goods_name;
    private String images;
    private int sale_number;
    private String category;
    private String date_time;
    private String insurance;
    private String transportation;
    private float trans_price;
    private int del_flag;
}
