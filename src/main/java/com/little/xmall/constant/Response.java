package com.little.xmall.constant;

import lombok.Data;

/**
 * 回复通用类
 * @author little
 * @date  2024.7.2
 */

@Data
public class Response {

    private int code;
    private String message;
    private Object data;

    public static Response success(ResponseCode responseCode, Object data) {
        Response r = new Response();
        r.setCode(responseCode.getCode());
        r.setMessage(responseCode.getMsg());
        r.setData(data);
        return r;
    }

    public static Response error(ResponseCode resultCode, Object data) {
        Response r = new Response();
        r.setCode(resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }

}
