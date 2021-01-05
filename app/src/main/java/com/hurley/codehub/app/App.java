package com.hurley.codehub.app;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

import com.facebook.stetho.Stetho;
import com.hjq.language.LanguagesManager;
import com.hjq.toast.ToastUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.dao.DaoSession;
import com.hurley.codehub.di.component.ApplicationComponent;
import com.hurley.codehub.di.component.DaggerApplicationComponent;
import com.hurley.codehub.di.module.ApplicationModule;
import com.hurley.codehub.module.wanandroid.core.main.HomeActivity;
import com.hurley.codehub.util.ConfigUtils;
import com.kongzue.dialog.v2.DialogSettings;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import org.litepal.LitePal;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import me.yokeyword.fragmentation.Fragmentation;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_IOS;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 4:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 该项目的Application基类
 * </pre>
 */
public class App extends MultiDexApplication {

    private static App instance;
    private ApplicationComponent mApplicationComponent;
    private DaoSession mDaoSession;

    // static代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
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

        // 初始化LitePal
        LitePal.initialize(this);

        // 初始化国家化框架
        LanguagesManager.init(this);

        /**
         * 必须在Application的onCreate方法中执行BGASwipeBackHelper.init来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，则把该界面里比较特殊的View的class添加到该集合中，例如WebView和SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
        // 初始化工具类
        Utils.init(this);
        // 初始化Toast工具类
        ToastUtils.init(this);
        // 初始化路由
        initARouter();

        // 初始化Fragmentation
        Fragmentation.builder()
                // BUBBLE：显示悬浮球 | SHAKE：摇一摇换出悬浮球 | NONE：隐藏悬浮球
                .stackViewMode(Fragmentation.NONE)
                .debug(ConfigUtils.DEBUG)
                .install();

        // 初始化Stetho
        if (ConfigUtils.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        // 初始化全局Dialog风格样式
        DialogSettings.style = STYLE_IOS;
        DialogSettings.use_blur = true;

        // 自动初始化开关
        // true表示app启动初始化升级模块，false不会自动初始化
        Beta.autoInit = true;
        // 自动检查更新开关
        // true表示初始化时自动检查升级，false表示不会自动检查升级
        Beta.autoCheckUpgrade = true;
        // 升级检查周期设置
        // 设置升级检查周期为60s（默认检查周期为0s）
        Beta.upgradeCheckPeriod = 60 * 1000;
        // 延迟初始化
        // 设置启动延时为1s（默认延时为3s），APP启动1s后初始化SDK，避免影响APP启动速度
        Beta.initDelay = 1 * 1000;
        // 设置通知栏内大图标
        Beta.largeIconId = R.mipmap.ic_launcher;
        // 设置更新弹窗默认展示的banner
        Beta.defaultBannerId = R.mipmap.ic_launcher;
        // 设置SD卡的Download为更新资源存储目录
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        // 设置开启显示打断策略
        Beta.showInterruptedStrategy = true;
        // 添加可显示弹窗的Activity
        Beta.canShowUpgradeActs.add(HomeActivity.class);
        // 设置自定义升级对话框UI布局
        // Beta.upgradeDialogLayoutId = R.layout.dialog_upgrade;
        // 设置自定义tip弹窗UI布局
        // Beta.tipsDialogLayoutId = R.layout.dialog_tips;
        // 设置是否显示消息通知
        Beta.enableNotification = true;
        // 设置Wifi下是否自动下载
        Beta.autoDownloadOnWifi = false;
        // 设置是否显示弹窗中的apk消息
        Beta.canShowApkInfo = true;
        // 初始化统一接口
        Bugly.init(getAppContext(), Constants.APP_ID, false);
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (ConfigUtils.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式（如果在InstantRun模式下运行，必须开启调试模式。线上版本需要关闭，否则有安全风险）
            ARouter.openDebug();
        }
        // 推荐在Application中初始化
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesManager.attach(base));
        MultiDex.install(this);
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
