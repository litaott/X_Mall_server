package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.OrderInfo;
import com.little.xmall.mapper.OrderInfoMapper;
import com.little.xmall.service.OrderService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 订单服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_order")
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderService {

    private final OrderInfoMapper orderInfoMapper;

    @Override
    public Response<Map<String, Object>> createOrder(OrderInfo orderInfo) {
        orderInfoMapper.insert(orderInfo);
        return Response.success(ResponseCode.SUCCESS, Map.of("order_id", orderInfo.getOrder_id()));
    }

    @Override
    public Response<List<Map<String, Object>>> getUserOrder(int user_id) {
        List<OrderInfo> list = orderInfoMapper.selectByMap(Map.of("user_id", user_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }

    @Override
    public Response<Map<String, Object>> payOrder(OrderInfo orderInfo) {
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        } else {
            String status = o.getStatus();
            switch (status) {
                case "未支付" -> {
                    o.setStatus("待发货");
                    o.setPay_time(orderInfo.getPay_time());
                    o.setPay_way(orderInfo.getPay_way());
                    orderInfoMapper.updateById(o);
                    return Response.success(ResponseCode.ORDER_PAY_SUCCESS, Map.of("order_id", o.getOrder_id()));
                }
                case "待发货", "运输中","已到货","已完成" -> {
                    return Response.error(ResponseCode.ORDER_ALREADY_PAY, null);
                }
                default -> {
                    return Response.error(ResponseCode.FAIL, null);
                }
            }
        }
    }

    @Override
    public Response<Map<String, Object>> sendOrder(OrderInfo orderInfo) {
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        } else {
            String status = o.getStatus();
            switch (status) {
                case "未支付" -> {
                    return Response.error(ResponseCode.ORDER_NOT_PAY, null);
                }
                case "待发货" -> {
                    o.setStatus("运输中");
                    o.setSend_time(orderInfo.getSend_time());
                    orderInfoMapper.updateById(o);
                    return Response.success(ResponseCode.ORDER_SEND_SUCCESS, Map.of("order_id", o.getOrder_id()));
                }
                case "运输中","已到货","已完成" -> {
                    return Response.error(ResponseCode.ORDER_ALREADY_SEND, null);
                }
                default -> {
                    return Response.error(ResponseCode.FAIL, null);
                }
            }
        }
    }

    @Override
    public Response<Map<String, Object>> receiveOrder(OrderInfo orderInfo) {
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        } else {
            String status = o.getStatus();
            switch (status) {
                case "未支付","待发货" -> {
                    return Response.error(ResponseCode.ORDER_NOT_SEND, null);
                }
                case "运输中" -> {
                    o.setStatus("已到货");
                    o.setReceive_time(orderInfo.getReceive_time());
                    orderInfoMapper.updateById(o);
                    return Response.success(ResponseCode.ORDER_RECEIVE_SUCCESS, Map.of("order_id", o.getOrder_id()));
                }
                case "已到货","已完成" -> {
                    return Response.error(ResponseCode.ORDER_ALREADY_RECEIVE, null);
                }
                default -> {
                    return Response.error(ResponseCode.FAIL, null);
                }
            }
        }
    }

    @Override
    public Response<Map<String, Object>> finishOrder(OrderInfo orderInfo) {
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        } else {
            String status = o.getStatus();
            switch (status) {
                case "未支付","待发货","运输中" -> {
                    return Response.error(ResponseCode.ORDER_NOT_RECEIVE, null);
                }
                case "已到货" -> {
                    o.setStatus("已完成");
                    o.setFinish_time(orderInfo.getFinish_time());
                    orderInfoMapper.updateById(o);
                    return Response.success(ResponseCode.ORDER_FINISH_SUCCESS, Map.of("order_id", o.getOrder_id()));
                }
                case "已完成" -> {
                    return Response.error(ResponseCode.ORDER_ALREADY_FINISH, null);
                }
                default -> {
                    return Response.error(ResponseCode.FAIL, null);
                }
            }
        }
    }
}
