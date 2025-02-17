package com.wsb.shortlink.project.service.impl;

import com.wsb.shortlink.project.service.UrlTitileService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;


import java.net.URL;
import java.net.HttpURLConnection;

/**
 * URL 标题接口实现层
 */
@Service
public class UrlTitileServiceImpl implements UrlTitileService {

    @SneakyThrows
    @Override
    public String getTitleByUrl(String url) {

        URL tagetUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) tagetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Document document = Jsoup.connect(url).get();
            return document.title();
        }

        return "Erro while fetching title";
    }
}
