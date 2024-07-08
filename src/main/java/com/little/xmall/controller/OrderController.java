package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 获取用户订单列表
     * @param user_id       用户id
     * @return Response
     */
    @GetMapping("/get_user_order")
    public Response<List<Map<String, Object>>> getUserOrder(int user_id){
        return orderService.getUserOrder(user_id);
    }
}
