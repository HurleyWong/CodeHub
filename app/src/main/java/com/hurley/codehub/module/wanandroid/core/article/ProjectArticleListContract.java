package com.hurley.codehub.module.wanandroid.core.article;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.ArticleBean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/21 11:09 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目文章列表 Contract类
 * </pre>
 */
public interface ProjectArticleListContract {

    interface View extends BaseContract.BaseView {

        void setProjectArticles(ArticleBean articleBean, int type);

        /**
         * 在文章列表页面成功收藏文章
         * @param position
         * @param articleBean
         */
        void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean);
    }

    interface Presenter extends BaseContract.BasePresenter<ProjectArticleListContract.View> {
        void loadProjectArticles(int id);

        void refresh();

        void loadMore();

        void collectArticle(int position, ArticleBean.DatasBean articleBean);
    }
}
