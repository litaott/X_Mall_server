package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.message.MessageInfo;
import com.little.xmall.mapper.message.MessageInfoMapper;
import com.little.xmall.service.MessageService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 消息服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_communication")
public class MessageServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements MessageService {

    private final MessageInfoMapper messageInfoMapper;

    @Override
    public Response<Map<String,Object>> sendMessage(MessageInfo messageInfo) {

        // 参数为空
        if (messageInfo == null)
            return Response.error(ResponseCode.FAIL, null);

        // 插入消息
        messageInfoMapper.insert(messageInfo);

        int id = messageInfo.getMessage_id();
        return Response.success(ResponseCode.MESSAGE_SEND_SUCCESS, Map.of("message_id", id));
    }

    @Override
    public Response<List<Map<String,Object>>> getMessage(int user_id, int store_id) {

        LambdaQueryWrapper<MessageInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 查询用户发送的消息
        queryWrapper.eq(MessageInfo::getSender_id, user_id)
                .eq(MessageInfo::getReceiver_id, store_id);
        List<Map<String, Object>> messages = messageInfoMapper.selectMaps(queryWrapper);

        queryWrapper.clear();

        // 查询店铺发送的消息
        queryWrapper.eq(MessageInfo::getSender_id, store_id)
                .eq(MessageInfo::getReceiver_id, user_id);
        List<Map<String, Object>> messages2 = messageInfoMapper.selectMaps(queryWrapper);

        messages.addAll(messages2);
        return Response.success(ResponseCode.MESSAGE_GET_SUCCESS, messages);
    }
}
