package me.xiaoye.daily.app.service;

import com.google.gson.Gson;

import java.io.IOException;

import me.xiaoye.daily.app.helper.NetHelper;


public class ContentService {
    private NetHelper netHelper = NetHelper.getInstance();

    public <T> T loadUrl(String url, Class<T> clz) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(netHelper.loadUrl(url), clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
