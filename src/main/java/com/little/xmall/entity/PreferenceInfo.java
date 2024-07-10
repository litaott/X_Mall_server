package com.little.xmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private int goods_id;
    private int pref_id;
    private String category;
    private String pref_name;
    private String start_time;
    private String end_time;
    @TableField(exist = false)
    private float reduction;
    @TableField(exist = false)
    private float discount;
    @TableField(exist = false)
    private String gift;
}
