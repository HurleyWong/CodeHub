package com.hurley.wanandroid.module.system;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.SystemBean;

import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午6:52
 *      github : https://github.com/HurleyJames
 *      desc   : 体系界面
 * </pre>
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static SystemFragment newInstance() {
        Bundle args = new Bundle();
        SystemFragment fragment = new SystemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setSystems(List<SystemBean> knowledgeSystems) {

    }
}
