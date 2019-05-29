package com.hurley.codehub.module.wanandroid.core.system;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.wanandroid.SystemBean;
import com.hurley.codehub.module.wanandroid.core.adapter.SystemAdapter;

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
        return new SystemFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.system_fragment;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mRvSystem.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSystem.setAdapter(mSystemAdapter);
        mSystemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mSystemAdapter.isFirstOnly(false);

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
        ARouter.getInstance().build(PathContainer.SYSTEM_DETAIL)
                .withString(Constants.CONTENT_TITLE_KEY, mSystemAdapter.getItem(position).getName())
                .withObject(Constants.CONTENT_CHILDREN_KEY, mSystemAdapter.getItem(position).getChildren())
                .navigation();
        //将点击的体系名称保存至自己的数据库
        mPresenter.saveSystems(mSystemAdapter.getItem(position).getName());
    }

}
