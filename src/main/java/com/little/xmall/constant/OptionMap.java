package com.little.xmall.constant;


import com.little.xmall.utils.BiMapUtil;

/**
 * 常量映射表类
 * @author Little
 */

public class OptionMap {
    public static final BiMapUtil
            USER_GENDER,        // 用户性别，0:未知 1:男 2:女
            USER_ROLE,          // 用户角色，0:普通用户 1:管理员
            ADDRESS_LABEL,      // 地址标签，0:家 1:学校 2:公司
            GOODS_CATEGORY,     // 商品类别，0:数码产品 1:家用电器
            PAY_WAY,            // 支付方式，0:支付宝 1:微信 2:余额
            ORDER_STATUS,       // 订单状态，0:待付款 1:待发货 2:待收货 3:待完成 4:已完成
            AFTER_SALE_CATEGORY,// 售后类型，0:退货退款 1:换货 2:仅退款
            AFTER_SALE_RESULT,  // 售后处理结果，0:同意申请 1:拒绝申请
            PREFERENCE_TYPE;    // 优惠类型，0:降价 1:折扣 2:赠品

    static {

        //用户性别
        USER_GENDER = new BiMapUtil();
        USER_GENDER.put(0, "未知");
        USER_GENDER.put(1, "男");
        USER_GENDER.put(2, "女");

        //用户角色
        USER_ROLE = new BiMapUtil();
        USER_ROLE.put(0, "普通用户");
        USER_ROLE.put(1, "管理员");

        //地址标签
        ADDRESS_LABEL = new BiMapUtil();
        ADDRESS_LABEL.put(0, "家");
        ADDRESS_LABEL.put(1, "学校");
        ADDRESS_LABEL.put(2, "公司");

        //商品分类
        GOODS_CATEGORY = new BiMapUtil();
        GOODS_CATEGORY.put(0, "数码产品");
        GOODS_CATEGORY.put(1, "家用电器");

        //支付方式
        PAY_WAY = new BiMapUtil();
        PAY_WAY.put(0, "支付宝");
        PAY_WAY.put(1, "微信");
        PAY_WAY.put(2, "余额");

        //订单状态
        ORDER_STATUS = new BiMapUtil();
        ORDER_STATUS.put(0, "待付款");
        ORDER_STATUS.put(1, "待发货");
        ORDER_STATUS.put(2, "待收货");
        ORDER_STATUS.put(3, "待完成");
        ORDER_STATUS.put(4, "已完成");

        //售后类型
        AFTER_SALE_CATEGORY = new BiMapUtil();
        AFTER_SALE_CATEGORY.put(0, "退货退款");
        AFTER_SALE_CATEGORY.put(1, "换货");
        AFTER_SALE_CATEGORY.put(2, "仅退款");

        //售后处理结果
        AFTER_SALE_RESULT = new BiMapUtil();
        AFTER_SALE_RESULT.put(0, "同意申请");
        AFTER_SALE_RESULT.put(1, "拒绝申请");

        //优惠类型
        PREFERENCE_TYPE = new BiMapUtil();
        PREFERENCE_TYPE.put(0, "降价");
        PREFERENCE_TYPE.put(1, "折扣");
        PREFERENCE_TYPE.put(2, "赠品");
    }

}
