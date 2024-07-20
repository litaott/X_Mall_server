package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.goods.CommentInfo;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.user.AddressInfo;
import com.little.xmall.service.GoodsService;
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
public class GoodsController {
    private final GoodsService goodsService;
    @PostMapping("/register_goods")
    public Response<Map<String, Object>> registerGoods(@RequestBody GoodsInfo goodsInfo) {
        return goodsService.registerGoods(goodsInfo);
    }
    @GetMapping("/get_goods")
    public Response<Map<String, Object>> getGoods(@RequestParam int goods_id) {
        return goodsService.getGoods(goods_id);
    }
    @PutMapping("/update_goods")
    public Response<Map<String, Object>> updateGoods(@RequestBody GoodsInfo goodsInfo) {
        return goodsService.updateGoods(goodsInfo);
    }
    @PutMapping("/update_goods_image")
    public Response<Map<String, Object>> updateGoodsImage(@RequestBody GoodsImageInfo goodsImageInfo) {
        return goodsService.updateGoodsImage(goodsImageInfo);
    }
    @PostMapping("/add_goods_image")
    public Response<Map<String,Object>> addGoodsImage(@RequestBody GoodsImageInfo goodsImageInfo){
        return goodsService.addGoodsImage(goodsImageInfo);
    }
    @DeleteMapping("/delete_goods")
    public Response<String> deleteGoods(@RequestParam Integer goods_id) {
        return goodsService.deleteGoods(goods_id);
    }
    @DeleteMapping("/delete_goods_images")
    public Response<String> deleteGoodsImage(@RequestParam Integer image_id) {
        return goodsService.deleteGoodsImage(image_id);
    }
    @PostMapping("/add_comment")
    public Response<Map<String, Object>> addComment(@RequestBody CommentInfo commentInfo) {
        return goodsService.addComment(commentInfo);
    }

    @GetMapping("/get_all_goods")
    Response<List<Map<String, Object>>>getAllGoods(){
        return goodsService.getAllGoods();
    }
    @GetMapping("/get_comment")
    public Response<Map<String, List<?>>> getComment(@RequestParam Integer goods_id) {
        return goodsService.getComment(goods_id);
    }
    @DeleteMapping("/delete_comment")
    public Response<String> deleteComment(@RequestParam Integer comment_id) {
        return goodsService.deleteComment(comment_id);
    }
}
