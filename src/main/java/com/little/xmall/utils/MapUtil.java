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

    public static HashMap<String, Object> of (String string, Object object) {
        return new HashMap<>(
                Map.of(
                        string , object
                )
        );
    }

}
