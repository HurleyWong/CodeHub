package com.hurley.wanandroid.module.user.login;



import com.hurley.wanandroid.base.BasePresenter;

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

    @Override
    public void login(String username, String password) {

    }
}
