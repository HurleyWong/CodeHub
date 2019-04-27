package com.hurley.codehub.module.wanandroid.core.user.setting;

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

        /**
         * 获得是否自动缓存
         *
         * @return
         */
        boolean getAutoCacheState();

        /**
         * 设置自动缓存
         *
         * @param b
         */
        void setAutoCacheState(boolean b);

        /**
         * 获得是否无图模式
         *
         * @return
         */
        boolean getNoImageState();

        /**
         * 设置无图模式
         *
         * @param b
         */
        void setNoImageState(boolean b);

        /**
         * 获得是否是夜间模式
         *
         * @param b
         */
        void setNightModeState(boolean b);

        /**
         * 设置夜间模式
         *
         * @return
         */
        boolean getNightModeState();

        /**
         * 获得反馈
         *
         * @param context
         * @param title
         */
        void feedback(Context context, String title);

    }
}
