package com.hurley.codehub.module.wanandroid.core.system;


import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.SystemBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:39 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 体系 Contract类
 * </pre>
 */
public interface SystemContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示体系数据
         *
         * @param systems
         */
        void setSystems(List<SystemBean> systems);
    }

    interface Presenter extends BaseContract.BasePresenter<SystemContract.View> {

        /**
         * 加载体系数据
         */
        void loadSystems();

        /**
         * 刷新
         */
        void refresh();

        /**
         * 将点击的体系保存至自己的数据库
         *
         * @param superChapterName
         */
        void saveSystems(String superChapterName);
    }
}
