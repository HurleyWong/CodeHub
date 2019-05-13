package com.hurley.codehub.module.wanandroid.core.search;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BaseBean;
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
        RetrofitManager.create(WanAndroidApiService.class)
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
        mPage++;
        mRefresh = false;
        loadSearchArticles(mKey);
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
                                mView.showFailed(App.getAppContext().getString(R.string.uncollect_failed));
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
