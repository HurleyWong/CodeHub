package com.hurley.codehub.module.article;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.ArticleBean;

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

        void setWxArticles(ArticleBean articleBean, int type);

        void collectWxArticleSuccess(int position, ArticleBean.DatasBean articleBean);
    }

    interface Presenter extends BaseContract.BasePresenter<WxArticleListContract.View> {

        void loadWxArticles(int id);

        void refresh();

        void loadMore();

        void collectWxArticle(int position, ArticleBean.DatasBean articleBean);
    }
}
