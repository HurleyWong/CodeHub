package com.hurley.wanandroid.module.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.module.index.IndexFragment;
import com.hurley.wanandroid.module.project.ProjectFragment;
import com.hurley.wanandroid.module.system.SystemFragment;
import com.hurley.wanandroid.module.user.UserFragment;
import com.hurley.wanandroid.module.wechat.WechatFragment;


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
public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private ISupportFragment[] mFragments = new ISupportFragment[5];
    private long mExitTime;
    private int preIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {

    }

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

    }

    /**
     * 底部导航栏的选择
     * @param menuItem
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_index:
                mToolbar.setTitle(R.string.home_nav_index);
                showHideFragment(mFragments[Constants.TYPE_INDEX], mFragments[preIndex]);
                preIndex = Constants.TYPE_INDEX;
                break;
            case R.id.home_system:
                mToolbar.setTitle(R.string.home_nav_system);
                showHideFragment(mFragments[Constants.TYPE_SYSTEM], mFragments[preIndex]);
                preIndex = Constants.TYPE_SYSTEM;
                break;
            case R.id.home_wechat:
                mToolbar.setTitle(R.string.home_nav_wechat);
                showHideFragment(mFragments[Constants.TYPE_WECHAT], mFragments[preIndex]);
                preIndex = Constants.TYPE_WECHAT;
                break;
            case R.id.home_project:
                mToolbar.setTitle(R.string.home_nav_project);
                showHideFragment(mFragments[Constants.TYPE_PROJECT], mFragments[preIndex]);
                preIndex = Constants.TYPE_PROJECT;
                break;
            case R.id.home_user:
                mToolbar.setTitle(R.string.home_nav_user);
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
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ToolBar栏的选择操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home_website) {
            //点击热搜以及常用网站

        } else if (item.getItemId() == R.id.home_search) {
            //点击搜索

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 再按一次退出
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


}
