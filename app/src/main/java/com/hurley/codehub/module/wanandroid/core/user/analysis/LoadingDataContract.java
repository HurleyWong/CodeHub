package com.hurley.codehub.module.wanandroid.core.user.analysis;

import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 5:51 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public interface LoadingDataContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<LoadingDataContract.View> {

        /**
         * 获取所有文章的信息
         */
        void getAllArticles();
    }
}
