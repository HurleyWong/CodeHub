package com.hurley.codehub.module.wanandroid.core.user.tag.all;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.hurley.codehub.api.LocalApiService;
import com.hurley.codehub.api.LocalUrlContainer;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.local.UserTag;
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
 *      date    : 2019-04-30 15:50
 *      github  : https://github.com/HurleyJames
 *      desc    : 全部标签 Presenter类
 * </pre>
 */
public class AllTagPresenter extends BasePresenter<AllTagContract.View> implements AllTagContract.Presenter{

    @Inject
    public AllTagPresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadFollowedTag(int userid) {
        RetrofitManager.createLocal(LocalApiService.class)
                .getFollowedTag(userid)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.setFollowedTag(response.getData());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }

    @Override
    public void saveTag(UserTag userTag) {
        Gson gson = new Gson();
        String strTag = gson.toJson(userTag);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalUrlContainer.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalApiService apiService = retrofit.create(LocalApiService.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strTag);
        Call<BaseBean<UserTag>> call = apiService.saveTag(body);
        call.enqueue(new Callback<BaseBean<UserTag>>() {
            @Override
            public void onResponse(Call<BaseBean<UserTag>> call, Response<BaseBean<UserTag>> response) {
                LogUtils.e("将关注的标签发送到后台成功");
            }

            @Override
            public void onFailure(Call<BaseBean<UserTag>> call, Throwable t) {
                LogUtils.e(t.getMessage());
            }
        });
    }

    @Override
    public void deleteTag(UserTag userTag) {
        Gson gson = new Gson();
        String strTag = gson.toJson(userTag);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalUrlContainer.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalApiService apiService = retrofit.create(LocalApiService.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strTag);
        Call<BaseBean<UserTag>> call = apiService.delTag(body);
        call.enqueue(new Callback<BaseBean<UserTag>>() {
            @Override
            public void onResponse(Call<BaseBean<UserTag>> call, Response<BaseBean<UserTag>> response) {
                LogUtils.e("将取消关注的标签发送到后台成功");
            }

            @Override
            public void onFailure(Call<BaseBean<UserTag>> call, Throwable t) {
                LogUtils.e(t.getMessage());
            }
        });
    }
}
