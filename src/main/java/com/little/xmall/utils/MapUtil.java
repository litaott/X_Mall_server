package com.little.xmall.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 * @author Little
 */

public class MapUtil {

    public static HashMap<String, Object> of (Map<String, Object> map) {
        return new HashMap<>(map);
    }

    public static HashMap<String, Object> of (String s1, Object o1) {
        return new HashMap<>(
                Map.of(
                        s1 , o1
                )
        );
    }

    public static HashMap<String, Object> of (String s1, Object o1, String s2, Object o2) {
        return new HashMap<>(
                Map.of(
                        s1 , o1,
                        s2 , o2
                )
        );
    }

}
