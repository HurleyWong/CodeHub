package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.util.ColorUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 10:57
 *      github  : https://github.com/HurleyJames
 *      desc    : 标签界面
 * </pre>
 */
@Route(path = PathContainer.TAG)
public class TagActivity extends BaseActivity<TagPresenter> implements TagContract.View {

    @BindView(R.id.tl_tag)
    TabLayout mTlTag;
    @BindView(R.id.vp_tag)
    ViewPager mVpTag;

    private List<String> mTitle;
    private List<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tag;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        fragments = new ArrayList<>();
        fragments.add(AllTagFragment.newInstance());
        fragments.add(FollowedTagFragment.newInstance());

        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.all_tag));
        mTitle.add(getString(R.string.followed_tag));

        for (String tab : mTitle) {
            mTlTag.addTab(mTlTag.newTab().setText(tab));
        }

        mVpTag.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int i) {
                return mTitle.get(i);
            }
        });
        mVpTag.setCurrentItem(0, false);
        mTlTag.setupWithViewPager(mVpTag, true);
    }

    /**
     * 显示返回键
     *
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
