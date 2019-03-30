package com.hurley.codehub.di.module;

import android.content.Context;


import com.hurley.codehub.app.App;
import com.hurley.codehub.di.scope.AppScope;
import com.hurley.codehub.di.scope.ContextLifeCycle;

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

