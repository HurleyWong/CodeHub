package com.hurley.wanandroid.module.main;

import com.hurley.wanandroid.base.BasePresenter;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 11:27 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class WebPresenter extends BasePresenter<WebContract.View> implements WebContract.Presenter {

    @Inject
    public WebPresenter() {}

    @Override
    public void collectInsideArticle(int id) {

    }

    @Override
    public void collectOutsideArticle(String title, String author, String link) {

    }
}
