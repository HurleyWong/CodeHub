package com.hurley.wanandroid.module.user.analysis;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/23 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析 Presenter类
 * </pre>
 */
public class AnalysisPresenter extends BasePresenter<AnalysisContract.View> implements AnalysisContract.Presenter{

    @Inject
    public AnalysisPresenter() {}

}
