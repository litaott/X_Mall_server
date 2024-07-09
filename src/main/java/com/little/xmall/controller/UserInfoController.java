package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.UserInfo;
import com.little.xmall.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * 用户控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 用户信息
     *
     * @param
     * @return Response
     */
    @PostMapping("/user")
    public Response<Map<String, Object>> apply(@RequestBody UserInfo userInfo) {
        return userInfoService.apply(userInfo);
    }
}
