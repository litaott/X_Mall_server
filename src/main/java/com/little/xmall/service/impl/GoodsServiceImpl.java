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

import java.util.ArrayList;
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

    //注册商品
    @Override
    public Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo) {
            goodsInfoMapper.insert(goodsInfo);
        List<String> image_url = goodsInfo.getImages();
        if (image_url != null && !image_url.isEmpty()) {
            for (String imageUrl : image_url) {
                GoodsImageInfo goodsImageInfo = new GoodsImageInfo();
                goodsImageInfo.setGoods_id(goodsInfo.getGoods_id());
                goodsImageInfo.setImage_url(imageUrl);
                goodsImageInfoMapper.insert(goodsImageInfo);}

        }
            return Response.success(ResponseCode.GOODS_REGISTER_SUCCESS, Map.of("goods_id", goodsInfo.getGoods_id()));
    }

    //获取商品信息
    @Override
    public Response<Map<String, Object>> getGoods(Integer goods_id) {
        GoodsInfo goodsInfo=goodsInfoMapper.selectById(goods_id);
        QueryWrapper<GoodsImageInfo> goodsImageInfoQueryWrapper=new QueryWrapper<>();
        List<GoodsImageInfo> list=goodsImageInfoMapper.selectList(goodsImageInfoQueryWrapper.eq("goods_id",goods_id));
        List<String>goodsList=new ArrayList<>();
        for(GoodsImageInfo goodsImageInfo : list){
            goodsList.add(goodsImageInfo.getImage_url());
        }
        goodsInfo.setImages(goodsList);
        return Response.success(ResponseCode.SUCCESS,MapUtil.getMap(goodsInfo) );

    }

    //修改商品信息
    @Override
    public Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo) {
        int result = goodsInfoMapper.updateById(goodsInfo);
        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        }
        return null;
    }

    @Override
    public Response<List<Map<String, Object>>> getAllGoods() {
        List<GoodsInfo> list = goodsInfoMapper.selectList(null);
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }
    //添加商品图片
    @Override
    public Response<Map<String,Object>> addGoodsImage(GoodsImageInfo goodsImageInfo){
        goodsImageInfoMapper.insert(goodsImageInfo);
        return Response.success(ResponseCode.SUCCESS,Map.of("image_id",goodsImageInfo.getImage_id()));
    }
    //修改商品图片
    @Override
    public Response<Map<String, Object>> updateGoodsImage(GoodsImageInfo goodsImageInfo) {
        UpdateWrapper<GoodsImageInfo> updateWrapper = new UpdateWrapper<>();
        GoodsImageInfo imageInfo=goodsImageInfoMapper.selectById(goodsImageInfo.getImage_id());
        updateWrapper.eq("image_id", goodsImageInfo.getImage_id()).set("image_url",goodsImageInfo.getImage_url());
        goodsImageInfo.setGoods_id(imageInfo.getGoods_id());
        goodsImageInfoMapper.update(goodsImageInfo,updateWrapper);
        return Response.success(ResponseCode.SUCCESS, null);
    }

    //删除商品
    @Override
    public Response<String> deleteGoods(Integer goods_id) {
        goodsInfoMapper.deleteById(goods_id);
        QueryWrapper<GoodsImageInfo> queryWrapper = new QueryWrapper<>();
        goodsImageInfoMapper.delete(queryWrapper.eq("goods_id",goods_id));
        QueryWrapper<CommentInfo> queryWrapper1 = new QueryWrapper<>();
        commentInfoMapper.delete(queryWrapper1.eq("goods_id",goods_id));
        return Response.success(ResponseCode.SUCCESS,null);
    }

    //删除商品图片
    @Override
    public Response<String> deleteGoodsImage(Integer image_id) {
        QueryWrapper<GoodsImageInfo> queryWrapper = new QueryWrapper<>();
        goodsImageInfoMapper.delete(queryWrapper.eq("image_id",image_id));
        return Response.success(ResponseCode.SUCCESS, null);
    }

    //添加评论
    @Override
    public Response<Map<String, Object>> addComment(CommentInfo commentInfo) {
        commentInfoMapper.insert(commentInfo);
        return Response.success(ResponseCode.SUCCESS, Map.of("comment_id", commentInfo.getComment_id()));
    }

    //获取评论
    @Override
    public Response<Map<String, List<?>>> getComment(Integer goods_id) {
        Map<String, List<?>> result = new HashMap<>();
        QueryWrapper<CommentInfo> commentInfoQueryWrapper = new QueryWrapper<>();
        commentInfoQueryWrapper.eq("goods_id", goods_id);
        List<CommentInfo> list = commentInfoMapper.selectList(commentInfoQueryWrapper);
        result.put("commentInfo",list);
        return Response.success(ResponseCode.SUCCESS, result);
    }

    //删除评论
    @Override
    public Response<String> deleteComment(int comment_id){
        commentInfoMapper.deleteById(comment_id);
        return Response.success(ResponseCode.SUCCESS, null);
    }
    @Override
    public  void deleteStoreGoods(int store_id){
        QueryWrapper<GoodsInfo> goodsInfoQueryWrapper=new QueryWrapper<>();
        goodsInfoQueryWrapper.eq("store_id",store_id);
        goodsInfoMapper.delete(goodsInfoQueryWrapper);
        QueryWrapper<GoodsImageInfo> goodsImageInfoQueryWrapper=new QueryWrapper<>();
        goodsImageInfoQueryWrapper.eq("goods_id",goodsInfoMapper.selectOne(goodsInfoQueryWrapper));
        goodsImageInfoMapper.delete(goodsImageInfoQueryWrapper);
    }
}
