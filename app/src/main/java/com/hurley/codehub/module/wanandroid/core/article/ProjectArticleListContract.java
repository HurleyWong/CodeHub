package com.hurley.codehub.module.wanandroid.core.article;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.ArticleBean;

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

        /**
         * 显示项目文章
         *
         * @param articleBean
         * @param type
         */
        void setProjectArticles(ArticleBean articleBean, int type);

        /**
         * 在文章列表页面成功收藏文章
         *
         * @param position
         * @param articleBean
         */
        void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean);
    }

    interface Presenter extends BaseContract.BasePresenter<ProjectArticleListContract.View> {

        /**
         * 加载项目文章
         *
         * @param id
         */
        void loadProjectArticles(int id);

        /**
         * 刷新
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadMore();

        /**
         * 收藏文章
         *
         * @param position
         * @param articleBean
         */
        void collectArticle(int position, ArticleBean.DatasBean articleBean);
    }
}
