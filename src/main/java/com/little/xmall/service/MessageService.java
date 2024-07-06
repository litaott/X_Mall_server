package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.MessageInfo;

/**
 * 消息服务接口
 * @author Little
 */

public interface MessageService extends IService<MessageInfo> {

    /**
     * 用户/商家发送消息
     *
     * @param messageInfo 消息信息
     * @return Response
     */
    Response sendMessage(MessageInfo messageInfo);

    /**
     * 获取用户与商家聊天记录
     * @param user_id       用户id
     * @param store_id      商家id
     * @return Response
     */
    Response getMessage(int user_id, int store_id);


}
