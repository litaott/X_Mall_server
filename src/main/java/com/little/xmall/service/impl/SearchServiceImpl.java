package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.goods.GoodsImageInfo;
import com.little.xmall.entity.goods.GoodsInfo;
import com.little.xmall.entity.search.SearchGoods;
import com.little.xmall.mapper.goods.GoodsImageInfoMapper;
import com.little.xmall.mapper.goods.GoodsInfoMapper;
import com.little.xmall.mapper.store.StoreInfoMapper;
import com.little.xmall.service.SearchService;
import com.little.xmall.service.StoreService;
import com.little.xmall.utils.MapUtil;
import com.little.xmall.utils.SearchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_goods")
public class SearchServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements SearchService {

    private final GoodsInfoMapper goodsInfoMapper;
    private final GoodsImageInfoMapper goodsImageInfoMapper;
    private final StoreService storeService;

    @Override
    public Response<List<List<Map<String, Object>>>> searchByKeyword(String keyword) {

        // 获取所有商品信息
        List<SearchGoods> searchGoodsList = new ArrayList<>();
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectList(null);

        // 提取搜索要素
        for (GoodsInfo goodsInfo : goodsInfoList) {
            SearchGoods searchGoods = new SearchGoods();
            searchGoods.setGoods_id(goodsInfo.getGoods_id());
            searchGoods.setGoods_name(goodsInfo.getGoods_name());
            searchGoods.setPrice(goodsInfo.getPrice());
            searchGoods.setSale_number(goodsInfo.getSale_number());
            searchGoods.setStore_name(storeService.getStoreName(goodsInfo.getStore_id()));
            searchGoods.setCategory(goodsInfo.getCategory());
            searchGoodsList.add(searchGoods);
        }

        // 调用搜索算法
        List<List<SearchGoods>> result_list = SearchUtil.search(searchGoodsList, keyword);

        //获取结果信息，转为Map
        List<List<Map<String, Object>>> map_list_list = new ArrayList<>();
        for (List<SearchGoods> list : result_list) {
            List<Map<String, Object>> map_list = new ArrayList<>();
            for (SearchGoods goods : list) {
                GoodsInfo goodsInfo = goodsInfoMapper.selectById(goods.getGoods_id());
                LambdaQueryWrapper<GoodsImageInfo> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(GoodsImageInfo::getGoods_id, goods.getGoods_id());
                List<String> images = goodsImageInfoMapper.selectList(queryWrapper).stream().map(GoodsImageInfo::getImage_url).toList();
                goodsInfo.setImages(images);
                map_list.add(MapUtil.getMap(goodsInfo));
            }
            map_list_list.add(map_list);
        }

        return Response.success(ResponseCode.SUCCESS, map_list_list);
    }
}
