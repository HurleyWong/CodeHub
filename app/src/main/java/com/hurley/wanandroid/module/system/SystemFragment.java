package com.hurley.wanandroid.module.system;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.SystemBean;
import com.hurley.wanandroid.module.adapter.SystemAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午6:52
 *      github : https://github.com/HurleyJames
 *      desc   : 体系界面
 * </pre>
 */
public class SystemFragment extends BaseFragment<SystemPresenter>
        implements SystemContract.View, SystemAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_system)
    SwipeRefreshLayout mSrlSystem;
    @BindView(R.id.rv_system)
    RecyclerView mRvSystem;

    @Inject
    SystemAdapter mSystemAdapter;

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
        mRvSystem.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSystem.setAdapter(mSystemAdapter);

        mSystemAdapter.setOnItemClickListener(this);
        mSrlSystem.setOnRefreshListener(this);

        mPresenter.loadSystems();
    }

    @Override
    public void setSystems(List<SystemBean> systems) {
        mSystemAdapter.setNewData(systems);
        //不再刷新
        mSrlSystem.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        //加载时刷新
        mSrlSystem.setRefreshing(true);
    }

    @Override
    public void showFailed(String message) {
        //加载失败时不刷新
        mSrlSystem.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

}
