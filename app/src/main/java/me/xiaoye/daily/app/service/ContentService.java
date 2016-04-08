package me.xiaoye.daily.app.service;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import me.xiaoye.daily.app.helper.NetHelper;
import me.xiaoye.daily.app.model.ContentModel;


public class ContentService {
    private NetHelper netHelper = NetHelper.getInstance();

    public ContentModel loadUrl(String url) {
        Gson gson = new Gson();
        try {
            Log.d("test", netHelper.loadUrl(url));
            return gson.fromJson(netHelper.loadUrl(url), ContentModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
