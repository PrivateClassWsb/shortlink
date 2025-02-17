package com.wsb.shortlink.admin.controller;

import com.wsb.shortlink.admin.common.convention.result.Result;
import com.wsb.shortlink.admin.common.convention.result.Results;
import com.wsb.shortlink.admin.remote.ShortLinkRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * URL标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTitileController {

    // TODO 后续重构为SpringCloud Feign调用
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService(){};

    /**
     * 根据URL获取对应网站的标题
     */
    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam String url) {
        return shortLinkRemoteService.getTitleByUrl(url);
    }
}
