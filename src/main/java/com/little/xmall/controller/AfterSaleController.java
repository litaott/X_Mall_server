package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.AfterSaleInfo;
import com.little.xmall.service.AfterSaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单控制器
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
     * @param afterSaleInfo   消息信息
     * @return Response
     */
    @PostMapping("/apply")
    public Response<Map<String, Object>> apply(@RequestBody AfterSaleInfo afterSaleInfo){
        return afterSaleService.apply(afterSaleInfo);
    }

}
