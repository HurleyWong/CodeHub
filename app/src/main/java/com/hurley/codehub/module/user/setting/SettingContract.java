package com.hurley.codehub.module.user.setting;

import android.content.Context;

import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/19 9:46 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 设置 Contract类
 * </pre>
 */
public interface SettingContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<SettingContract.View> {

        boolean getAutoCacheState();

        void setAutoCacheState(boolean b);

        boolean getNoImageState();

        void setNoImageState(boolean b);

        void setNightModeState(boolean b);

        boolean getNightModeState();

        void feedback(Context context, String title);

    }
}
