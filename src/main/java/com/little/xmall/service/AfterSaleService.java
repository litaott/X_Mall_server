package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.aftersale.AfterSaleInfo;

import java.util.List;
import java.util.Map;

/**
 * 售后服务接口
 * @author Little
 */
public interface AfterSaleService extends IService<AfterSaleInfo> {

    /**
     * 用户申请售后
     * @param afterSaleInfo 售后信息
     * @return Response
     */
    Response<Map<String,Object>> apply(AfterSaleInfo afterSaleInfo);

    /**
     * 店铺处理售后
     * @param afterSaleInfo 售后信息
     * @return Response
     */
    Response<Map<String,Object>> handle(AfterSaleInfo afterSaleInfo);

    /**
     * 获取用户售后订单列表
     * @param user_id 用户id
     * @return Response
     */
    Response<List<Map<String,Object>>> get_user_after_sale(int user_id);

    /**
     * 获取店铺售后订单列表
     * @param store_id 店铺id
     * @return Response
     */
    Response<List<Map<String,Object>>> get_store_after_sale(int store_id);
}
