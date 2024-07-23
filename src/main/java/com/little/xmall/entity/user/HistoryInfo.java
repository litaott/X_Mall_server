package com.little.xmall.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.little.xmall.constant.OptionMap;
import com.little.xmall.entity.goods.GoodsInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;
/**
 * 历史记录实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("history_info")
public class HistoryInfo {
    @TableId(value = "user_id", type = IdType.AUTO)
    Integer user_id;
    Integer goods_id;
    String time;
    Boolean is_delete;
    @TableField(exist = false)
    GoodsInfo goodsInfo;
}
