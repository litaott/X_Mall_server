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
 * 购物车实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cart_info")
public class CartInfo implements Serializable {
    @TableId(value = "user_id")
    private Integer user_id;
    @TableId(value = "goods_id")
    private Integer goods_id;
    private int quantity;
}