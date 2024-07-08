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
 * 用户地址实体类
 * @author Little
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("address_info")
public class AddressInfo implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer address_id;
    private int user_id;
    private String address;
    private String receiver;
    private String phone_number;
    private int is_default;
//    private enum label {
//        HOME("家"),SCHOOL("学校"),COMPANY("公司");
//        private String description;
//
//        label(String description) {
//            this.description = description;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//    }
}