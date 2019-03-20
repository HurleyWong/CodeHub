package com.hurley.wanandroid.module.main;

import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 11:26 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public interface WebContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<WebContract.View> {

        void collectInsideArticle(int id);

        void collectOutsideArticle(String title, String author, String link);

        boolean getAutoCacheState();

        boolean getNoImageState();
    }
}
