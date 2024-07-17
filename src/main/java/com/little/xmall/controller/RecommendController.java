package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 推荐控制器类
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    /**
     * 获取用户推荐商品信息
     *
     * @param user_id 用户id
     * @return Response
     */
    @GetMapping("recommend_by_user_id")
    public Response<List<Map<String, Object>>> recommendByUserId(int user_id){
        return recommendService.recommendByUserId(user_id);
    }

}
