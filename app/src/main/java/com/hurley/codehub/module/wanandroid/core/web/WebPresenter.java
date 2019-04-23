package com.hurley.codehub.module.wanandroid.core.web;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.hurley.codehub.api.LocalApiService;
import com.hurley.codehub.api.LocalUrlContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.wanandroid.BaseBean;

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

    @Override
    public void collectInsideArticle(int id) {

    }

    @Override
    public void collectOutsideArticle(String title, String author, String link) {

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
        Gson gson = new Gson();
        String strArticle = gson.toJson(article);
        LogUtils.e(strArticle);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(LocalUrlContainer.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalApiService apiService = retrofit.create(LocalApiService.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strArticle);
        Call<BaseBean<Article>> call = apiService.saveArticle(body);
        call.enqueue(new Callback<BaseBean<Article>>() {
            @Override
            public void onResponse(Call<BaseBean<Article>> call, Response<BaseBean<Article>> response) {
                LogUtils.e("成功");
            }

            @Override
            public void onFailure(Call<BaseBean<Article>> call, Throwable t) {
                LogUtils.e(t.getMessage());
            }
        });
    }
}
