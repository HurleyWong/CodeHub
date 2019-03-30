package com.hurley.codehub.module.web;

import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BasePresenter;

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

    @Override
    public boolean getAutoCacheState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.AUTO_CACHE);
    }

    @Override
    public boolean getNoImageState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.NO_IMAGE);
    }
}
