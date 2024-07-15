package com.little.xmall.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品图片实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods_image_info")
public class GoodsImageInfo {
    @TableId(value = "image_id", type = IdType.AUTO)
    private Integer image_id;
    private int goods_id;
    private String image_url;
}
