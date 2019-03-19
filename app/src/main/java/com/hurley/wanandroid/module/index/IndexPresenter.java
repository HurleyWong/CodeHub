package com.hurley.wanandroid.module.index;



import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.App;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.app.LoadType;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.BannerBean;
import com.hurley.wanandroid.bean.PageBean;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:36 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首页 Presenter类
 * </pre>
 */
public class IndexPresenter extends BasePresenter<IndexContract.View> implements IndexContract.Presenter {

    /**
     * 页数
     */
    private int mPage;
    /**
     * 是否刷新
     */
    private boolean isRefresh;

    @Inject
    public IndexPresenter() {
        this.isRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadBanners() {
        RetrofitManager.create(ApiService.class)
                .getIndexBanners()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.setBanners(response.getData());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> mView.showFailed(throwable.getMessage()));
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadArticles() {
        RetrofitManager.create(ApiService.class)
                .getIndexArticles(mPage)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setArticles(response.getData(),loadType);
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                    mView.setArticles(new ArticleBean(), loadType);
                });
    }

    @Override
    public void refresh() {
        //页数设置为首页
        mPage = 0;
        isRefresh = true;
        loadBanners();
        loadArticles();
    }

    @Override
    public void loadMore() {
        mPage ++;
        isRefresh = false;
        loadArticles();
    }

    /**
     * 收藏文章
     * @param position
     * @param articleBean
     */
    @SuppressLint("CheckResult")
    @Override
    public void collectArticle(int position, ArticleBean.DatasBean articleBean) {
        if (SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS)) {
            if (articleBean.isCollect()) {
                //如果已收藏
                RetrofitManager.create(ApiService.class)
                        .unCollectArticle2(articleBean.getId(), -1)
                        .compose(RxSchedulers.applySchedulers())
                        .compose(mView.bindToLife())
                        .subscribe(new Consumer<BaseBean>() {
                            @Override
                            public void accept(BaseBean response) throws Exception {
                                if (response.getErrorCode() == BaseBean.SUCCESS) {
                                    articleBean.setCollect(!articleBean.isCollect());
                                    mView.collectArticleSuccess(position, articleBean);
                                    mView.showSuccess(App.getAppContext().getString(R.string.uncollect_success));
                                } else {
                                    mView.showFailed(App.getAppContext().getString(R.string.uncollect_failed));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFailed(throwable.getMessage());
                            }
                        });
            } else {
                //如果未收藏
                RetrofitManager.create(ApiService.class)
                        .collectInsideArticle(articleBean.getId())
                        .compose(RxSchedulers.applySchedulers())
                        .compose(mView.bindToLife())
                        .subscribe(new Consumer<BaseBean>() {
                            @Override
                            public void accept(BaseBean response) throws Exception {
                                if (response.getErrorCode() == BaseBean.SUCCESS) {
                                    mView.showSuccess(App.getAppContext().getString(R.string.collect_success));
                                } else {
                                    mView.showFailed(App.getAppContext().getString(R.string.collect_failed));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFailed(throwable.getMessage());
                            }
                        });

            }
        } else {
            //如果未登录，跳转至登录界面
            ARouter.getInstance().build(PathContainer.LOGIN).navigation();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        mView.showLoading();
        String username = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME);
        String password = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.PASSWORD);

        Observable<BaseBean<UserBean>> observableUser = RetrofitManager.create(ApiService.class).login(username, password);
        Observable<BaseBean<List<BannerBean>>> observableBanner = RetrofitManager.create(ApiService.class).getIndexBanners();
        Observable<BaseBean<ArticleBean>> observableArticle = RetrofitManager.create(ApiService.class).getIndexArticles(mPage);

        //🔥zip操作符合并两个或者多个Observable发射出的数据项，根据指定的函数变换它们，并发射一个新值
        Observable.zip(observableUser, observableBanner, observableArticle,
                (response1, response2, response3) -> {
                    Map<String, Object> objectMap = new HashMap<>();
                    objectMap.put(Constants.USER_KEY, response1);
                    objectMap.put(Constants.BANNER_KEY, response2);
                    objectMap.put(Constants.ARTICLE_KEY, response3);
                    return objectMap;
                }).compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(stringObjectMap -> {
                        BaseBean<UserBean> response = (BaseBean<UserBean>) stringObjectMap.get(Constants.USER_KEY);
                        assert response != null;
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            mView.showSuccess(App.getAppContext().getString(R.string.login_auto_success));
                        } else {
                            mView.showFailed(response.getErrorMsg());
                        }

                        List<BannerBean> banners = (List<BannerBean>) stringObjectMap.get(Constants.BANNER_KEY);
                        ArticleBean articleBean = (ArticleBean) stringObjectMap.get(Constants.ARTICLE_KEY);
                        mView.setBanners(banners);
                        mView.setArticles(articleBean, LoadType.TYPE_REFRESH_SUCCESS);
                    }, throwable -> mView.showFailed(throwable.getMessage()));
    }
}
