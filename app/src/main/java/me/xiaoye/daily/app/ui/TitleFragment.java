package me.xiaoye.daily.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.service.BaseTask;
import me.xiaoye.daily.app.service.NetTask;
import me.xiaoye.daily.app.ui.adapter.TitleListAdapter;
import me.xiaoye.daily.app.ui.base.BaseFragment;
import me.xiaoye.daily.app.util.Constants;
import me.xiaoye.daily.app.util.DateUtil;


public class TitleFragment extends BaseFragment {

    @Bind(R.id.main_rv)
    RecyclerView recyclerView;

    TitleListAdapter titleListAdapter;
    @Bind(R.id.content_sp)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager layoutManager;
    private String latestUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        initRecyclerView();
        initSwipeRefresh();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        latestUrl = bundle.getString("data");
        new NetTask(titleListAdapter, swipeRefreshLayout).execute(latestUrl);
    }

    private void onScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isToBottom()) {
                    if (latestUrl.contains("latest")) {
                        NetTask netTask = new NetTask(titleListAdapter, swipeRefreshLayout);
                        NetTask.add(netTask);
                        netTask.execute(Constants.ZHIHU_OLD + DateUtil.nextDate());
                        swipeRefreshLayout.setRefreshing(true);
                    }
                } else {
                    BaseTask.cancelAll();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    private boolean isToBottom() {
        if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
            return true;
        }
        return false;
    }

    private void initRecyclerView() {
        titleListAdapter = new TitleListAdapter();
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(titleListAdapter);
        onScroll();
        titleListAdapter.setOnClickListener(new TitleListAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int itemId, int id) {
                Intent intent = new Intent(TitleFragment.this.getActivity(), ContentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeColors(R.color.primary, R.color.primary_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new NetTask(titleListAdapter, swipeRefreshLayout).execute(latestUrl);
            }
        });
    }
}
