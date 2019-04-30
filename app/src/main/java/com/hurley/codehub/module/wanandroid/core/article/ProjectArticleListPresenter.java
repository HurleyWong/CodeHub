package com.hurley.codehub.module.wanandroid.core.article;

import android.annotation.SuppressLint;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import org.litepal.util.Const;

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
    public ProjectArticleListPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadProjectArticles(int id) {
        this.mId = id;
        RetrofitManager.create(WanAndroidApiService.class)
                .getProjectArticles(mPage, id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (isRefresh) {
                        mView.setProjectArticles(response.getData(), 0);
                    } else {
                        mView.setProjectArticles(response.getData(), 1);
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
        mPage++;
        isRefresh = false;
        loadProjectArticles(mId);
    }

    @SuppressLint("CheckResult")
    @Override
    public void collectArticle(int position, ArticleBean.DatasBean articleBean) {
        //如果已登录
        if (SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS)) {
            if (articleBean.isCollect()) {
                //如果已收藏，则取消收藏文章
                RetrofitManager.create(WanAndroidApiService.class)
                        .unCollectArticle1(articleBean.getId(), -1)
                        .compose(RxSchedulers.applySchedulers())
                        .compose(mView.bindToLife())
                        .subscribe(response -> {
                            if (response.getErrorCode() == BaseBean.SUCCESS) {
                                articleBean.setCollect(!articleBean.isCollect());
                                mView.collectArticleSuccess(position, articleBean);
                                mView.showSuccess(App.getAppContext().getString(R.string.uncollect_success));
                            } else {
                                mView.showFailed(App.getAppContext().getString(R.string.uncollect_success));
                            }
                        }, throwable -> mView.showFailed(throwable.getMessage()));
            } else {
                //如果未收藏，则收藏该文章
                RetrofitManager.create(WanAndroidApiService.class)
                        .collectInsideArticle(articleBean.getId())
                        .compose(RxSchedulers.applySchedulers())
                        .compose(mView.bindToLife())
                        .subscribe(response -> {
                            if (response.getErrorCode() == BaseBean.SUCCESS) {
                                articleBean.setCollect(!articleBean.isCollect());
                                mView.collectArticleSuccess(position, articleBean);
                                mView.showSuccess(App.getAppContext().getString(R.string.collect_success));
                            } else {
                                mView.showFailed(App.getAppContext().getString(R.string.collect_failed));
                            }
                        }, throwable -> mView.showFailed(throwable.getMessage()));
            }
        } else {
            //如果未登录，跳转至登录界面
            ARouter.getInstance().build(PathContainer.LOGIN).navigation();
        }
    }
}
















