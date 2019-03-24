package com.hurley.wanandroid.module.user.analysis;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/23 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析数据界面
 * </pre>
 */
@Route(path = PathContainer.ANALYSIS)
public class AnalysisActivity extends BaseActivity<AnalysisPresenter> implements AnalysisContract.View {

    @BindView(R.id.tb_analysis)
    TabLayout mTbAnalysis;
    @BindView(R.id.vp_analysis)
    ViewPager mVpAnalysis;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_analysis;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        //mPresenter.getNearbyArticles();
        mPresenter.getAllArticles();
        //LogUtils.e(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getStringSet(Constants.SYSTEM_NAME));

        tabTitles.add(getString(R.string.analysis_week));
        tabTitles.add(getString(R.string.analysis_month));
        tabTitles.add(getString(R.string.analysis_year));

        fragments.add(new AnalysisWeekFragment());
        fragments.add(new AnalysisMonthFragment());
        fragments.add(new AnalysisYearFragment());

        mVpAnalysis.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles.get(position);
            }
        });


        //让每个标签平分TabLayout的全部宽度，需配合MODE_FIXED使用
        mTbAnalysis.setTabGravity(TabLayout.GRAVITY_FILL);
        mTbAnalysis.setupWithViewPager(mVpAnalysis);

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










