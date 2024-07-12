package com.little.xmall.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Map转换工具类
 * @author Little
 */

public class MapUtil {

    /**
     * 实体类转换为Map
     * @param obj   实体类
     * @param <T>   泛型
     * @return map
     */
    public static <T> Map<String, Object> getMap (T obj){
        String jsonString = JSON.toJSONString(obj);
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {};
        return JSON.parseObject(jsonString, typeReference);
    }

    /**
     * 实体类列表转换为Map列表
     * @param list_obj  实体类列表
     * @param <T>       泛型
     * @return list_map
     */
    public static <T> List<Map<String, Object>> getMapList (List<T> list_obj){
        List<Map<String, Object>> list_map = new ArrayList<>();
        for (T obj: list_obj)
            list_map.add(getMap(obj));
        return list_map;
    }

}
