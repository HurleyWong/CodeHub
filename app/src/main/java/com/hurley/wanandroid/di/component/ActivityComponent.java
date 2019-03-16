package com.hurley.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;


import com.hurley.wanandroid.di.module.ActivityModule;
import com.hurley.wanandroid.di.scope.ActivityScope;
import com.hurley.wanandroid.di.scope.ContextLifeCycle;
import com.hurley.wanandroid.module.main.WebActivity;
import com.hurley.wanandroid.module.system.SystemDetailActivity;
import com.hurley.wanandroid.module.user.login.LoginActivity;
import com.hurley.wanandroid.module.user.register.RegisterActivity;

import dagger.Component;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:54 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
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

    //TODO 添加inject(Activity)

    void inject(WebActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(SystemDetailActivity activity);
}
