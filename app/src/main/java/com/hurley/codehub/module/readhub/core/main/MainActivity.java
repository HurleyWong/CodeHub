package com.hurley.codehub.module.readhub.core.main;

import android.annotation.SuppressLint;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.module.wanandroid.event.LoginEvent;
import com.hurley.codehub.module.wanandroid.event.LogoutEvent;
import com.hurley.codehub.net.callback.RxBus;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 3:15 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Route(path = PathContainer.MAIN)
public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View, View.OnClickListener {

    @BindView(R.id.dl_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_main)
    NavigationView mNavigationView;

    private LinearLayout mLlLogin;
    private TextView mTvLoginStatus;

    /**
     * 是否登录
     */
    private boolean isLogin;

    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activitiy_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
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
    }

    private void setNavigationViewListener() {
        mNavigationView.setCheckedItem(R.id.home_readhub);
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                //玩安卓页面
                case R.id.home_wan_android:
                    menuItem.setChecked(true);
                    ARouter.getInstance().build(PathContainer.HOME).navigation();
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
        });
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
                ActivityUtils.finishAllActivities();
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
}
