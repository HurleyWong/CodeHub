package com.hurley.codehub.module.wanandroid.core.user;


import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:28 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 用户 Contract类
 * </pre>
 *
 */
public interface UserContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<UserContract.View> {
    }
}
