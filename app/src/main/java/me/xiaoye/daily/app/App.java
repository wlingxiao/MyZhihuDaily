package me.xiaoye.daily.app;

import android.app.Application;

/**
 * Created by VAIO on 2016/4/9.
 */
public class App extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }
}
