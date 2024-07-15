package com.little.xmall.entity.store;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商店信息实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("store_info")
public class StoreInfo implements Serializable{
    @TableId(value = "store_id", type = IdType.AUTO)
    private Integer store_id;
    private String store_name;
    private String owner_name;
    private String password;
    private String phone_number;
    private String credit_id;
    private float reputation;
    private float revenue;
    private int fans_number;
    private String image;
    private String create_time;
}
