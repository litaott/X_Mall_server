package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.order.OrderInfo;
import com.little.xmall.entity.order.OrderItemInfo;
import com.little.xmall.mapper.order.OrderInfoMapper;
import com.little.xmall.mapper.order.OrderItemInfoMapper;
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
    private final OrderItemInfoMapper orderItemInfoMapper;

    @Override
    public Response<Map<String, Object>> createOrder(OrderInfo orderInfo) {

        // 参数为空
        if (orderInfo == null)
            return Response.error(ResponseCode.FAIL, null);

        // 插入订单信息
        orderInfoMapper.insert(orderInfo);

        // 插入订单商品信息
        List<OrderItemInfo> goods_list = orderInfo.getGoods_list();
        for (OrderItemInfo itemInfo : goods_list) {
            itemInfo.setOrder_id(orderInfo.getOrder_id());
            orderItemInfoMapper.insert(itemInfo);
        }

        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());
        System.out.println(o.getTotal_price());
        System.out.println(o.getTrans_price());

        return Response.success(ResponseCode.ORDER_CREATE_SUCCESS, Map.of("order_id", orderInfo.getOrder_id()));
    }

    @Override
    public Response<List<Map<String, Object>>> getUserOrder(int user_id) {

        LambdaQueryWrapper<OrderInfo> order_queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<OrderItemInfo> item_queryWrapper = new LambdaQueryWrapper<>();

        // 获取订单信息列表
        order_queryWrapper.eq(OrderInfo::getUser_id, user_id);
        List<OrderInfo> list = orderInfoMapper.selectList(order_queryWrapper);

        // 订单列表为空
        if (list.isEmpty())
            return Response.error(ResponseCode.ORDER_GET_SUCCESS, null);

        // 添加订单商品信息
        for (OrderInfo orderInfo : list) {
            item_queryWrapper.eq(OrderItemInfo::getOrder_id, orderInfo.getOrder_id());
            orderInfo.setGoods_list(orderItemInfoMapper.selectList(item_queryWrapper));
        }

        return Response.success(ResponseCode.ORDER_GET_SUCCESS, MapUtil.getMapList(list));
    }

    @Override
    public Response<Map<String, Object>> payOrder(OrderInfo orderInfo) {

        // 获取订单信息
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());

        // 订单不存在
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        }

        // 订单状态判断
        int status_index = o.getStatus_index();
        switch (status_index) {
            // 订单支付成功
            case 0 -> {
                o.setStatus_index(1);
                o.setPay_time(orderInfo.getPay_time());
                o.setPay_way(orderInfo.getPay_way());
                orderInfoMapper.updateById(o);
                return Response.success(ResponseCode.ORDER_PAY_SUCCESS, Map.of("order_id", o.getOrder_id()));
            }
            // 订单已支付（失败）
            case 1,2,3,4 -> {
                return Response.error(ResponseCode.ORDER_ALREADY_PAY, null);
            }
            default -> {
                return Response.error(ResponseCode.FAIL, null);
            }
        }
    }

    @Override
    public Response<Map<String, Object>> sendOrder(OrderInfo orderInfo) {

        // 获取订单信息
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());

        // 订单不存在
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        }

        // 订单状态判断
        int status_index = o.getStatus_index();
        switch (status_index) {
            // 订单未支付（失败）
            case 0 -> {
                return Response.error(ResponseCode.ORDER_NOT_PAY, null);
            }
            // 订单发货成功
            case 1 -> {
                o.setStatus_index(2);
                o.setSend_time(orderInfo.getSend_time());
                orderInfoMapper.updateById(o);
                return Response.success(ResponseCode.ORDER_SEND_SUCCESS, Map.of("order_id", o.getOrder_id()));
            }
            // 订单已发货（失败）
            case 2, 3, 4 -> {
                return Response.error(ResponseCode.ORDER_ALREADY_SEND, null);
            }
            default -> {
                return Response.error(ResponseCode.FAIL, null);
            }
        }
    }

    @Override
    public Response<Map<String, Object>> receiveOrder(OrderInfo orderInfo) {

        // 获取订单信息
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());

        // 订单不存在
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        }

        // 订单状态判断
        int status_index = o.getStatus_index();
        switch (status_index) {
            // 订单未发货（失败）
            case 0, 1 -> {
                return Response.error(ResponseCode.ORDER_NOT_SEND, null);
            }
            // 订单到货成功
            case 2 -> {
                o.setStatus_index(3);
                o.setReceive_time(orderInfo.getReceive_time());
                orderInfoMapper.updateById(o);
                return Response.success(ResponseCode.ORDER_RECEIVE_SUCCESS, Map.of("order_id", o.getOrder_id()));
            }
            // 订单已到货（失败）
            case 3, 4 -> {
                return Response.error(ResponseCode.ORDER_ALREADY_RECEIVE, null);
            }
            default -> {
                return Response.error(ResponseCode.FAIL, null);
            }
        }
    }

    @Override
    public Response<Map<String, Object>> finishOrder(OrderInfo orderInfo) {

        // 获取订单信息
        OrderInfo o = orderInfoMapper.selectById(orderInfo.getOrder_id());

        // 订单不存在
        if (o == null) {
            return Response.error(ResponseCode.ORDER_NOT_EXIST, null);
        }

        // 订单状态判断
        int status_index = o.getStatus_index();
        switch (status_index) {
            // 订单未到货（失败）
            case 0, 1, 2 -> {
                return Response.error(ResponseCode.ORDER_NOT_RECEIVE, null);
            }
            // 订单完成成功
            case 3 -> {
                o.setStatus_index(4);
                o.setFinish_time(orderInfo.getFinish_time());
                orderInfoMapper.updateById(o);
                return Response.success(ResponseCode.ORDER_FINISH_SUCCESS, Map.of("order_id", o.getOrder_id()));
            }
            // 订单已完成（失败）
            case 4 -> {
                return Response.error(ResponseCode.ORDER_ALREADY_FINISH, null);
            }
            default -> {
                return Response.error(ResponseCode.FAIL, null);
            }
        }
    }
}
