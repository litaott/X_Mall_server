package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 优惠控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/preference")
public class PreferenceController {

    private final PreferenceService preferenceService;

    /**
     * 获取商品优惠信息
     * @param goods_id 商品id
     * @return Response
     */
    @GetMapping("/get_preference_info")
    Response<List<Map<String,Object>>> get_preference_info(int goods_id){
        return preferenceService.get_preference_info(goods_id);
    }

}
