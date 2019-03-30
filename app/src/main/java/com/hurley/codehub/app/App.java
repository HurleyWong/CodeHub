package com.hurley.codehub.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

import com.facebook.stetho.Stetho;
import com.hjq.toast.ToastUtils;
import com.hurley.codehub.dao.DaoSession;
import com.hurley.codehub.di.component.ApplicationComponent;
import com.hurley.codehub.di.component.DaggerApplicationComponent;
import com.hurley.codehub.di.module.ApplicationModule;
import com.hurley.codehub.utils.ConfigUtil;

import org.litepal.LitePal;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 4:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 该项目的Application基类
 * </pre>
 */
public class App extends Application {

    private static App instance;
    private ApplicationComponent mApplicationComponent;
    private DaoSession mDaoSession;

    //static代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initApplicationComponent();

        //初始化LitePal
        LitePal.initialize(this);

        /**
         * 必须在Application的onCreate方法中执行BGASwipeBackHelper.init来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，则把该界面里比较特殊的View的class添加到该集合中，例如WebView和SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
        //初始化工具类
        Utils.init(this);
        //初始化Toast工具类
        ToastUtils.init(this);
        //初始化路由
        initARouter();

        //初始化Fragmentation
        Fragmentation.builder()
                //BUBBLE：显示悬浮球 | SHAKE：摇一摇换出悬浮球 | NONE：隐藏悬浮球
                .stackViewMode(Fragmentation.NONE)
                .debug(ConfigUtil.DEBUG)
                .install();

        //初始化Stetho
        if (ConfigUtil.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (ConfigUtil.DEBUG) {
            //打印日志
            ARouter.openLog();
            //开启调试模式（如果在InstantRun模式下运行，必须开启调试模式。线上版本需要关闭，否则有安全风险）
            ARouter.openDebug();
        }
        //推荐在Application中初始化
        ARouter.init(this);
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}