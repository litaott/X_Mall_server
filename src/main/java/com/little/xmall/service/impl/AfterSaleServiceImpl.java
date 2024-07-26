package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.aftersale.AfterSaleInfo;
import com.little.xmall.mapper.aftersale.AfterSaleMapper;
import com.little.xmall.service.AfterSaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 售后服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
@DS("db_XMall_after_sale")
public class AfterSaleServiceImpl extends ServiceImpl<AfterSaleMapper, AfterSaleInfo> implements AfterSaleService {

    private final AfterSaleMapper afterSaleMapper;

    @Override
    public Response<Map<String, Object>> apply(AfterSaleInfo afterSaleInfo) {

        // 参数为空
        if (afterSaleInfo == null)
            return Response.error(ResponseCode.FAIL, null);

        // 插入售后订单
        afterSaleMapper.insert(afterSaleInfo);

        int id = afterSaleInfo.getAfter_sale_id();
        return Response.success(ResponseCode.AFTER_SALE_APPLY_SUCCESS, Map.of("after_sale_id", id));
    }

    @Override
    public Response<Map<String, Object>> handle(AfterSaleInfo afterSaleInfo) {

        // 参数为空
        if (afterSaleInfo == null || afterSaleInfo.getAfter_sale_id() == null)
            return Response.error(ResponseCode.FAIL, null);

        // 查询售后订单
        AfterSaleInfo a = afterSaleMapper.selectById(afterSaleInfo.getAfter_sale_id());

        // 售后订单不存在
        if (a == null)
            return Response.error(ResponseCode.AFTER_SALE_NOT_EXIST, null);

        // 售后订单已完成
        if (a.getIs_finish())
            return Response.error(ResponseCode.AFTER_SALE_ALREADY_HANDLE, null);

        // 更新售后订单
        a.setResult(afterSaleInfo.getResult());
        a.setIs_finish(true);
        afterSaleMapper.updateById(a);

        int id = a.getAfter_sale_id();
        return Response.success(ResponseCode.AFTER_SALE_HANDLE_SUCCESS, Map.of("after_sale_id", id));
    }

    @Override
    public Response<List<AfterSaleInfo>> get_user_after_sale(int user_id) {

        LambdaQueryWrapper<AfterSaleInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 获取用户售后订单
        queryWrapper.eq(AfterSaleInfo::getUser_id, user_id);
        List<AfterSaleInfo> list = afterSaleMapper.selectList(queryWrapper);

        return Response.success(ResponseCode.AFTER_SALE_USER_GET_SUCCESS, list);
    }

    @Override
    public Response<List<AfterSaleInfo>> get_store_after_sale(int store_id) {

        LambdaQueryWrapper<AfterSaleInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 获取店铺售后订单
        queryWrapper.eq(AfterSaleInfo::getStore_id, store_id);
        List<AfterSaleInfo> list = afterSaleMapper.selectList(queryWrapper);

        return Response.success(ResponseCode.AFTER_SALE_STORE_GET_SUCCESS, list);
    }
}
