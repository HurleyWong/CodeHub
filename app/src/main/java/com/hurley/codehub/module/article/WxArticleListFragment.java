package com.hurley.codehub.module.article;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.ArticleBean;
import com.hurley.codehub.event.LoginEvent;
import com.hurley.codehub.module.adapter.ArticleAdapter;
import com.hurley.codehub.module.web.WebActivity;
import com.hurley.codehub.net.callback.RxBus;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 7:24 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 微信公众号文章列表页面
 * </pre>
 */
public class WxArticleListFragment extends BaseFragment<WxArticleListPresenter>
        implements WxArticleListContract.View,
                ArticleAdapter.OnItemClickListener,
                ArticleAdapter.OnItemChildClickListener,
                ArticleAdapter.RequestLoadMoreListener,
                SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.srl_article_list)
    SwipeRefreshLayout mSrlWxArticleList;
    @BindView(R.id.rv_article_list)
    RecyclerView mRvWxArticleList;

    private int article;

    @Inject
    ArticleAdapter mArticleAdapter;

    public static WxArticleListFragment newInstance(int id) {
        Bundle args = new Bundle();
        WxArticleListFragment fragment = new WxArticleListFragment();
        fragment.setArguments(args);
        args.putInt(Constants.ARTICLE_KEY, id);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article_list;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = getArguments().getInt(Constants.ARTICLE_KEY);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(View view) {
        //隐藏文章所属体系，因为已经确定是在公众号中显示文章列表
        mArticleAdapter.setChapterNameVisible(false);

        mRvWxArticleList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvWxArticleList.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlWxArticleList.setOnRefreshListener(this);

        mPresenter.loadWxArticles(article);

        //登录成功后刷新
        RxBus.getInstance().toFlowable(LoginEvent.class)
                .subscribe(loginEvent -> mPresenter.refresh());
    }

    @Override
    public void setWxArticles(ArticleBean articleBean, int type) {
        if (type == 0) {
            mArticleAdapter.setNewData(articleBean.getDatas());
            mSrlWxArticleList.setRefreshing(false);
            mArticleAdapter.loadMoreComplete();
        } else if (type == 1) {
            mArticleAdapter.addData(articleBean.getDatas());
            mArticleAdapter.loadMoreComplete();
        }
    }

    @Override
    public void collectWxArticleSuccess(int position, ArticleBean.DatasBean articleBean) {

    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void showLoading() {
        mSrlWxArticleList.setRefreshing(true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        WebActivity.startWeb(mArticleAdapter.getItem(position).getId(),
                mArticleAdapter.getItem(position).getLink(),
                mArticleAdapter.getItem(position).getTitle(),
                mArticleAdapter.getItem(position).getAuthor());
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }
}
