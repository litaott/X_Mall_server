package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 资源控制器类
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping("/upload")
    public Response<Map<String, Object>> upload(MultipartFile file) {
        return resourceService.upload(file);
    }


}
