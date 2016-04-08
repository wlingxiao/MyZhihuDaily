package me.xiaoye.daily.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.service.NetTask;
import me.xiaoye.daily.app.ui.adapter.TitleListAdapter;
import me.xiaoye.daily.app.util.Constants;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_list)
    RecyclerView recyclerView;

    TitleListAdapter titleListAdapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        titleListAdapter = new TitleListAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(titleListAdapter);
        onScroll();
        titleListAdapter.setOnClickListener(new TitleListAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int itemId, int id) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        NetTask netTask = new NetTask(titleListAdapter);
        NetTask.add(netTask);
        netTask.execute(Constants.ZHIHU_LATEST);
    }

    private void onScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isToBottom()) {
                    NetTask netTask = new NetTask(titleListAdapter);
                    NetTask.add(netTask);
                    netTask.execute(Constants.ZHIHU_OLD);
                } else {
                    NetTask.cancelAll();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    private boolean isToBottom() {
        if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1){
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        NetTask.cancelAll();
    }
}
