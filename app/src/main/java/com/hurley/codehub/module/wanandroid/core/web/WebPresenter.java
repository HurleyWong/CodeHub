package com.hurley.codehub.module.wanandroid.core.web;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.hurley.codehub.R;
import com.hurley.codehub.api.LocalApiService;
import com.hurley.codehub.api.LocalUrlContainer;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 11:27 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class WebPresenter extends BasePresenter<WebContract.View> implements WebContract.Presenter {

    @Inject
    public WebPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void collectInsideArticle(int id) {
        //如果已登录
        if (SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS)) {
            RetrofitManager.create(WanAndroidApiService.class)
                    .collectInsideArticle(id)
                    .compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(response -> {
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            mView.showSuccess(App.getAppContext().getString(R.string.collect_success));
                        } else {
                            mView.showFailed(App.getAppContext().getString(R.string.collect_failed));
                        }
                    }, throwable -> LogUtils.e(throwable.getMessage()));
        } else {
            //如果未登录，跳转至登录界面
            ARouter.getInstance().build(PathContainer.LOGIN).navigation();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void collectOutsideArticle(String title, String author, String link) {
        //如果已登录
        if (SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS)) {
            RetrofitManager.create(WanAndroidApiService.class)
                    .collectOutsideArticle(title, author, link)
                    .compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(response -> {
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            mView.showSuccess(App.getAppContext().getString(R.string.collect_success));
                        } else {
                            mView.showFailed(App.getAppContext().getString(R.string.collect_failed));
                        }
                    }, throwable -> LogUtils.e(throwable.getMessage()));
        } else {
            //如果未登录，跳转至登录界面
            ARouter.getInstance().build(PathContainer.LOGIN).navigation();
        }
    }

    @Override
    public boolean getAutoCacheState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.AUTO_CACHE);
    }

    @Override
    public boolean getNoImageState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.NO_IMAGE);
    }

    @Override
    public void saveArticles(Article article) {
//        Gson gson = new Gson();
//        String strArticle = gson.toJson(article);
//        LogUtils.e(strArticle);
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(LocalUrlContainer.baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        LocalApiService apiService = retrofit.create(LocalApiService.class);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strArticle);
//        Call<BaseBean<Article>> call = apiService.saveArticle(body);
//        call.enqueue(new Callback<BaseBean<Article>>() {
//            @Override
//            public void onResponse(Call<BaseBean<Article>> call, Response<BaseBean<Article>> response) {
//                LogUtils.e("成功");
//            }
//
//            @Override
//            public void onFailure(Call<BaseBean<Article>> call, Throwable t) {
//                LogUtils.e(t.getMessage());
//            }
//        });
    }
}
