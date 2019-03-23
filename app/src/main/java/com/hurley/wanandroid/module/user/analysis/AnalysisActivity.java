package com.hurley.wanandroid.module.user.analysis;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;

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

    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
