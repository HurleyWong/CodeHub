package com.hurley.wanandroid.module.search;

import android.annotation.SuppressLint;

import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.app.LoadType;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.PageBean;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

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
                .subscribe(new Consumer<BaseBean<ArticleBean>>() {
                    @Override
                    public void accept(BaseBean<ArticleBean> response) throws Exception {
                        int loadType = mRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setSearchArticles(response.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setSearchArticles(new ArticleBean(), loadType);
                    }
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
