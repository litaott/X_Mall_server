package com.little.xmall.controller;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.MessageInfo;
import com.little.xmall.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 消息控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/communication")
public class MessageController {

    private final MessageService messageService;

    /**
     * 用户/商家发送消息
     * @param messageInfo   消息信息
     * @return Response
     */
    @PostMapping("/send_message")
    public Response sendMessage(@RequestBody MessageInfo messageInfo){
        return messageService.sendMessage(messageInfo);
    }

    /**
     * 获取用户与商家聊天记录
//     * @param user_id       用户id
//     * @param store_id      商家id
     * @return Response
     */
    @GetMapping("/get_message")
    public Response getMessage(@RequestBody Map<String,Object> requestBody){
        return messageService.getMessage((int)requestBody.get("user_id"), (int)requestBody.get("store_id"));
    }

}
