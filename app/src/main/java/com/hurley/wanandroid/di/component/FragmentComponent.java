package com.hurley.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;


import com.hurley.wanandroid.di.module.FragmentModule;
import com.hurley.wanandroid.di.scope.ContextLifeCycle;
import com.hurley.wanandroid.di.scope.FragmentScope;
import com.hurley.wanandroid.module.article.SystemArticleListFragment;
import com.hurley.wanandroid.module.article.ProjectArticleListFragment;
import com.hurley.wanandroid.module.article.WxArticleListFragment;
import com.hurley.wanandroid.module.index.IndexFragment;
import com.hurley.wanandroid.module.project.ProjectFragment;
import com.hurley.wanandroid.module.system.SystemFragment;
import com.hurley.wanandroid.module.user.UserFragment;
import com.hurley.wanandroid.module.user.analysis.AnalysisMonthFragment;
import com.hurley.wanandroid.module.user.analysis.AnalysisWeekFragment;
import com.hurley.wanandroid.module.wechat.WechatFragment;

import dagger.Component;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:54 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    @ContextLifeCycle("Activity")
    Context getActivityContext();

    @ContextLifeCycle("App")
    Context getAppContext();

    Activity getActivity();

    void inject(IndexFragment fragment);

    void inject(SystemFragment fragment);

    void inject(WechatFragment fragment);

    void inject(ProjectFragment fragment);

    void inject(UserFragment fragment);

    void inject(SystemArticleListFragment fragment);

    void inject(WxArticleListFragment fragment);

    void inject(ProjectArticleListFragment fragment);

}
