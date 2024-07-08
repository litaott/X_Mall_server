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
 * 商品实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("comment_info")

    public class CommentInfo implements Serializable {
        @TableId(value = "comment_id",type = IdType.AUTO)
        private Integer comment_id;
        private int goods_id;
        private int sender_id;
        private String message;
        private String date_name;
}
