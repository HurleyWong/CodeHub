package com.hurley.wanandroid.module.wechat;


import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:45 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 公众号 Presenter类
 * </pre>
 */
public class WechatPresenter extends BasePresenter<WechatContract.View> implements WechatContract.Presenter{

    @Inject
    public WechatPresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadWxAccounts() {
        //显示加载进度条
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getWxAccounts()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.setWxAccounts(response.getData());
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> mView.showFailed(throwable.getMessage()));
    }

    @Override
    public void refresh() {
        //刷新，重新加载数据
        loadWxAccounts();
    }
}
