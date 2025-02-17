package com.wsb.shortlink.project.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * URL 标题接口层
 */
public interface UrlTitileService {

    /**
     * 根据 URL 获取标题
     *
     * @param url 目标网站路径
     * @return 网站标题
     */
    String getTitleByUrl(String url);
}
