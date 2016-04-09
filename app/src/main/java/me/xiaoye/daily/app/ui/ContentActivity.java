package me.xiaoye.daily.app.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.model.ContentModel;
import me.xiaoye.daily.app.service.BaseTask;
import me.xiaoye.daily.app.service.ContentTask;
import me.xiaoye.daily.app.ui.base.BaseActivity;
import me.xiaoye.daily.app.util.Constants;


public class ContentActivity extends BaseActivity {

    @Bind(R.id.content_webview)
    WebView webView;

    @Bind(R.id.content_iv)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_content);
        ButterKnife.bind(this);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setTextZoom(300);
        BaseTask<String, Void, ContentModel> task = new ContentTask(this);
        task.execute(Constants.ZHIHU_CONTENT + getIntent().getIntExtra("id", 0));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseTask.cancelAll();
        ButterKnife.unbind(this);

    }

    public void setDate(ContentModel s) {
        Glide.with(this).load(s.getImage()).into(imageView);
        webView.loadDataWithBaseURL("file:///android_asset/",Constants.CSS + s.getBody(), "text/html;charset=UTF-8", "UTF-8", null);
    }
}
