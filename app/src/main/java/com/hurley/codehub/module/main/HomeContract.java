package com.hurley.codehub.module.main;

import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 5:35 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 主页 Contract类
 * </pre>
 */
public interface HomeContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<HomeContract.View> {

        void setNightMode(boolean isNight);
    }
}
