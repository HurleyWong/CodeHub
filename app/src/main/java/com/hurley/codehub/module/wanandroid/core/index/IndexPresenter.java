package com.hurley.codehub.module.wanandroid.core.index;


import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.hurley.codehub.R;
import com.hurley.codehub.api.LocalApiService;
import com.hurley.codehub.api.LocalUrlContainer;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.api.WanAndroidUrlContainer;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.local.Chapter;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;
import com.hurley.codehub.bean.wanandroid.UserBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
     * 文章id
     */
    private int mCid;
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
        RetrofitManager.create(WanAndroidApiService.class)
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

    /**
     * 加载文章列表
     */
    @SuppressLint("CheckResult")
    @Override
    public void loadArticles() {
        RetrofitManager.create(WanAndroidApiService.class)
                .getIndexArticles(mPage)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setArticles(response.getData(), loadType);
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                    mView.setArticles(new ArticleBean(), loadType);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadTopArticles() {
        RetrofitManager.create(WanAndroidApiService.class)
                .getTopArticles()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.setTopArticles(response.getData());
                        LogUtils.e(response.getErrorMsg());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadRecommendArticles(int cid) {
        RetrofitManager.create(WanAndroidApiService.class)
                .getSystemArticles(mPage, cid)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    mView.setRecommendArticles(response.getData());
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getRecommendChapter(List<Integer> list) {
        RetrofitManager.createLocal(LocalApiService.class)
                .getChapterId(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID))
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(chapter -> {
                    for (int i = 0; i < chapter.getData().size(); i++) {
                        list.add(chapter.getData().get(i).getChapterid());
                        LogUtils.e("推荐的文章体系id是：" + chapter.getData().get(i).getChapterid());
                        loadRecommendArticles(chapter.getData().get(i).getChapterid());
                    }
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }

    @Override
    public void refreshRecommendChapter() {
        List<Integer> list = new ArrayList<>();
        getRecommendChapter(list);
    }

    @Override
    public void refresh() {
        //页数设置为首页
        mPage = 0;
        isRefresh = true;
        loadBanners();
        loadArticles();
        List<Integer> list = new ArrayList<>();
        getRecommendChapter(list);
    }

    @Override
    public void loadMore() {
        mPage++;
        isRefresh = false;
        loadArticles();
    }

    /**
     * 收藏文章或者取消收藏文章
     *
     * @param position
     * @param articleBean
     */
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

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        String username = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME);
        String password = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.PASSWORD);

        Observable<BaseBean<UserBean>> observableUser = RetrofitManager.create(WanAndroidApiService.class).login(username, password);
        Observable<BaseBean<List<BannerBean>>> observableBanner = RetrofitManager.create(WanAndroidApiService.class).getIndexBanners();
        Observable<BaseBean<ArticleBean>> observableArticle = RetrofitManager.create(WanAndroidApiService.class).getIndexArticles(mPage);

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
