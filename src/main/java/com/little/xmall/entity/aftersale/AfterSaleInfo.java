package com.little.xmall.entity.aftersale;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.little.xmall.constant.OptionMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 售后信息实体类
 *
 * @author Little
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("after_sale_info")
public class AfterSaleInfo implements Serializable {
    @TableId(value = "after_sale_id", type = IdType.AUTO)
    private Integer after_sale_id;
    private int user_id;
    private int store_id;
    private int order_id;
    private int goods_id;
    private int category_index;
    private String reason;
    private int result_index;
    private String start_time;
    private String finish_time;
    private Boolean is_finish;

    @TableField(exist = false)
    private String category;

    @TableField(exist = false)
    private String result;

    public void setCategory(String category) {
        this.category = category;
        this.category_index = OptionMap.AFTER_SALE_CATEGORY.getInt(category);
    }

    public void setCategory_index(int category_index) {
        this.category_index = category_index;
        this.category = OptionMap.AFTER_SALE_CATEGORY.getStr(category_index);
    }

    public void setResult(String result) {
        this.result = result;
        this.result_index = OptionMap.AFTER_SALE_RESULT.getInt(result);
    }

    public void setResult_index(int result_index) {
        this.result_index = result_index;
        this.result = OptionMap.AFTER_SALE_RESULT.getStr(result_index);
    }
}
