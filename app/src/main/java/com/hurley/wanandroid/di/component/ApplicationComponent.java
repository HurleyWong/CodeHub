package com.hurley.wanandroid.di.component;

import android.content.Context;


import com.hurley.wanandroid.di.module.ApplicationModule;
import com.hurley.wanandroid.di.scope.AppScope;
import com.hurley.wanandroid.di.scope.ContextLifeCycle;

import dagger.Component;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/4 2:03 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 注射器类，其参数为将被注入的类App
 * </pre>
 */

@AppScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLifeCycle("App")
    Context getApplication();
}

