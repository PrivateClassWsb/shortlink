package com.wsb.shortlink.project.controller;

import com.wsb.shortlink.project.common.convention.result.Result;
import com.wsb.shortlink.project.common.convention.result.Results;
import com.wsb.shortlink.project.service.UrlTitileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * URL 标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTitileController {

    private final UrlTitileService urlTitileService;

    /**
     * 根据 URL 获取对应网站的标题
     */
    @GetMapping("/api/short-link/v1/title")
    public Result<String> getTitleByUrl(@RequestParam String url) {
        return Results.success(urlTitileService.getTitleByUrl(url));
    }
}
