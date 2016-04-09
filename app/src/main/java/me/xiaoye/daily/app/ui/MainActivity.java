package me.xiaoye.daily.app.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaoye.daily.app.R;
import me.xiaoye.daily.app.model.ThemesModel;
import me.xiaoye.daily.app.service.MenuItemTask;
import me.xiaoye.daily.app.service.NetTask;
import me.xiaoye.daily.app.ui.base.BaseActivity;
import me.xiaoye.daily.app.util.Constants;

public class MainActivity extends BaseActivity {
    private final static String TAG = "MainActivity";
    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        addFragment();
        new MenuItemTask(this).execute(Constants.ZHIHU_THEMES);

    }

    private void initToolbar() {
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); 设置导航栏图标
        //toolbar.setTitle("Title");//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setTitle("主页");
        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
                ;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        NetTask.cancelAll();
    }

    public void initNavigationView(final List<ThemesModel.Others> others) {
        Menu menu = navigationView.getMenu();
        for (ThemesModel.Others other : others) {
            menu.add(R.id.theme_group, other.getId(), Menu.NONE, other.getName());
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() != R.id.home_page) {
                    toolbar.setTitle(item.getTitle());
                    Fragment fragment = new TitleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("data", Constants.ZHIHU_THEMES_CONTENT + item.getItemId());
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                } else {
                    toolbar.setTitle("主页");
                    addFragment();
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void addFragment() {
        Fragment fragment = new TitleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", Constants.ZHIHU_LATEST);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fl, fragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fl, fragment);
        transaction.commit();
    }
}
