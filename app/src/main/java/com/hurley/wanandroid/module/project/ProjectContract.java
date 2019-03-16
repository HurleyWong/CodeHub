package com.hurley.wanandroid.module.project;


import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:29 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目 Contract类
 * </pre>
 */
public interface ProjectContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<ProjectContract.View> {
    }
}
