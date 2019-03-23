package com.hurley.wanandroid.module.user.analysis;

import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/23 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析 Contract类
 * </pre>
 */
public interface AnalysisContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<AnalysisContract.View> {

        /**
         * 获取几天前的所有文章信息
         */
        void getNearbyArticles();

        /**
         * 获取所有文章的信息
         */
        void getAllArticles();
    }
}
