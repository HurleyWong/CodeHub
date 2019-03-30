package com.hurley.codehub.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hurley.codehub.module.article.ProjectArticleListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/21 11:06 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目适配器类
 * </pre>
 */
public class ProjectAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private List<Integer> mIds;
    private List<ProjectArticleListFragment> projectArticleListFragments = new ArrayList<>();

    public ProjectAdapter(FragmentManager fm, List<String> titles, List<Integer> ids) {
        super(fm);
        this.mTitles = titles;
        this.mIds = ids;

        for(Integer id : ids) {
            projectArticleListFragments.add(ProjectArticleListFragment.newInstance(id));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return projectArticleListFragments.get(i);
    }

    @Override
    public int getCount() {
        return projectArticleListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
