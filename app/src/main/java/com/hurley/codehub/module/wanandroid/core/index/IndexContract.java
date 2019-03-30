package com.hurley.codehub.module.wanandroid.core.index;


import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;

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
        void setBanners(List<BannerBean> banners);

        void setArticles(ArticleBean articleBean, @LoadType.checker int loadType);

        void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadBanners();

        void loadArticles();

        void getAllArticles();

        void refresh();

        void loadMore();

        void collectArticle(int position, ArticleBean.DatasBean articleBean);

        void loadData();
    }
}
