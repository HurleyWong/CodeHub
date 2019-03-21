package com.hurley.wanandroid.module.project;


import com.hurley.wanandroid.base.BaseContract;
import com.hurley.wanandroid.bean.ProjectBean;

import java.util.List;

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
        void setProjects(List<ProjectBean> projects);
    }

    interface Presenter extends BaseContract.BasePresenter<ProjectContract.View> {

        void loadProjects();

        void refresh();
    }
}
