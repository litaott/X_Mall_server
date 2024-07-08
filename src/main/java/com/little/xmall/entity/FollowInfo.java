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
 * 关注实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("follow_info")
public class FollowInfo implements Serializable {
    @TableId(value = "user_id")
    private Integer user_id;
    @TableId(value = "store_id")
    private Integer store_id;
    private String goods_name;
    private float price;
    private int quantity;

}
