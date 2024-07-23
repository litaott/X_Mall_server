package com.little.xmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.little.xmall.constant.Response;
import com.little.xmall.entity.user.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 资源服务接口
 *
 * @author Little
 */
public interface ResourceService extends IService<UserInfo> {

    /**
     * 上传资源
     *
     * @param files 文件
     * @return Response
     */
    Response<List<String>> upload(MultipartFile[] files);

}
