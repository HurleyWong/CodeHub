package com.hurley.codehub.module.search;

import android.annotation.SuppressLint;

import com.hurley.codehub.api.ApiService;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.ArticleBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/27 3:37 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 搜索 Presenter类
 * </pre>
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private int mPage;
    private boolean mRefresh;
    private String mKey;

    @Inject
    public SearchPresenter() {
        this.mRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadSearchArticles(String key) {
        this.mKey = key;
        RetrofitManager.create(ApiService.class)
                .getSearchArticles(mPage, mKey)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    int loadType = mRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                    mView.setSearchArticles(response.getData(), loadType);
                }, throwable -> {
                    int loadType = mRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                    mView.setSearchArticles(new ArticleBean(), loadType);
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        mRefresh = true;
        loadSearchArticles(mKey);
    }

    @Override
    public void loadMore() {
        mPage ++;
        mRefresh = false;
        loadSearchArticles(mKey);
    }
}
