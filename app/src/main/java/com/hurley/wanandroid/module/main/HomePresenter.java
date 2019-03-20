package com.hurley.wanandroid.module.main;

import android.annotation.SuppressLint;

import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.event.NightModeEvent;
import com.hurley.wanandroid.net.callback.RxBus;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 5:37 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {}

    @SuppressLint("CheckResult")
    @Override
    public void setNightMode(boolean isNight) {
        RxBus.getInstance().toFlowable(NightModeEvent.class)
                .subscribe(nightModeEvent -> mView.useNightMode(isNight));
    }
}
