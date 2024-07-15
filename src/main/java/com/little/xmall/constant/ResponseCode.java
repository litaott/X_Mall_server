package com.little.xmall.constant;

/**
 * 回复编码枚举类
 * @author Little
 */

public enum ResponseCode {
    /**
     * 一般回复
     */
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),

    /**
     * 用户相关
     */
    USER_REGISTER_SUCCESS(201,"注册成功"),
    USER_EXIST(401,"用户已存在"),

    USER_SIGN_IN_SUCCESS(202,"登录成功"),
    USER_PASSWORD_ERROR(402,"密码错误"),
    USER_NOT_EXIST(403,"用户不存在"),

    USER_FOLLOWED(206,"用户已关注"),
    USER_NOT_FOLLOW(408,"用户未关注"),
    /**
     * 商品相关
     */

    GOODS_REGISTER_SUCCESS(203,"注册成功"),
    GOODS_NOT_EXIST(404,"商品不存在"),
    /**
     *商铺相关
     */
    STORE_REGISTER_SUCCESS(204,"注册成功"),
    STORE_SIGN_IN_SUCCESS(205,"登录成功"),
    STORE_PASSWORD_ERROR(405,"密码错误"),
    STORE_NOT_EXIST(406,"店铺不存在"),
    STORE_HAS_EXIST(407,"商店已注册"),







    /**
     *订单相关
     */
    ORDER_GET_SUCCESS(251,"订单查询成功"),
    ORDER_CREATE_SUCCESS(258,"订单创建成功"),
    ORDER_PAY_SUCCESS(259,"订单支付成功"),
    ORDER_SEND_SUCCESS(260,"订单发货成功"),
    ORDER_RECEIVE_SUCCESS(261,"订单到货成功"),
    ORDER_FINISH_SUCCESS(262,"订单完成成功"),

    ORDER_NOT_EXIST(451,"订单不存在"),
    ORDER_NOT_PAY(452,"订单未支付"),
    ORDER_NOT_SEND(453,"订单未发货"),
    ORDER_NOT_RECEIVE(454,"订单未到货"),
    ORDER_ALREADY_PAY(455,"订单已支付"),
    ORDER_ALREADY_SEND(456,"订单已发货"),
    ORDER_ALREADY_RECEIVE(457,"订单已到货"),
    ORDER_ALREADY_FINISH(458,"订单已完成"),


    /**
     *聊天记录相关
     */
    MESSAGE_GET_SUCCESS(252,"聊天记录查询成功"),
    MESSAGE_SEND_SUCCESS(253,"消息发送成功"),

    /**
     *售后相关
     */
    AFTER_SALE_APPLY_SUCCESS(254,"售后申请成功"),
    AFTER_SALE_HANDLE_SUCCESS(255,"售后处理成功"),
    AFTER_SALE_USER_GET_SUCCESS(263,"用户售后订单查询成功"),
    AFTER_SALE_STORE_GET_SUCCESS(264,"商家售后订单查询成功"),

    AFTER_SALE_NOT_EXIST(459,"售后订单不存在"),
    AFTER_SALE_ALREADY_HANDLE(460,"售后订单已处理"),



    /**
     *优惠相关
     */
    PREFERENCE_GET_SUCCESS(256,"优惠查询成功"),
    PREFERENCE_ADD_SUCCESS(257,"优惠添加成功"),








    /**
     * 占位
     */
    THE_END(1000,"末尾占位");

    private final int code;
    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
