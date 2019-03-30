package com.hurley.codehub.module.wanandroid.core.article;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.ArticleBean;
import com.hurley.codehub.module.wanandroid.event.LoginEvent;
import com.hurley.codehub.module.wanandroid.core.adapter.ArticleAdapter;
import com.hurley.codehub.module.wanandroid.core.web.WebActivity;
import com.hurley.codehub.net.callback.RxBus;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/18 2:50 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 文章列表页面
 * </pre>
 */
@Route(path = PathContainer.ARTICLE_LIST)
public class SystemArticleListFragment extends BaseFragment<SystemArticleListPresenter>
        implements SystemArticleListContract.View,
                ArticleAdapter.OnItemClickListener,
                ArticleAdapter.OnItemChildClickListener,
                ArticleAdapter.RequestLoadMoreListener,
                SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SystemArticleListFragment";

    @BindView(R.id.srl_article_list)
    SwipeRefreshLayout mSrlArticleList;
    @BindView(R.id.rv_article_list)
    RecyclerView mRvArticleList;

    @Autowired
    public int cid;

    @Inject
    ArticleAdapter mArticleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article_list;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(View view) {
        //隐藏文章所属体系，因为已经是在所属文章体系中显示文章列表
        mArticleAdapter.setChapterNameVisible(false);

        mRvArticleList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvArticleList.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlArticleList.setOnRefreshListener(this);

        mPresenter.loadSystemArticles(cid);

        //登录成功后刷新
        RxBus.getInstance().toFlowable(LoginEvent.class)
                .subscribe(loginEvent -> mPresenter.refresh());
    }

    @Override
    public void setSystemArticles(ArticleBean articleBean, int loadType) {
        setLoadDataResult(mArticleAdapter, mSrlArticleList, articleBean.getDatas(), loadType);
    }

    @Override
    public void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean) {

    }

    @Override
    public void showLoading() {
        mSrlArticleList.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
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
