package com.little.xmall.entity.preference;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 赠品信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("gift_info")
public class GiftInfo implements Serializable {
    @TableId(value = "gift_id", type = IdType.AUTO)
    Integer gift_id;
    String gift;
}
