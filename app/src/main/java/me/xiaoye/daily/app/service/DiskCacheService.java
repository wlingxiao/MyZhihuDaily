package me.xiaoye.daily.app.service;

import android.support.annotation.Nullable;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import me.xiaoye.daily.app.App;
import me.xiaoye.daily.app.util.Md5FileNameGenerator;
import me.xiaoye.daily.app.util.StorageUtil;

/**
 * Created by VAIO on 2016/4/9.
 */
public class DiskCacheService {

    private static DiskLruCache diskLruCache;

    public static void service() {
        File dir = StorageUtil.getCacheDirectory(App.getApplication());
        try {
            diskLruCache = DiskLruCache.open(dir, 1, 1, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void put(String rawStr, String data) {
        byte[] bytes = data.getBytes();
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(Md5FileNameGenerator.generate(rawStr));
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                outputStream.write(bytes);
                editor.commit();
            }
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public static String get(String rawUrl) {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(Md5FileNameGenerator.generate(rawUrl));
            InputStream in = snapshot.getInputStream(0);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            char[] chars = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.read(chars) != -1) {
                stringBuilder.append(chars);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
