package me.xiaoye.daily.app.helper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetHelper {
    public static OkHttpClient okHttpClient;
    private static NetHelper netHelper;

    private NetHelper() {
    }

    public synchronized static NetHelper getInstance() {
        if (netHelper == null) {
            okHttpClient = new OkHttpClient();
            netHelper = new NetHelper();
        }
        return netHelper;
    }

    public String loadUrl(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

}
