package com.hurley.wanandroid.module.user;


import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:28 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 *
 */
public interface UserContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<UserContract.View> {
    }
}
