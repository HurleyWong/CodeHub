package com.hurley.codehub.module.wanandroid.core.main;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.module.wanandroid.event.LoginEvent;
import com.hurley.codehub.module.wanandroid.event.LogoutEvent;
import com.hurley.codehub.module.wanandroid.core.index.IndexFragment;
import com.hurley.codehub.module.wanandroid.core.project.ProjectFragment;
import com.hurley.codehub.module.wanandroid.core.system.SystemFragment;
import com.hurley.codehub.module.wanandroid.core.user.UserFragment;
import com.hurley.codehub.module.wanandroid.core.wechat.WechatFragment;
import com.hurley.codehub.net.callback.RxBus;


import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 上午10:51
 *      github : https://github.com/HurleyJames
 *      desc   : 主页界面
 * </pre>
 */
@Route(path = PathContainer.HOME)
public class HomeActivity extends BaseActivity<HomePresenter>
        implements HomeContract.View, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.dl_home)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_home)
    NavigationView mNavigationView;
    @BindView(R.id.bnv_home)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.toolbar_title)
    TextView mTvTitle;

    private LinearLayout mLlLogin;
    private TextView mTvLoginStatus;

    private ISupportFragment[] mFragments = new ISupportFragment[5];
    private long mExitTime;
    private int preIndex;

    /**
     * 是否登录
     */
    private boolean isLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        ISupportFragment indexFragment = findFragment(IndexFragment.class);
        if (indexFragment == null) {
            mFragments[Constants.TYPE_INDEX] = IndexFragment.newInstance();
            mFragments[Constants.TYPE_SYSTEM] = SystemFragment.newInstance();
            mFragments[Constants.TYPE_WECHAT] = WechatFragment.newInstance();
            mFragments[Constants.TYPE_PROJECT] = ProjectFragment.newInstance();
            mFragments[Constants.TYPE_USER] = UserFragment.newInstance();
            loadMultipleRootFragment(R.id.layout_fragment, Constants.TYPE_INDEX,
                    mFragments[Constants.TYPE_INDEX],
                    mFragments[Constants.TYPE_SYSTEM],
                    mFragments[Constants.TYPE_WECHAT],
                    mFragments[Constants.TYPE_PROJECT],
                    mFragments[Constants.TYPE_USER]);
        } else {
            mFragments[Constants.TYPE_INDEX] = indexFragment;
            mFragments[Constants.TYPE_SYSTEM] = findFragment(SystemFragment.class);
            mFragments[Constants.TYPE_WECHAT] = findFragment(WechatFragment.class);
            mFragments[Constants.TYPE_PROJECT] = findFragment(ProjectFragment.class);
            mFragments[Constants.TYPE_USER] = findFragment(UserFragment.class);
        }

        initNavigationHeaderView();
        setNavigationViewListener();

        //设置用户登录状态
        setUserStatus();
        //登录成功后需重新设置用户状态
        RxBus.getInstance().toFlowable(LoginEvent.class).subscribe(loginEvent -> setUserStatus());
        //退出登录后需更新设置用户状态
        RxBus.getInstance().toFlowable(LogoutEvent.class).subscribe(logoutEvent -> setUserStatus());

    }

    /**
     * 初始化左侧导航栏
     */
    private void initNavigationHeaderView() {
        View headerView = mNavigationView.getHeaderView(0);
        mLlLogin = headerView.findViewById(R.id.ll_login);
        mTvLoginStatus = headerView.findViewById(R.id.tv_login_status);
        mLlLogin.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * 设置左侧导航栏监听
     */
    private void setNavigationViewListener() {
        mNavigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        //玩安卓页面
                        case R.id.home_wan_android:
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;
                        //ReadHub页面
                        case R.id.home_readhub:
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;
                        //DiyCode页面
                        case R.id.home_diycode:
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;
                        //Github Trending页面
                        case R.id.home_github_trending:
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;
                        default:
                            break;
                    }
                    return false;
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login:
                if (!isLogin) {
                    //未登录
                    ARouter.getInstance().build(PathContainer.LOGIN).navigation();
                }
            default:
                break;
        }
    }

    /**
     * 底部导航栏的选择
     *
     * @param menuItem
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_index:
                mTvTitle.setText(R.string.home_nav_index);
                mToolbar.setTitle("");
                showHideFragment(mFragments[Constants.TYPE_INDEX], mFragments[preIndex]);
                preIndex = Constants.TYPE_INDEX;
                break;
            case R.id.home_system:
                mTvTitle.setText(R.string.home_nav_system);
                mToolbar.setTitle("");
                showHideFragment(mFragments[Constants.TYPE_SYSTEM], mFragments[preIndex]);
                preIndex = Constants.TYPE_SYSTEM;
                break;
            case R.id.home_wechat:
                mTvTitle.setText(R.string.home_nav_wechat);
                mToolbar.setTitle("");
                showHideFragment(mFragments[Constants.TYPE_WECHAT], mFragments[preIndex]);
                preIndex = Constants.TYPE_WECHAT;
                break;
            case R.id.home_project:
                mTvTitle.setText(R.string.home_nav_project);
                mToolbar.setTitle("");
                showHideFragment(mFragments[Constants.TYPE_PROJECT], mFragments[preIndex]);
                preIndex = Constants.TYPE_PROJECT;
                break;
            case R.id.home_user:
                mTvTitle.setText(R.string.home_nav_user);
                mToolbar.setTitle("");
                showHideFragment(mFragments[Constants.TYPE_USER], mFragments[preIndex]);
                preIndex = Constants.TYPE_USER;
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 创建
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_options_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ToolBar栏的选择操作
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home_website) {
            //点击热搜以及常用网站
        } else if (item.getItemId() == R.id.home_search) {
            //点击搜索
            ARouter.getInstance().build(PathContainer.SEARCH).navigation();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 再按一次退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime > Constants.INTERVAL_TIME)) {
                toast(R.string.home_exit);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置用户状态
     */
    public void setUserStatus() {
        isLogin = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS);
        if (isLogin) {
            //已登录
            mTvLoginStatus.setText(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME));
        } else {
            //未登录
            mTvLoginStatus.setText(R.string.click_login);
        }
    }
}
