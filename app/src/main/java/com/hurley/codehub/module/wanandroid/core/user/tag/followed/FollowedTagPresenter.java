package com.hurley.codehub.module.wanandroid.core.user.tag.followed;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.hurley.codehub.api.LocalApiService;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-30 15:16
 *      github  : https://github.com/HurleyJames
 *      desc    : 已关注标签 Presenter类
 * </pre>
 */
public class FollowedTagPresenter extends BasePresenter<FollowedTagContract.View> implements FollowedTagContract.Presenter {

    @Inject
    public FollowedTagPresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadFollowedTag(int userId) {
        RetrofitManager.createLocal(LocalApiService.class)
                .getFollowedTag(userId)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    if (response.getErrorCode() == BaseBean.SUCCESS) {
                        mView.setFollowedTag();
                    } else {
                        mView.showFailed(response.getErrorMsg());
                    }
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }
}
