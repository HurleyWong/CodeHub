package com.hurley.wanandroid.module.project;

import android.view.View;

import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;



/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午5:14
 *      github : https://github.com/HurleyJames
 *      desc   : 项目界面
 * </pre>
 */
public class ProjectFragment extends BaseFragment<ProjectPresent> implements ProjectContract.View {

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }


}
