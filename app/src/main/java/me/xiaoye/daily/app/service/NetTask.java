package me.xiaoye.daily.app.service;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import me.xiaoye.daily.app.model.LatestModel;
import me.xiaoye.daily.app.ui.TitleFragment;
import me.xiaoye.daily.app.ui.adapter.TitleListAdapter;


public class NetTask extends BaseTask<String, Void, List<LatestModel.Stories>> {
    private TitleFragment titleFragment;

    public NetTask(TitleFragment titleFragment) {
        this.titleFragment = titleFragment;
    }

    private NetService netService = new NetService();

    @Override
    protected void onPreExecute() {
        BaseTask.add(this);
    }

    @Override
    protected List<LatestModel.Stories> doInBackground(String... strings) {
        return netService.loadUrl(strings[0]);
    }

    @Override
    protected void onPostExecute(List<LatestModel.Stories> s) {
        titleFragment.notifyAdapterDateSetChanged(s);
        titleFragment.cancelRefresh();
    }
}
