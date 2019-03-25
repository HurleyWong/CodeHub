package com.hurley.wanandroid.module.user.analysis;

import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 3:17 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析一周数据 Contract类
 * </pre>
 */
public interface AnalysisWeekContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<AnalysisWeekContract.View> {

        /**
         * 获取一周内所有文章的信息
         */
        void getAllArticlesInWeek();
    }

}
