package com.hurley.wanandroid.module.user.analysis;

import com.hurley.wanandroid.base.BaseContract;
import com.hurley.wanandroid.bean.ArticleBean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 3:44 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析一月数据 Contract类
 * </pre>
 */
public interface AnalysisMonthContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<AnalysisMonthContract.View> {

        /**
         * 获取一月内所有文章的信息
         */
        void getAllArticlesInMonth();

        void refresh();
    }
}
