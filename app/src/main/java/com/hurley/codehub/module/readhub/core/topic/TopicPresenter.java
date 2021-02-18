package com.hurley.codehub.module.readhub.core.topic;

import android.annotation.SuppressLint;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hurley.codehub.api.ReadhubApiService;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;
import com.hurley.codehub.util.ReplaceUtils;


import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:35
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter {

    /**
     * 是否刷新
     */
    private boolean isRefresh;

    @Inject
    public TopicPresenter() {
        this.isRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadTopicContent() {
        RetrofitManager.createReadHub(ReadhubApiService.class)
                .getTopic(0, Constants.PAGE_SIZE)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                    mView.setTopicContent(response.getData(), loadType);
                    String date = TimeUtils.date2String(ReplaceUtils.replaceDate(response.getData().get(0).getPublishDate()));
                    LogUtils.e(TimeUtils.getFriendlyTimeSpanByNow(date));
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadMore(int lastCursor) {
        RetrofitManager.createReadHub(ReadhubApiService.class)
                .getTopic(lastCursor, Constants.PAGE_SIZE)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> {
                    int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                    mView.setTopicContent(response.getData(), loadType);
                    String date = TimeUtils.date2String(ReplaceUtils.replaceDate(response.getData().get(0).getPublishDate()));
                    LogUtils.e(TimeUtils.getFriendlyTimeSpanByNow(date));
                }, throwable -> LogUtils.e(throwable.getMessage()));
    }

    @Override
    public void refresh() {

    }
}