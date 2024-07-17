package com.little.xmall.entity.preference;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 降价信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("reduction_info")
public class ReductionInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    float reduction;
}
