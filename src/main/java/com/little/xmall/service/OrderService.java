package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.MessageInfo;
import com.little.xmall.entity.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * 订单服务接口
 * @author Little
 */

public interface OrderService extends IService<OrderInfo> {

//    /**
//     * 用户创建订单
//     * @param messageInfo 消息信息
//     * @return Response
//     */
//    Response<Map<String,Object>> sendMessage(MessageInfo messageInfo);

    /**
     * 获取用户订单
     * @param user_id 用户id
     * @return Response
     */
    Response<List<Map<String,Object>>> getUserOrder(int user_id);

}
