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













    /**
     * 占位
     */
    THE_END(1000,"末尾占位");

    private int code;
    private String msg;

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
