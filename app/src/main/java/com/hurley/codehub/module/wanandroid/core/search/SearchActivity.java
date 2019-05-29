package com.hurley.codehub.module.wanandroid.core.search;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;

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
 *      date    : 2019/3/27 3:39 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 搜索界面
 * </pre>
 */
@Route(path = PathContainer.SEARCH)
public class SearchActivity extends BaseActivity<SearchPresenter>
        implements SearchContract.View,
        ArticleAdapter.OnItemClickListener,
        ArticleAdapter.OnItemChildClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        ArticleAdapter.RequestLoadMoreListener {

    @BindView(R.id.srl_search)
    SwipeRefreshLayout mSrlSearch;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;

    @Inject
    ArticleAdapter mArticleAdapter;

    private SearchView mSearchView;


    @Override
    protected int getLayoutId() {
        return R.layout.search_activity;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mRvSearch.setLayoutManager(new LinearLayoutManager(this));
        mRvSearch.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlSearch.setOnRefreshListener(this);

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.home_search).getActionView();
        //最大宽度
        mSearchView.setMaxWidth(1920);
        //设置搜索图标是否显示在搜索框内
        mSearchView.setIconifiedByDefault(true);
        //搜索框是否展开，false表示展开
        mSearchView.setIconified(false);
        //设置提示词
        mSearchView.setQueryHint(getString(R.string.input_key));
        //获取焦点
        mSearchView.setFocusable(true);
        mSearchView.requestFocusFromTouch();
        //设置键盘的回车键为搜索
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchView.setOnCloseListener(() -> {
            finish();
            return true;
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mPresenter.loadSearchArticles(s);
                //搜索后，收起软键盘
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setSearchArticles(ArticleBean articleBean, int loadType) {
        setLoadDataResult(mArticleAdapter, mSrlSearch, articleBean.getDatas(), loadType);
    }

    @Override
    public void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean) {
        //重新设置该item的属性，即可改变item的isCollect的状态
        mArticleAdapter.setData(position, articleBean);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.collectArticle(position, mArticleAdapter.getItem(position));
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
