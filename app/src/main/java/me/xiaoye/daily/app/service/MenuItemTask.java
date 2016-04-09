package me.xiaoye.daily.app.service;

import java.util.List;

import me.xiaoye.daily.app.model.ThemesModel;
import me.xiaoye.daily.app.ui.MainActivity;

/**
 * Created by VAIO on 2016/4/8.
 */
public class MenuItemTask extends BaseTask<String, Void, List<ThemesModel.Others>> {

    private MainActivity mainActivity;

    public MenuItemTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private NetService netService = new NetService();
    @Override
    protected List<ThemesModel.Others> doInBackground(String... strings) {
        return netService.loadMenuItem(strings[0]);
    }

    @Override
    protected void onPreExecute() {
        BaseTask.add(this);
    }

    @Override
    protected void onPostExecute(List<ThemesModel.Others> s) {
        mainActivity.initNavigationView(s);
    }
}
