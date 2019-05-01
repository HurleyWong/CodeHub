package com.hurley.codehub.module.readhub.core.topic;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.readhub.TopicBean;
import com.hurley.codehub.module.readhub.core.adapter.ContentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:02
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门专题页面
 * </pre>
 */
public class TopicFragment extends BaseFragment<TopicPresenter>
        implements TopicContract.View, ContentAdapter.OnItemClickListener, ContentAdapter.OnItemChildClickListener {

    @BindView(R.id.srl_topic)
    SmartRefreshLayout mSrlTopic;
    @BindView(R.id.rv_topic)
    RecyclerView mRvTopic;

    @Inject
    ContentAdapter mContentAdapter;

    public static TopicFragment newInstance() {
        Bundle args = new Bundle();
        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initInjector() {
       mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mRvTopic.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTopic.setAdapter(mContentAdapter);
        mContentAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mContentAdapter.isFirstOnly(false);

        mContentAdapter.setOnItemClickListener(this);
        mContentAdapter.setOnItemChildClickListener(this);
        mContentAdapter.setOnLoadMoreListener(this::loadMore, mRvTopic);

        mPresenter.loadTopicContent();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void setTopicContent(List<TopicBean> topicBeans, int loadType) {
        setLoadDataResult(mContentAdapter, mSrlTopic, topicBeans, loadType);
    }

    public void loadMore() {
        mPresenter.loadMore(mContentAdapter.getItem(mContentAdapter.getItemCount() - 2).getOrder());
    }
}
