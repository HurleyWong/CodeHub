package com.hurley.wanandroid.module.index;



import android.annotation.SuppressLint;

import com.blankj.utilcode.util.SPUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.ApiService;
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
 *      desc    :
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
                    mView.setArticles(new PageBean(), loadType);
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

    @Override
    public void collectArticle(int position, ArticleBean articleBean) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        mView.showLoading();
        String username = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME);
        String password = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.PASSWORD);

        Observable<BaseBean<UserBean>> observableUser = RetrofitManager.create(ApiService.class).login(username, password);
        Observable<BaseBean<List<BannerBean>>> observableBanner = RetrofitManager.create(ApiService.class).getIndexBanners();
        Observable<BaseBean<PageBean<ArticleBean>>> observableArticle = RetrofitManager.create(ApiService.class).getIndexArticles(mPage);

        Observable.zip(observableUser, observableBanner, observableArticle,
                new Function3<BaseBean<UserBean>, BaseBean<List<BannerBean>>, BaseBean<PageBean<ArticleBean>>, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> apply(BaseBean<UserBean> response1, BaseBean<List<BannerBean>> response2, BaseBean<PageBean<ArticleBean>> response3) throws Exception {
                        Map<String, Object> objectMap = new HashMap<>();
                        objectMap.put(Constants.USER_KEY, response1);
                        objectMap.put(Constants.BANNER_KEY, response2);
                        objectMap.put(Constants.ARTICLE_KEY, response3);
                        return objectMap;
                    }
                }).compose(RxSchedulers.<Map<String, Object>>applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(new Consumer<Map<String, Object>>() {
                        @Override
                        public void accept(Map<String, Object> stringObjectMap) throws Exception {
                            BaseBean<UserBean> response = (BaseBean<UserBean>) stringObjectMap.get(Constants.USER_KEY);
                            if (response.getErrorCode() == BaseBean.SUCCESS) {
                                mView.showSuccess(App.getAppContext().getString(R.string.login_auto_success));
                            } else {
                                mView.showFailed(response.getErrorMsg());
                            }

                            List<BannerBean> banners = (List<BannerBean>) stringObjectMap.get(Constants.BANNER_KEY);
                            PageBean<ArticleBean> article = (PageBean<ArticleBean>) stringObjectMap.get(Constants.ARTICLE_KEY);
                            mView.setBanners(banners);
                            mView.setArticles(article, LoadType.TYPE_REFRESH_SUCCESS);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.showFailed(throwable.getMessage());
                        }
                    });
    }
}
