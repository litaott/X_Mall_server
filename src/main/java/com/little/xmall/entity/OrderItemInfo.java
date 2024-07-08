package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单详情实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_item_info")
public class OrderItemInfo implements Serializable {
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Integer order_item_id;
    private int order_id;
    private int goods_id;
    private float price;
    private int quantity;
}
