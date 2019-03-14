package com.hurley.wanandroid.module.index;



import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.ArticleBean;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:36 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class IndexPresenter extends BasePresenter<IndexContract.View> implements IndexContract.Presenter {

    private int mPage;
    private boolean mIsRefresh;

    @Inject
    public IndexPresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadBanners() {

    }

    @Override
    public void loadArticles() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void collectArticle(int position, ArticleBean articleBean) {

    }

    @Override
    public void loadData() {

    }
}
