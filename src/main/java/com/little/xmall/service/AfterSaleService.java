package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.AfterSaleInfo;

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
}
