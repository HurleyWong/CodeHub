package com.hurley.wanandroid.module.project;



import android.annotation.SuppressLint;

import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.bean.ProjectBean;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

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
        RetrofitManager.create(ApiService.class)
                .getProject()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<BaseBean<List<ProjectBean>>>() {
                    @Override
                    public void accept(BaseBean<List<ProjectBean>> response) throws Exception {
                        mView.setProjects(response.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void refresh() {
        loadProjects();
    }
}
