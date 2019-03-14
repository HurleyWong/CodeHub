package com.hurley.wanandroid.module.system;



import com.hurley.wanandroid.base.BaseContract;
import com.hurley.wanandroid.bean.SystemBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:39 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public interface SystemContract {

    interface View extends BaseContract.BaseView {
        void setSystems(List<SystemBean> knowledgeSystems);
    }

    interface Presenter extends BaseContract.BasePresenter<SystemContract.View> {
        void loadSystems();

        void refresh();
    }
}
