package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.goods.CommentInfo;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.mapper.goods.GoodsInfoMapper;
import com.little.xmall.mapper.goods.GoodsImageInfoMapper;
import com.little.xmall.mapper.goods.CommentInfoMapper;
import com.little.xmall.service.GoodsService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 商品信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_goods")
public class GoodsServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsService {

    private final GoodsInfoMapper goodsInfoMapper;
    private final GoodsImageInfoMapper goodsImageInfoMapper;
    private final CommentInfoMapper commentInfoMapper;

    @Override
    public Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo) {//测试完成
            goodsInfoMapper.insert(goodsInfo);
        List<String> image_url = goodsInfo.getImages();
        if (image_url != null && !image_url.isEmpty()) {
            for (String imageUrl : image_url) {
                GoodsImageInfo goodsImageInfo = new GoodsImageInfo();
                goodsImageInfo.setGoods_id(goodsInfo.getGoods_id()); // 设置关联的商品ID
                goodsImageInfo.setImage_url(imageUrl);
                goodsImageInfoMapper.insert(goodsImageInfo);}

        }
            return Response.success(ResponseCode.GOODS_REGISTER_SUCCESS, Map.of("goods_id", goodsInfo.getGoods_id()));
    }
    @Override
    public Response<List<Map<String, Object>>> getGoods(Integer goods_id) {
        List<GoodsInfo> list = goodsInfoMapper.selectByMap(Map.of("goods_id", goods_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }
    @Override
    public Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo) {//测试完成
        int result = goodsInfoMapper.updateById(goodsInfo);
        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        }
        return null;
    }
    @Override
    public Response<Map<String, Object>> updateGoodsImage(GoodsImageInfo goodsImageInfo) {//测试完成
        UpdateWrapper<GoodsImageInfo> updateWrapper = new UpdateWrapper<>();
        GoodsImageInfo imageInfo=goodsImageInfoMapper.selectById(goodsImageInfo.getImage_id());
        updateWrapper.eq("image_id", goodsImageInfo.getImage_id()).set("image_url",goodsImageInfo.getImage_url());
        goodsImageInfo.setGoods_id(imageInfo.getGoods_id());
        goodsImageInfoMapper.update(goodsImageInfo,updateWrapper);
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public Response<String> deleteGoods(Integer goods_id) {//测试完成
        goodsInfoMapper.deleteById(goods_id);
        QueryWrapper<GoodsImageInfo> queryWrapper = new QueryWrapper<>();
        goodsImageInfoMapper.delete(queryWrapper.eq("goods_id",goods_id));
        QueryWrapper<CommentInfo> queryWrapper1 = new QueryWrapper<>();
        commentInfoMapper.delete(queryWrapper1.eq("goods_id",goods_id));
        return Response.success(ResponseCode.SUCCESS,null);
    }
    @Override
    public Response<String> deleteGoodsImage(Integer image_id) {//测试完成
        QueryWrapper<GoodsImageInfo> queryWrapper = new QueryWrapper<>();
        goodsImageInfoMapper.delete(queryWrapper.eq("image_id",image_id));
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public Response<Map<String, Object>> addComment(CommentInfo commentInfo) {//测试完成
        commentInfoMapper.insert(commentInfo);
        return Response.success(ResponseCode.SUCCESS, Map.of("comment_id", commentInfo.getComment_id()));
    }
    @Override
    public Response<Map<String, List<?>>> getComment(Integer goods_id) {//测试完成
        Map<String, List<?>> result = new HashMap<>();
        QueryWrapper<CommentInfo> commentInfoQueryWrapper = new QueryWrapper<>();
        commentInfoQueryWrapper.eq("goods_id", goods_id);
        List<CommentInfo> list = commentInfoMapper.selectList(commentInfoQueryWrapper);
        result.put("commentInfo",list);
        return Response.success(ResponseCode.SUCCESS, result);
    }
    @Override
    public Response<String> deleteComment(int comment_id){//测试完成
        commentInfoMapper.deleteById(comment_id);
        return Response.success(ResponseCode.SUCCESS, null);
    }
}
