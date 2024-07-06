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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 消息服务实现类
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
//        messageInfoMapper.insert(messageInfo);
//        int id = messageInfo.getId();
        return Response.success(ResponseCode.MESSAGE_SEND_SUCCESS, MapUtil.of("message_id", 1));
    }

    @Override
    public Response getMessage(int user_id, int store_id) {
        return null;
    }
}
