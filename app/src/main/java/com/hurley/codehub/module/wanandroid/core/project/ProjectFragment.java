package com.hurley.codehub.module.wanandroid.core.project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.wanandroid.ProjectBean;
import com.hurley.codehub.module.wanandroid.core.adapter.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

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
    TabLayout mTlProject;
    @BindView(R.id.vp_project)
    ViewPager mVpProject;

    private ProjectAdapter mProjectAdapter;

    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.project_fragment;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mPresenter.loadProjects();
    }


    @Override
    public void setProjects(List<ProjectBean> projects) {
        List<Integer> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        if (projects.size() > 0) {
            for (ProjectBean projectBean : projects) {
                ids.add(projectBean.getId());
                names.add(projectBean.getName());
            }
        }

        mProjectAdapter = new ProjectAdapter(getChildFragmentManager(), names, ids);
        mVpProject.setAdapter(mProjectAdapter);
        mVpProject.setOffscreenPageLimit(1);
        mVpProject.setCurrentItem(0, false);
        //将TabLayout与ViewPager绑定
        mTlProject.setupWithViewPager(mVpProject, true);


    }
}
