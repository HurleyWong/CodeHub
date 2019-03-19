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

    //🔥一个应用要用到很多的Component，一般划分的原则是有一个全局的Component，比如AppComponent。
    //🔥每个界面有一个Component，比如一个Activity定义一个Component，一个Fragment定义一个Component。
    //🔥如果某些界面之间的依赖的类是一样的，可以共用一个Component。
    //🔥为了管理这些Component，可以使用自定义Scope注解。它可以更好地管理Component与Module之间的匹配关系。

    @ContextLifeCycle("App")
    Context getApplication();
}

