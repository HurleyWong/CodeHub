package com.hurley.wanandroid.di.module;

import android.app.Activity;
import android.content.Context;


import com.hurley.wanandroid.di.scope.ActivityScope;
import com.hurley.wanandroid.di.scope.ContextLifeCycle;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:55 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    @ContextLifeCycle("Activity")
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
