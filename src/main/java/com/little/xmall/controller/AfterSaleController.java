package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.AfterSaleInfo;
import com.little.xmall.service.AfterSaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 *
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/after_sale")
public class AfterSaleController {

    private final AfterSaleService afterSaleService;

    /**
     * 用户申请售后
     *
     * @param afterSaleInfo 消息信息
     * @return Response
     */
    @PostMapping("/apply")
    public Response<Map<String, Object>> apply(@RequestBody AfterSaleInfo afterSaleInfo) {
        return afterSaleService.apply(afterSaleInfo);
    }

    /**
     * 店铺处理售后
     *
     * @param afterSaleInfo 售后信息
     * @return Response
     */
    @PutMapping("/handle")
    Response<Map<String, Object>> handle(@RequestBody AfterSaleInfo afterSaleInfo) {
        return afterSaleService.handle(afterSaleInfo);
    }

    /**
     * 获取用户售后订单列表
     *
     * @param user_id 用户id
     * @return Response
     */
    @GetMapping("/get_user_after_sale")
    Response<List<Map<String, Object>>> get_user_after_sale(int user_id) {
        return afterSaleService.get_user_after_sale(user_id);
    }

    /**
     * 获取店铺售后订单列表
     *
     * @param store_id 店铺id
     * @return Response
     */
    @GetMapping("/get_store_after_sale")
    Response<List<Map<String, Object>>> get_store_after_sale(int store_id) {
        return afterSaleService.get_store_after_sale(store_id);
    }

}
