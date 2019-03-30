package com.hurley.codehub.module.user.login;



import android.annotation.SuppressLint;

import com.hurley.codehub.api.ApiService;
import com.hurley.codehub.base.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/2 1:52 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 登录 Presenter类
 * </pre>
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void login(String username, String password) {
        RetrofitManager.create(ApiService.class)
                .login(username, password)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.loginSuccess(response.getData());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> mView.showFailed(throwable.getMessage()));
    }
}
