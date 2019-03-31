package com.hurley.codehub.module.readhub.core.main;

import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 3:14 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 主页 Contract类
 * </pre>
 */
public interface MainContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<MainContract.View> {

    }
}
