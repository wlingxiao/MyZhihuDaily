package me.xiaoye.daily.app.service;

import android.os.AsyncTask;

import java.util.HashSet;
import java.util.List;

import me.xiaoye.daily.app.model.LatestModel;
import me.xiaoye.daily.app.ui.adapter.TitleListAdapter;


public class NetTask extends AsyncTask<String, Void, List<LatestModel.Stories>> {

    private static HashSet<NetTask> hashSet = new HashSet<>();

    private TitleListAdapter titleListAdapter;

    public NetTask(TitleListAdapter titleListAdapter) {
        this.titleListAdapter = titleListAdapter;
    }

    private NetService netService = new NetService();

    @Override
    protected void onPreExecute() {
        hashSet.add(this);
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

    public static void remove(NetTask netTask) {
        if (hashSet.contains(netTask)) {
            hashSet.remove(netTask);
        }
    }

    public static void add(NetTask netTask) {
        hashSet.add(netTask);
    }

    public static void cancelAll() {
        for (NetTask i : hashSet) {
            if (!i.isCancelled()) {
                i.cancel(true);
                remove(i);
            }
        }
    }
}
