package com.hurley.codehub.module.wanandroid.core.system;



import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.SystemBean;

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
        void setSystems(List<SystemBean> systems);
    }

    interface Presenter extends BaseContract.BasePresenter<SystemContract.View> {
        void loadSystems();

        void refresh();
    }
}
