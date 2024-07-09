package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.AfterSaleInfo;
import com.little.xmall.mapper.AfterSaleMapper;
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
        afterSaleMapper.insert(afterSaleInfo);
        int id = afterSaleInfo.getAfter_sale_id();
        return Response.success(ResponseCode.AFTER_SALE_APPLY_SUCCESS, Map.of("after_sale_id",id));
    }

    @Override
    public Response<Map<String, Object>> handle(AfterSaleInfo afterSaleInfo) {
        AfterSaleInfo a = afterSaleMapper.selectById(afterSaleInfo.getAfter_sale_id());
        if (a == null){
            return Response.error(ResponseCode.AFTER_SALE_NOT_EXIST, null);
        }else {
            if (a.getIs_finished().equals("已完成")){
                return Response.error(ResponseCode.AFTER_SALE_ALREADY_HANDLE, null);
            }else {
                a.setResult(afterSaleInfo.getResult());
                a.setIs_finished("已完成");
                afterSaleMapper.updateById(a);
                int id = a.getAfter_sale_id();
                return Response.success(ResponseCode.AFTER_SALE_HANDLE_SUCCESS, Map.of("after_sale_id",id));
            }
        }
    }

    @Override
    public Response<List<Map<String, Object>>> get_user_after_sale(int user_id) {
        return null;
    }

    @Override
    public Response<List<Map<String, Object>>> get_store_after_sale(int store_id) {
        return null;
    }
}
