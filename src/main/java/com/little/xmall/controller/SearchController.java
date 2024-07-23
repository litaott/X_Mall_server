package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 搜索控制器类
 *
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search_by_keyword")
    public Response<List<List<Map<String, Object>>>> searchByKeyword(String keyword) {
        return searchService.searchByKeyword(keyword);
    }

}
