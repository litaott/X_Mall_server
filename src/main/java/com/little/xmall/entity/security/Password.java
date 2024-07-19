package com.little.xmall.entity.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.little.xmall.constant.OptionMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password implements Serializable{
    private Integer user_id;
    private String old_password;
    private String new_password;
}
