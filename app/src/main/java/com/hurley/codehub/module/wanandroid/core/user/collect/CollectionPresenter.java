package com.hurley.codehub.module.wanandroid.core.user.collect;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.Constants;
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
 *      date    : 2019/3/18 10:58 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 我的收藏 Presenter类
 * </pre>
 */
public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {

    /**
     * 页码
     */
    private int mPage;

    /**
     * 是否刷新
     */
    private boolean isRefresh;

    @Inject
    public CollectionPresenter() {
        isRefresh = true;
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadCollection() {
        if (isRefresh) {
            mView.showLoading();
        }
        LogUtils.e(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME));
        LogUtils.e(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.PASSWORD));
        LogUtils.e(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS));
        RetrofitManager.create(WanAndroidApiService.class)
                .getCollectArticles(mPage)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<BaseBean<ArticleBean>>() {
                    @Override
                    public void accept(BaseBean<ArticleBean> response) throws Exception {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setCollectionArticle(response.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setCollectionArticle(new ArticleBean(), loadType);
                        mView.showFailed(throwable.getMessage());
                    }
                });

    }

    @Override
    public void refresh() {
        //页数设置为首页
        mPage = 0;
        isRefresh = true;
        loadCollection();
    }

    @Override
    public void loadMore() {
        mPage ++;
        isRefresh = false;
        loadCollection();
    }

    @Override
    public void unCollectArticle(int position, ArticleBean.DatasBean articleBean) {

    }
}
