package com.little.xmall.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;
/**
 * 用户信息实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserInfo implements Serializable{
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;
    private String password;
    private String username;
    private int is_login;
    private float balance;
    private String phone_number;
    private String avatar;
    private int gender;
    private int age;
    private int follow_number;
//    private enum role {
//        USER("用户"), ADMINISTRATOR("管理员");
//        private String description;
//
//        role(String description) {
//            this.description = description;
//        }
//    }
    private String create_time;
    private int del_flag;


}
