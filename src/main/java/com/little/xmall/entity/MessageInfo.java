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
 * 消息实体类
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("communication_info")
public class MessageInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private int sender_id;
    private int receiver_id;
    private String message;
    private String time;
    private int is_read;
}
