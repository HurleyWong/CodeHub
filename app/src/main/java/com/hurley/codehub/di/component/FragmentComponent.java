package com.hurley.codehub.di.component;

import android.app.Activity;
import android.content.Context;


import com.hurley.codehub.di.module.FragmentModule;
import com.hurley.codehub.di.scope.ContextLifeCycle;
import com.hurley.codehub.di.scope.FragmentScope;
import com.hurley.codehub.module.readhub.core.topic.TopicFragment;
import com.hurley.codehub.module.wanandroid.core.article.SystemArticleListFragment;
import com.hurley.codehub.module.wanandroid.core.article.ProjectArticleListFragment;
import com.hurley.codehub.module.wanandroid.core.article.WxArticleListFragment;
import com.hurley.codehub.module.wanandroid.core.index.IndexFragment;
import com.hurley.codehub.module.wanandroid.core.project.ProjectFragment;
import com.hurley.codehub.module.wanandroid.core.system.SystemFragment;
import com.hurley.codehub.module.wanandroid.core.user.UserFragment;
import com.hurley.codehub.module.wanandroid.core.user.tag.all.AllTagFragment;
import com.hurley.codehub.module.wanandroid.core.user.tag.followed.FollowedTagFragment;
import com.hurley.codehub.module.wanandroid.core.wechat.WechatFragment;

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

    //WanAndroid Fragment

    void inject(IndexFragment fragment);

    void inject(SystemFragment fragment);

    void inject(WechatFragment fragment);

    void inject(ProjectFragment fragment);

    void inject(UserFragment fragment);

    void inject(SystemArticleListFragment fragment);

    void inject(WxArticleListFragment fragment);

    void inject(ProjectArticleListFragment fragment);

    void inject(AllTagFragment fragment);

    void inject(FollowedTagFragment fragment);

    //ReadHub Fragment

    void inject(TopicFragment fragment);
}
