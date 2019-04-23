package com.hurley.codehub.module.wanandroid.core.web;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.local.Article;

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

        /**
         * 收藏站内文章
         *
         * @param id
         */
        void collectInsideArticle(int id);

        /**
         * 收藏站外文章
         *
         * @param title
         * @param author
         * @param link
         */
        void collectOutsideArticle(String title, String author, String link);

        /**
         * 设置是否自动缓存
         *
         * @return
         */
        boolean getAutoCacheState();

        /**
         * 设置是否无图模式
         *
         * @return
         */
        boolean getNoImageState();

        /**
         * 将点击的文章保存至自己的数据库
         *
         * @param article
         */
        void saveArticles(Article article);
    }
}
