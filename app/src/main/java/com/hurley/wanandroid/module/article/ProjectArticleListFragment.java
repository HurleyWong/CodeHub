package com.hurley.wanandroid.module.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.module.adapter.ArticleAdapter;
import com.hurley.wanandroid.module.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/21 11:08 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目文章列表页面
 * </pre>
 */
public class ProjectArticleListFragment extends BaseFragment<ProjectArticleListPresenter>
        implements ProjectArticleListContract.View,
                ArticleAdapter.OnItemClickListener,
                ArticleAdapter.OnItemChildClickListener,
                ArticleAdapter.RequestLoadMoreListener,
                SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_article_list)
    SwipeRefreshLayout mSrlProjectArticle;
    @BindView(R.id.rv_article_list)
    RecyclerView mRvProjectArticle;

    private int article;

    @Inject
    ArticleAdapter mArticleAdapter;

    private List<ArticleBean.DatasBean> mArticleBeans = new ArrayList<>();

    public static ProjectArticleListFragment newInstance(int id) {
        Bundle args = new Bundle();
        ProjectArticleListFragment fragment = new ProjectArticleListFragment();
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

    @Override
    protected void initView(View view) {
        //隐藏文章所属体系，因为已经是在所属文章体系中显示文章列表
        mArticleAdapter.setChapterNameVisible(false);

        mRvProjectArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvProjectArticle.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlProjectArticle.setOnRefreshListener(this);

        mPresenter.loadProjectArticles(article);
    }

    @Override
    public void setProjectArticles(ArticleBean articleBean, int type) {
        if (type == 0) {
            mArticleAdapter.setNewData(articleBean.getDatas());
            mSrlProjectArticle.setRefreshing(false);
            mArticleAdapter.loadMoreComplete();
        } else if (type == 1) {
            mArticleAdapter.addData(articleBean.getDatas());
            mArticleAdapter.loadMoreComplete();
        }
    }

    @Override
    public void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean) {

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
