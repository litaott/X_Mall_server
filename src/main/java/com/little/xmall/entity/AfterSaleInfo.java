package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 售后信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("after_sale_info")
public class AfterSaleInfo implements Serializable {
    @TableId(value = "after_sale_id", type = IdType.AUTO)
    private Integer after_sale_id;
    private int user_id;
    private int store_id;
    private int order_id;
    private int goods_id;
    private String category;
    private String reason;
    private String result;
    private String start_time;
    private String finish_time;
    private String is_finished;
}
