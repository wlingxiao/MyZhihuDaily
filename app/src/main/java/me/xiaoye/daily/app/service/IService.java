package me.xiaoye.daily.app.service;

/**
 * Created by VAIO on 2016/4/8.
 */
public interface IService {
    <T> T loadUrl(String url, Class<T> t);
}
