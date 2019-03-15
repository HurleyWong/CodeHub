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
@Route(path = "/main/HomeActivity")
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
            mFragments[0] = IndexFragment.newInstance();
            mFragments[1] = SystemFragment.newInstance();
            mFragments[2] = WechatFragment.newInstance();
            mFragments[3] = ProjectFragment.newInstance();
            mFragments[4] = UserFragment.newInstance();
            loadMultipleRootFragment(R.id.layout_fragment, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3],
                    mFragments[4]);
        } else {
            mFragments[0] = indexFragment;
            mFragments[1] = findFragment(SystemFragment.class);
            mFragments[2] = findFragment(WechatFragment.class);
            mFragments[3] = findFragment(ProjectFragment.class);
            mFragments[4] = findFragment(UserFragment.class);
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
                showHideFragment(mFragments[0], mFragments[preIndex]);
                preIndex = 0;
                break;
            case R.id.home_system:
                mToolbar.setTitle(R.string.home_nav_system);
                showHideFragment(mFragments[1], mFragments[preIndex]);
                preIndex = 1;
                break;
            case R.id.home_wechat:
                mToolbar.setTitle(R.string.home_nav_wechat);
                showHideFragment(mFragments[2], mFragments[preIndex]);
                preIndex = 2;
                break;
            case R.id.home_project:
                mToolbar.setTitle(R.string.home_nav_project);
                showHideFragment(mFragments[3], mFragments[preIndex]);
                preIndex = 3;
                break;
            case R.id.home_user:
                mToolbar.setTitle(R.string.home_nav_user);
                showHideFragment(mFragments[4], mFragments[preIndex]);
                preIndex = 4;
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
                ToastUtils.setBgColor(ContextCompat.getColor(this, R.color.gray));
                ToastUtils.showShort(R.string.home_exit);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
