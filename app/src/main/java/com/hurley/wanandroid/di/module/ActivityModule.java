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

    //🔥将@Module标注在类上，用来告诉Component，可以从这个类中获取依赖对象
    //🔥@Module标注的类是一个工厂，用来生成各种类

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    //🔥@Provides标记在方法上，表示可以通过这个方法来获取依赖对象的实例
    //🔥@Provides标记的方法，就是用来生成这些类的实例

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
