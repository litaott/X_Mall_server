package com.little.xmall.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.little.xmall.constant.OptionMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private int pay_way_index;
    private String receive_time;
    private String finish_time;
    private int address_id;
    private int status_index;

    @TableField(exist = false)
    private List<OrderItemInfo> goods_list;

    @TableField(exist = false)
    private String pay_way;

    @TableField(exist = false)
    private String status;

    public void setPay_way(String pay_way){
        this.pay_way = pay_way;
        this.pay_way_index = OptionMap.PAY_WAY.getInt(pay_way);
    }

    public void setPay_way_index(int pay_way_index){
        this.pay_way_index = pay_way_index;
        this.pay_way = OptionMap.PAY_WAY.getStr(pay_way_index);
    }

    public void setStatus(String status){
        this.status = status;
        this.status_index = OptionMap.ORDER_STATUS.getInt(status);
    }

    public void setStatus_index(int status_index){
        this.status_index = status_index;
        this.status = OptionMap.ORDER_STATUS.getStr(status_index);
    }
}

