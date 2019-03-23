package com.hurley.wanandroid.module.user.analysis;

import com.hurley.wanandroid.base.BaseActivity;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/23 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析数据界面
 * </pre>
 */
public class AnalysisActivity extends BaseActivity<AnalysisPresenter> implements AnalysisContract.View {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

    }
}
