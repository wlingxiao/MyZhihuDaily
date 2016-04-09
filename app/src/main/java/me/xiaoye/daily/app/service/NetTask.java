package me.xiaoye.daily.app.service;

import java.util.List;

import me.xiaoye.daily.app.model.LatestModel;
import me.xiaoye.daily.app.ui.adapter.TitleListAdapter;


public class NetTask extends BaseTask<String, Void, List<LatestModel.Stories>> {

    private TitleListAdapter titleListAdapter;

    public NetTask(TitleListAdapter titleListAdapter) {
        this.titleListAdapter = titleListAdapter;
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
        titleListAdapter.setList(s);
        titleListAdapter.notifyDataSetChanged();
    }


}
