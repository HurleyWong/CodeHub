package com.hurley.wanandroid.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.hurley.wanandroid.di.scope.ContextLifeCycle;
import com.hurley.wanandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:59 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    @ContextLifeCycle("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Fragment provideFragment() {
        return mFragment;
    }
}
