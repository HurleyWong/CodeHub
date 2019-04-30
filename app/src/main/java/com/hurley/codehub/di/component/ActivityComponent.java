package com.hurley.codehub.di.component;

import android.app.Activity;
import android.content.Context;


import com.hurley.codehub.di.module.ActivityModule;
import com.hurley.codehub.di.scope.ActivityScope;
import com.hurley.codehub.di.scope.ContextLifeCycle;
import com.hurley.codehub.module.wanandroid.core.main.HomeActivity;
import com.hurley.codehub.module.wanandroid.core.search.SearchActivity;
import com.hurley.codehub.module.wanandroid.core.user.analysis.LoadingDataActivity;
import com.hurley.codehub.module.wanandroid.core.user.tag.TagActivity;
import com.hurley.codehub.module.wanandroid.core.web.WebActivity;
import com.hurley.codehub.module.wanandroid.core.user.collect.CollectionActivity;
import com.hurley.codehub.module.wanandroid.core.user.login.LoginActivity;
import com.hurley.codehub.module.wanandroid.core.user.register.RegisterActivity;
import com.hurley.codehub.module.wanandroid.core.user.setting.SettingActivity;

import dagger.Component;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:54 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Activity注入器类
 * </pre>
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLifeCycle("Activity")
    Context getActivityContext();

    @ContextLifeCycle("App")
    Context getAppContext();

    Activity getActivity();

    //🔥定义inject方法，传入需要注入依赖的目标类

    void inject(HomeActivity activity);

    void inject(WebActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(CollectionActivity activity);

    void inject(SearchActivity activity);

    void inject(LoadingDataActivity activity);

    void inject(SettingActivity activity);

}
