package com.wsb.shortlink.admin.controller;

import com.wsb.shortlink.admin.common.convention.result.Result;
import com.wsb.shortlink.admin.remote.ShortLinkActualRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * URL标题控制层
 */
@RestController(value = "urlTitleControllerByAdmin")
@RequiredArgsConstructor
public class UrlTitileController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    /**
     * 根据URL获取对应网站的标题
     */
    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam String url) {
        return shortLinkActualRemoteService.getTitleByUrl(url);
    }
}
