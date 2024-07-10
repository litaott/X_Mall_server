package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.order.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * 订单服务接口
 *
 * @author Little
 */

public interface OrderService extends IService<OrderInfo> {

    /**
     * 用户创建订单
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    Response<Map<String, Object>> createOrder(OrderInfo orderInfo);

    /**
     * 获取用户订单
     *
     * @param user_id 用户id
     * @return Response
     */
    Response<List<Map<String, Object>>> getUserOrder(int user_id);

    /**
     * 用户支付订单
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    Response<Map<String, Object>> payOrder(OrderInfo orderInfo);

    /**
     * 订单发货
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    Response<Map<String, Object>> sendOrder(OrderInfo orderInfo);

    /**
     * 订单到货
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    Response<Map<String, Object>> receiveOrder(OrderInfo orderInfo);

    /**
     * 订单完成
     *
     * @param orderInfo 订单信息
     * @return Response
     */
    Response<Map<String, Object>> finishOrder(OrderInfo orderInfo);

}
