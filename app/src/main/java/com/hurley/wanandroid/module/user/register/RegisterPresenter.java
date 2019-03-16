package com.hurley.wanandroid.module.user.register;

import android.annotation.SuppressLint;

import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 4:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 注册 Presenter类
 * </pre>
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Inject
    public RegisterPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void register(String username, String password, String repassword) {
        RetrofitManager.create(ApiService.class)
                .register(username, password, repassword)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.registerSuccess(response.getData());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> mView.showFailed(throwable.getMessage()));
    }
}
