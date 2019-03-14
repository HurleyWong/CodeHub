package com.hurley.wanandroid.module.index;


import com.hurley.wanandroid.app.LoadType;
import com.hurley.wanandroid.base.BaseContract;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.BannerBean;
import com.hurley.wanandroid.bean.PageBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public interface IndexContract {

    interface View extends BaseContract.BaseView {
        void setBanners(List<BannerBean> banners);

        void setArticles(PageBean pageBean, @LoadType.checker int loadType);

        void collectArticleSuccess(int position, ArticleBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadBanners();

        void loadArticles();

        void refresh();

        void loadMore();

        void collectArticle(int position, ArticleBean articleBean);

        void loadData();
    }
}
