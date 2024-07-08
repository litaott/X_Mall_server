package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 优惠信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("preference_info")
public class PreferenceInfo implements Serializable {
    @TableId(value = "preference_id", type = IdType.AUTO)
    private Integer preference_id;
    private String category;
    private int user_id;
    private int store_id;
    private int order_id;
    private int goods_id;
    private String reason;
    private String result;
    private String start_time;
    private String finish_time;
    private int is_finished;
}
