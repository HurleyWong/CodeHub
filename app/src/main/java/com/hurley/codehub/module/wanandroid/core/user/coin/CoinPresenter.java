package com.hurley.codehub.module.wanandroid.core.user.coin;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-09-11 14:25
 *      github  : https://github.com/HurleyJames
 *      desc    : 积分 Presenter类
 * </pre>
 */
public class CoinPresenter extends BasePresenter<CoinContract.View> implements CoinContract.Presenter {

    /**
     * 页码
     */
    private int mPage = 1;

    /**
     * 是否刷新
     */
    private boolean isRefresh;

    @Inject
    public CoinPresenter() {
        isRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getUserCoin() {
        if (isRefresh) {
            mView.showLoading();
        }
        RetrofitManager.create(WanAndroidApiService.class)
                .getUserCoin()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    mView.showUserCoin(response.getData().getCoinCount(), response.getData().getRank());
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCoinRank() {
        if (isRefresh) {
            mView.showLoading();
        }
        RetrofitManager.create(WanAndroidApiService.class)
                .getCoinRank(mPage)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    mView.showCoinRank(response.getData().getDatas());
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }


}
