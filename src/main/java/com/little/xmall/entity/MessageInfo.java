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
public class MessageInfo implements Serializable {//序列化、反序列化数据
    @TableId(value = "message_id", type = IdType.AUTO)//指定主键
    private Integer message_id;
    private int sender_id;
    private int receiver_id;
    private String message;
    private String time;
    private int is_read;
}