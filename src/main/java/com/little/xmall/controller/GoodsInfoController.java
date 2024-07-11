package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.service.GoodsInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
    private final GoodsInfoService goodsInfoService;
    @PostMapping("/user/register_user")
    public Response<Map<String, Object>> registerGoods(@RequestBody GoodsInfo goodsInfo) {
        return goodsInfoService.registerGoods(goodsInfo);
    }
    @GetMapping("/goods/get_goods")
    public Response<List<Map<String, Object>>> getGoods(int goods_id) {
        return goodsInfoService.getGoods(goods_id);
    }
    @PostMapping("/goods/update")
    public Response<Map<String, Object>> updateGoods(@RequestBody GoodsInfo goodsInfo) {
        return goodsInfoService.updateGoods(goodsInfo);
    }
    @DeleteMapping("/goods/delete_goods")
    public Response<String> deleteGoods(@RequestParam Integer goods_id) {
        return goodsInfoService.deleteGoods(goods_id);
    }
}
