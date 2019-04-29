package com.hurley.codehub.module.wanandroid.core.index;


import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;
import com.hurley.codehub.bean.wanandroid.TopArticleBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首页 Contract类
 * </pre>
 */
public interface IndexContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示首页轮播图
         *
         * @param banners
         */
        void setBanners(List<BannerBean> banners);

        /**
         * 显示首页文章
         *
         * @param articleBean
         * @param loadType
         */
        void setArticles(ArticleBean articleBean, @LoadType.checker int loadType);

        /**
         * 显示置顶文章
         *
         * @param articles
         */
        void setTopArticles(List<ArticleBean.DatasBean> articles);

        /**
         * 收藏文章成功
         *
         * @param position
         * @param articleBean
         */
        void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean);

        /**
         * 显示推荐文章
         *
         * @param articleBean
         */
        void setRecommendArticles(ArticleBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        /**
         * 加载首页轮播图
         */
        void loadBanners();

        /**
         * 加载首页文章
         */
        void loadArticles();

        /**
         * 加载置顶文章
         */
        void loadTopArticles();

        /**
         * 加载推荐文章
         *
         * @param cid
         */
        void loadRecommendArticles(int cid);

        void getRecommendChapter(List<Integer> list);

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

        /**
         * 加载所有数据（用户信息、轮播图、文章）
         */
        void loadData();
    }
}
