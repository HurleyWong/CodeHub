package com.hurley.codehub.module.wanandroid.core.project;


import android.annotation.SuppressLint;

import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ProjectBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:30 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目 Presenter类
 * </pre>
 */
public class ProjectPresent extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    @Inject
    public ProjectPresent() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadProjects() {
        //显示加载进度条
        mView.showLoading();
        RetrofitManager.create(WanAndroidApiService.class)
                .getProject()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(response -> mView.setProjects(response.getData()), throwable -> mView.showFailed(throwable.getMessage()));
    }

    @Override
    public void refresh() {
        loadProjects();
    }
}
