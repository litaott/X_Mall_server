package com.little.xmall.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.little.xmall.constant.OptionMap;
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
    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer address_id;
    private int user_id;
    private String address;
    private String receiver;
    private String phone_number;
    private Boolean is_default;
    private int label_index;
    @TableField(exist = false)
    private String label;
    public void setLabel(String label) {
        this.label = label;
        this.label_index = OptionMap.ADDRESS_LABEL.getInt(label);
    }
    public void setLabel_index(int label_index){
        this.label=OptionMap.ADDRESS_LABEL.getStr(label_index);
        this.label_index=label_index;
    }

}