package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.AfterSaleInfo;
import com.little.xmall.mapper.AfterSaleMapper;
import com.little.xmall.service.AfterSaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
