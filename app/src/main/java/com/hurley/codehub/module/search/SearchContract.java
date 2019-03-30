package com.hurley.codehub.module.search;

import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.ArticleBean;

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

        void setSearchArticles(ArticleBean articleBean, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<SearchContract.View> {

        void loadSearchArticles(String key);

        void refresh();

        void loadMore();
    }
}
