package com.little.xmall.constant;

import lombok.Data;

/**
 * 回复通用类
 * @author Little
 */

@Data
public class Response<T> {

    private int code;
    private String message;
    private T data;

    public static <T> Response<T> success(ResponseCode responseCode, T data) {
        Response<T> r = new Response<>();
        r.setCode(responseCode.getCode());
        r.setMessage(responseCode.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> Response<T> error(ResponseCode responseCode, T data) {
        Response<T> r = new Response<>();
        r.setCode(responseCode.getCode());
        r.setMessage(responseCode.getMsg());
        r.setData(data);
        return r;
    }

}
