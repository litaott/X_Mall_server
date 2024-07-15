package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.user.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 资源服务接口
 * @author Little
 */
public interface ResourceService extends IService<UserInfo> {

    /**
     * 上传资源
     *
     * @param file 文件
     * @return Response
     */
    Response<Map<String, Object>> upload(MultipartFile file);

}
