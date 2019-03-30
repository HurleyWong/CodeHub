package com.hurley.codehub.module.wanandroid.core.user.collect;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.module.wanandroid.core.adapter.ArticleAdapter;
import com.hurley.codehub.module.wanandroid.core.web.WebActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/18 10:57 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 我的收藏界面
 * </pre>
 */
@Route(path = PathContainer.COLLECTION)
public class CollectionActivity extends BaseActivity<CollectionPresenter>
        implements CollectionContract.View,
                    ArticleAdapter.OnItemClickListener,
                    ArticleAdapter.OnItemChildClickListener,
                    ArticleAdapter.RequestLoadMoreListener,
                    SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "CollectionActivity";

    @BindView(R.id.srl_collection)
    SwipeRefreshLayout mSrlCollection;
    @BindView(R.id.rv_collection)
    RecyclerView mRvCollection;

    @Inject
    ArticleAdapter mArticleAdapter;

    private boolean isLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        mRvCollection.setLayoutManager(new LinearLayoutManager(this));
        mRvCollection.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlCollection.setOnRefreshListener(this);
        mArticleAdapter.isCollect(true);

        //加载数据
        mPresenter.loadCollection();
    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void setCollectionArticle(ArticleBean articleBean, int loadType) {
        setLoadDataResult(mArticleAdapter, mSrlCollection, articleBean.getDatas(), loadType);
    }

    @Override
    public void unCollectArticleSuccess(int position) {

    }

    @Override
    public void showLoading() {
        //显示刷新进度条
        mSrlCollection.setRefreshing(true);
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
