package com.hurley.wanandroid.module.wechat;


import com.hurley.wanandroid.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 6:45 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public interface WechatContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<WechatContract.View> {
    }
}
