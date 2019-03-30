package com.hurley.codehub.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:02 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Contract基类
 * </pre>
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {

        /**
         * 显示进度中
         */
        void showLoading();

        /**
         * 隐藏进度
         */
        void hideLoading();

        /**
         * 显示请求成功
         * @param message
         */
        void showSuccess(String message);

        /**
         * 失败重试
         * @param message
         */
        void showFailed(String message);

        /**
         * 显示当前网络不可用
         */
        void showNoNet();

        /**
         * 重试
         */
        void onRetry();

        /**
         * 设置夜间模式
         * @param isNight
         */
        void useNightMode(boolean isNight);

        /**
         * 绑定生命周期
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }
}
