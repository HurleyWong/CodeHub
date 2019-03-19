package com.hurley.wanandroid.base;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:02 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Presenter基类
 * </pre>
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
