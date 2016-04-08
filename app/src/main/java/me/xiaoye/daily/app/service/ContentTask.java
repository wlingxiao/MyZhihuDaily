package me.xiaoye.daily.app.service;

import android.os.AsyncTask;

import java.util.List;

import me.xiaoye.daily.app.model.ContentModel;
import me.xiaoye.daily.app.model.LatestModel;
import me.xiaoye.daily.app.ui.ContentActivity;


public class ContentTask extends AsyncTask<String, Void, ContentModel> {
    private ContentActivity contentActivity;

    public ContentTask(ContentActivity contentActivity) {
        this.contentActivity = contentActivity;
    }

    @Override
    protected ContentModel doInBackground(String... strings) {
        ContentService contentService = new ContentService();
        return contentService.loadUrl(strings[0]);
    }

    @Override
    protected void onPostExecute(ContentModel s) {
        contentActivity.setDate(s);
    }


}
