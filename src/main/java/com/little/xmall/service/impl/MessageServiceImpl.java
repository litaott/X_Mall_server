package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.MessageInfo;
import com.little.xmall.mapper.MessageInfoMapper;
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
    public Response sendMessage(MessageInfo messageInfo) {
        messageInfoMapper.insert(messageInfo);
        int id = messageInfo.getId();
        return Response.success(ResponseCode.MESSAGE_SEND_SUCCESS, MapUtil.of("message_id", id));
    }

    @Override
    public Response getMessage(int user_id, int store_id) {
        List<MessageInfo> messages = messageInfoMapper.selectByMap(
                MapUtil.of("sender_id", user_id, "receiver_id", store_id)
        );
        messages.addAll(
                messageInfoMapper.selectByMap(
                        MapUtil.of("sender_id", store_id, "receiver_id", user_id)
                )
        );
        return Response.success(ResponseCode.MESSAGE_GET_SUCCESS, messages);
    }
}
