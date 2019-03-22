package com.hurley.wanandroid.module.user.collect;

import com.hurley.wanandroid.app.LoadType;
import com.hurley.wanandroid.base.BaseContract;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.PageBean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/18 10:57 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 我的收藏 Contract类
 * </pre>
 */
public interface CollectionContract {

    interface View extends BaseContract.BaseView {
        void setCollectionArticle(ArticleBean articleBean, @LoadType.checker int loadType);

        /**
         * 取消收藏文章成功
         * @param position
         */
        void unCollectArticleSuccess(int position);
    }

    interface Presenter extends BaseContract.BasePresenter<CollectionContract.View> {
        /**
         * 加载我收藏的文章
         */
        void loadCollection();

        /**
         * 刷新
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadMore();

        /**
         * 取消收藏文章
         * @param position
         * @param articleBean
         */
        void unCollectArticle(int position, ArticleBean.DatasBean articleBean);
    }
}
