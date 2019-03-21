package com.hurley.wanandroid.module.article;

import android.annotation.SuppressLint;

import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/21 11:12 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目文章列表 Presenter类
 * </pre>
 */
public class ProjectArticleListPresenter extends BasePresenter<ProjectArticleListContract.View> implements ProjectArticleListContract.Presenter {

    private int mId;
    private int mPage = 1;
    private boolean isRefresh = true;

    @Inject
    public ProjectArticleListPresenter() {}

    @SuppressLint("CheckResult")
    @Override
    public void loadProjectArticles(int id) {
        this.mId = id;
        RetrofitManager.create(ApiService.class)
                .getProjectArticles(mPage, id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<BaseBean<ArticleBean>>() {
                    @Override
                    public void accept(BaseBean<ArticleBean> response) throws Exception {
                        if (isRefresh) {
                            mView.setProjectArticles(response.getData(), 0);
                        } else {
                            mView.setProjectArticles(response.getData(), 1);
                        }
                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 1;
        isRefresh = true;
        loadProjectArticles(mId);
    }

    @Override
    public void loadMore() {
        mPage ++;
        isRefresh = false;
        loadProjectArticles(mId);
    }

    @Override
    public void collectArticle(int position, ArticleBean.DatasBean articleBean) {

    }
}
