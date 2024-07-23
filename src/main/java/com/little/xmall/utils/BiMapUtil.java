package com.little.xmall.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiMapUtil {
    private final Map<Integer, String> forwardMap = new HashMap<>();
    private final Map<String, Integer> reverseMap = new HashMap<>();

    public BiMapUtil() {
    }

    public void put(Integer key, String value) {
        forwardMap.put(key, value);
        reverseMap.put(value, key);
    }

    public String getStr(Integer key) {
        return forwardMap.get(key);
    }

    public Integer getInt(String value) {
        return reverseMap.get(value);
    }
}
