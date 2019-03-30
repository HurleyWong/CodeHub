package com.hurley.codehub.module.wanandroid.core.user.register;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.UserBean;


/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 4:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 注册 Contract类
 * </pre>
 */
public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void registerSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContract.BasePresenter<RegisterContract.View> {
        void register(String username, String password, String repassword);
    }
}
