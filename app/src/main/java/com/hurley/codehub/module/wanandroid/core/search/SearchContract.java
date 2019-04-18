package com.hurley.codehub.module.wanandroid.core.search;

import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.ArticleBean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/27 3:29 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 搜索 Contract类
 * </pre>
 */
public interface SearchContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示搜索文章结果
         *
         * @param articleBean
         * @param loadType
         */
        void setSearchArticles(ArticleBean articleBean, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<SearchContract.View> {

        /**
         * 加载搜索结果
         *
         * @param key
         */
        void loadSearchArticles(String key);

        /**
         * 刷新
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadMore();
    }
}
