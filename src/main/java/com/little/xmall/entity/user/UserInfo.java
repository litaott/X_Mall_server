package com.little.xmall.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.little.xmall.constant.OptionMap;
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
public class UserInfo implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;
    private String password;
    private String username;
    private Boolean is_login;
    private float balance;
    private String phone_number;
    private String avatar;
    private int gender_index;
    private int age;
    private int follow_number;
    private int role_index;
    private String create_time;
    @TableField(exist = false)
    private String gender;
    @TableField(exist = false)
    private String role;

    public void setGender(String gender) {
        this.gender = gender;
        this.gender_index = OptionMap.USER_GENDER.getInt(gender);
    }
    public void setGender_index(int gender_index){
        this.gender=OptionMap.USER_GENDER.getStr(gender_index);
        this.gender_index=gender_index;
    }
    public void setRole(String role) {
        this.role = gender;
        this.role_index = OptionMap.USER_ROLE.getInt(role);
    }
    public void setRole_index(int role_index){
        this.role=OptionMap.USER_ROLE.getStr(role_index);
        this.role_index=role_index;
    }


}
