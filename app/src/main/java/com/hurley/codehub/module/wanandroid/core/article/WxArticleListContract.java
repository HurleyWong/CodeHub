package com.hurley.codehub.module.wanandroid.core.article;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.ArticleBean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 7:11 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 公众号文章列表 Contract类
 * </pre>
 */
public interface WxArticleListContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示微信公众号文章
         *
         * @param articleBean
         * @param type
         */
        void setWxArticles(ArticleBean articleBean, int type);

        /**
         * 显示收藏文章成功
         *
         * @param position
         * @param articleBean
         */
        void collectWxArticleSuccess(int position, ArticleBean.DatasBean articleBean);
    }

    interface Presenter extends BaseContract.BasePresenter<WxArticleListContract.View> {

        /**
         * 加载微信公众号文章
         *
         * @param id
         */
        void loadWxArticles(int id);

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
        void collectWxArticle(int position, ArticleBean.DatasBean articleBean);
    }
}
