package me.xiaoye.daily.app.service;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import me.xiaoye.daily.app.helper.NetHelper;
import me.xiaoye.daily.app.model.LatestModel;
import me.xiaoye.daily.app.model.ThemesModel;

public class NetService {
    private NetHelper netHelper = NetHelper.getInstance();

    public List<LatestModel.Stories> loadUrl(String url) {
        Gson gson = new Gson();
        int i = 0;
        try {
            String s = netHelper.loadUrl(url);
            JSONObject jsonObject = new JSONObject(s);
            LatestModel latestModel = gson.fromJson(s, LatestModel.class);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            for (LatestModel.Stories stories : latestModel.getStories()) {
                stories.setImage(latestImageLoader(jsonArray.getJSONObject(i++)));
            }
            return latestModel.getStories();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String latestImageLoader(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("images");
            return jsonArray.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return "images";
        }
    }

    public List<ThemesModel.Others> loadMenuItem(String url) {
        Gson gson = new Gson();
        try {
            String s = netHelper.loadUrl(url);
            ThemesModel themesModel = gson.fromJson(s, ThemesModel.class);
            return themesModel.getOthers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
