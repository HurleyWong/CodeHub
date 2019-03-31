package com.hurley.codehub.module.wanandroid.core.main;

import android.annotation.SuppressLint;

import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.module.wanandroid.event.NightModeEvent;
import com.hurley.codehub.net.callback.RxBus;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 5:37 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 主页 Presenter类
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
