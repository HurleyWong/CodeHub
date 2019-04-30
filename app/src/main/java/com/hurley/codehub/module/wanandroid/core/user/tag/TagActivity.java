package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.module.wanandroid.core.user.tag.all.AllTagFragment;
import com.hurley.codehub.module.wanandroid.core.user.tag.followed.FollowedTagFragment;

import java.util.ArrayList;
import java.util.List;

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
public class TagActivity extends BaseActivity {

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
