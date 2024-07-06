package com.little.xmall.constant;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 回复通用类
 * @author Little
 */

@Data
public class Response {

    private int code;
    private String message;
    private HashMap<String,Object> data;

    public static Response success(ResponseCode responseCode, HashMap<String,Object> data) {
        Response r = new Response();
        r.setCode(responseCode.getCode());
        r.setMessage(responseCode.getMsg());
        r.setData(data);
        return r;
    }

    public static  Response error(ResponseCode resultCode, HashMap<String,Object> data) {
        Response r = new Response();
        r.setCode(resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }

}
