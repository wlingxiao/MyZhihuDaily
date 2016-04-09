package me.xiaoye.daily.app.service;

import me.xiaoye.daily.app.model.ContentModel;
import me.xiaoye.daily.app.ui.ContentActivity;


public class ContentTask extends BaseTask<String, Void, ContentModel> {
    private ContentActivity contentActivity;

    public ContentTask(ContentActivity contentActivity) {
        this.contentActivity = contentActivity;
    }

    @Override
    protected ContentModel doInBackground(String... strings) {
        ContentService contentService = new ContentService();
        return contentService.loadUrl(strings[0], ContentModel.class);
    }

    @Override
    protected void onPostExecute(ContentModel s) {
        contentActivity.setDate(s);
    }

    @Override
    protected void onPreExecute() {
        BaseTask.add(this);
    }
}
