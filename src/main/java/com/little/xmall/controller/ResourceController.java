package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
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
    public Response<List<String>> upload(MultipartFile[] files) {
        return resourceService.upload(files);
    }

}
