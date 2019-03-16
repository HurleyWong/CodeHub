package com.hurley.wanandroid.module.project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;

import butterknife.BindView;


/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午5:14
 *      github : https://github.com/HurleyJames
 *      desc   : 项目界面
 * </pre>
 */
public class ProjectFragment extends BaseFragment<ProjectPresent> implements ProjectContract.View {

    private static final String TAG = "ProjectFragment";

    @BindView(R.id.tl_project)
    TabLayout mTbProject;
    @BindView(R.id.vp_project)
    ViewPager mVpProject;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        //将TabLayout与ViewPager绑定
        mTbProject.setupWithViewPager(mVpProject);
    }


}
