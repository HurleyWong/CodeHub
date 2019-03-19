package com.hurley.wanandroid.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.bean.SystemBean;
import com.hurley.wanandroid.module.article.ArticleListFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/16 2:10 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 体系二级目录适配器类
 * </pre>
 */
public class SystemDetailAdapter extends FragmentPagerAdapter {

    @Nullable
    private List<SystemBean.Children> mChildrenList;
    private List<ArticleListFragment> mArticleListFragments;

    @Inject
    public SystemDetailAdapter(FragmentManager fm, List<SystemBean.Children> children) {
        super(fm);
        this.mChildrenList = children;
        mArticleListFragments = new ArrayList<>();
        if (mChildrenList == null) {
            return;
        }
        for (SystemBean.Children childrenBean : mChildrenList) {
            ArticleListFragment articleListFragment = (ArticleListFragment) ARouter.getInstance()
                    .build(PathContainer.ARTICLE_LIST)
                    .withInt(Constants.CONTENT_CID_KEY, childrenBean.getId())
                    .navigation();
            mArticleListFragments.add(articleListFragment);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return mArticleListFragments.get(i);
    }

    @Override
    public int getCount() {
        return mArticleListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChildrenList.get(position).getName();
    }
}
