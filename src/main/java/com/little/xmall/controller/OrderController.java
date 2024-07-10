package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.order.OrderInfo;
import com.little.xmall.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 用户创建订单
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    @PostMapping("/create_order")
    public Response<Map<String, Object>> createOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.createOrder(orderInfo);
    }

    /**
     * 获取用户订单列表
     *
     * @param user_id 用户id
     * @return Response
     */
    @GetMapping("/get_user_order")
    public Response<List<Map<String, Object>>> getUserOrder(int user_id) {
        return orderService.getUserOrder(user_id);
    }

    /**
     * 用户支付订单
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    @PutMapping("/pay_order")
    public Response<Map<String, Object>> payOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.payOrder(orderInfo);
    }

    /**
     * 订单发货
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    @PutMapping("/send_order")
    public Response<Map<String, Object>> sendOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.sendOrder(orderInfo);
    }

    /**
     * 订单到货
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    @PutMapping("/receive_order")
    public Response<Map<String, Object>> receiveOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.receiveOrder(orderInfo);
    }

    /**
     * 订单完成
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    @PutMapping("/finish_order")
    public Response<Map<String, Object>> finishOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.finishOrder(orderInfo);
    }


}
