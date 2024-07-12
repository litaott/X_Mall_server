package com.little.xmall.entity.preference;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 打折信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("discount_info")
public class DiscountInfo implements Serializable {
    @TableId(value = "discount_id", type = IdType.AUTO)
    Integer discount_id;
    float discount;
}