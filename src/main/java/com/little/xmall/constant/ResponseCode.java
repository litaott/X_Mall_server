package com.little.xmall.constant;

/**
 * 回复编码枚举类
 * @author little
 * @date 2024.7.2
 */

public enum ResponseCode {
    /**
     * 一般回复
     */
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),

    /**
     * 注册相关
     */
    USER_REGISTER_SUCCESS(201,"注册成功"),
    USER_EXIST(401,"用户已存在"),












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
