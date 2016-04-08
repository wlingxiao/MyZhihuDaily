package me.xiaoye.daily.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.model.ContentModel;
import me.xiaoye.daily.app.service.ContentTask;
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
        ContentTask contentTask = new ContentTask(this);
        contentTask.execute("http://news-at.zhihu.com/api/4/news/" + getIntent().getIntExtra("id", 0));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void setDate(ContentModel s) {
        Glide.with(this).load(s.getImage()).into(imageView);
        webView.loadData(s.getBody(), "text/html;charset=UTF-8", null);
    }
}
