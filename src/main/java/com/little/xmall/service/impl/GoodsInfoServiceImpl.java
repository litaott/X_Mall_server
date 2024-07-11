package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.mapper.goods.GoodsInfoMapper;
import com.little.xmall.service.GoodsInfoService;
import com.little.xmall.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 商品信息Service实现类
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_goods_info")
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsInfoService {

    private final GoodsInfoMapper goodsInfoMapper;
    @Override
    public Response<Map<String, Object>> registerGoods(GoodsInfo goodsInfo) {
            goodsInfoMapper.insert(goodsInfo);
            return Response.success(ResponseCode.GOODS_REGISTER_SUCCESS, Map.of("goods_id", goodsInfo.getGoods_id()));
    }
    @Override
    public Response<List<Map<String, Object>>> getGoods(Integer goods_id) {
        List<GoodsInfo> list = goodsInfoMapper.selectByMap(Map.of("goods_id", goods_id));
        return Response.success(ResponseCode.SUCCESS, MapUtil.getMapList(list));
    }
    @Override
    public Response<Map<String, Object>> updateGoods(GoodsInfo goodsInfo) {
        int result = goodsInfoMapper.updateById(goodsInfo);

        if (result > 0) {
            return Response.success(ResponseCode.SUCCESS, null);
        }
        return null;
    }
    @Override
    public Response<String> deleteGoods(Integer goods_id) {
        goodsInfoMapper.deleteById(goods_id);;
        return Response.success(ResponseCode.SUCCESS, null);
    }
}
