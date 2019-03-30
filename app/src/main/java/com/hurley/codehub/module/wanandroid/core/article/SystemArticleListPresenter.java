package com.hurley.codehub.module.wanandroid.core.article;

import android.annotation.SuppressLint;

import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseBean;
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
                .subscribe(new Consumer<BaseBean<ArticleBean>>() {
                    @Override
                    public void accept(BaseBean<ArticleBean> response) throws Exception {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setSystemArticles(response.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setSystemArticles(new ArticleBean(), loadType);
                    }
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
        mPage ++;
        isRefresh = false;
        loadSystemArticles(mCid);
    }

    @Override
    public void collectArticle(int position, ArticleBean.DatasBean articleBean) {

    }
}
