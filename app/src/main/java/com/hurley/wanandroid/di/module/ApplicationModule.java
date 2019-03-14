package com.hurley.wanandroid.di.module;

import android.content.Context;


import com.hurley.wanandroid.app.App;
import com.hurley.wanandroid.di.scope.AppScope;
import com.hurley.wanandroid.di.scope.ContextLifeCycle;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/5 10:33 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

@Module
public class ApplicationModule {

    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    @Provides
    @AppScope
    @ContextLifeCycle("App")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}

