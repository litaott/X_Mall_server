package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 订单实体类
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_info")
public class OrderInfo implements Serializable {
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer order_id;
    private int user_id;
    private int store_id;
    private float total_price;
    private float trans_price;
    private String pay_time;
    private String send_time;
    private String pay_way;
//    private enum pay_way{
//        WECHAT("微信"),
//        ALIPAY("支付宝");
//
//        private final String displayName;
//
//        pay_way(String displayName) {
//            this.displayName = displayName;
//        }
//
//        public String getDisplayName() {
//            return displayName;
//        }
//    };
    private String receive_time;
    private String finish_time;
    private int address_id;
    private String status;
    @TableField(exist = false)
    private List<OrderItemInfo> goods_list;
}

