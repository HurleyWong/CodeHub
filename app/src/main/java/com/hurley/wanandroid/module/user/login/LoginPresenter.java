package com.hurley.wanandroid.module.user.login;



import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

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
                    if (response.getErrorCode() == 0) {
                        mView.loginSuccess(response.getData());
                    } else {
                        mView.showFaild(response.getErrorMsg());
                    }
                }, throwable -> {
                    mView.showFaild(throwable.getMessage());
                });
    }
}
