package com.hurley.codehub.module.wanandroid.core.article;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/18 2:50 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 文章列表 Presenter类
 * </pre>
 */
public class SystemArticleListPresenter extends BasePresenter<SystemArticleListContract.View> implements SystemArticleListContract.Presenter {

    /**
     * 是否刷新
     */
    private boolean isRefresh;
    /**
     * 页数
     */
    private int mPage;
    /**
     * 文章id
     */
    private int mCid;

    @Inject
    public SystemArticleListPresenter() {
        this.isRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadSystemArticles(int cid) {
        this.mCid = cid;
        RetrofitManager.create(WanAndroidApiService.class)
                .getSystemArticles(mPage, mCid)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                    mView.setSystemArticles(response.getData(), loadType);
                }, throwable -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                    mView.setSystemArticles(new ArticleBean(), loadType);
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        isRefresh = true;
        loadSystemArticles(mCid);
    }

    @Override
    public void loadMore() {
        mPage++;
        isRefresh = false;
        loadSystemArticles(mCid);
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

    @Override
    public void saveArticle(String chapterName, String superChapterName) {

    }
}
