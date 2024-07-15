package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.constant.ResponseCode;
import com.little.xmall.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final OssUtil ossUtil;

    @PostMapping("/upload")
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
