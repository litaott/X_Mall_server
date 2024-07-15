package com.little.xmall.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.mapper.user.UserInfoMapper;
import com.little.xmall.service.ResourceService;
import com.little.xmall.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 资源服务实现类
 *
 * @author Little
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements ResourceService {

    private final OssUtil ossUtil;

    @Override
    public Response<Map<String, Object>> upload(MultipartFile file) {
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension = null;
            if (originalFilename != null) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            //构造新文件名称
            String objectName = UUID.randomUUID() + extension;

            //文件的请求路径
            String filePath = ossUtil.upload(file.getBytes(), objectName);
            return Response.success(ResponseCode.SUCCESS, Map.of("url", filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
