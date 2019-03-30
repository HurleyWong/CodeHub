package com.hurley.codehub.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hurley.codehub.module.article.WxArticleListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/20 6:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 微信公众号适配器类
 * </pre>
 */
public class WechatAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private List<Integer> mIds;
    private List<WxArticleListFragment> wxArticleListFragments = new ArrayList<>();

    public WechatAdapter(FragmentManager fm, List<String> titles, List<Integer> ids) {
        super(fm);
        this.mTitles = titles;
        this.mIds = ids;

        for (Integer id : ids) {
            wxArticleListFragments.add(WxArticleListFragment.newInstance(id));
        }

    }

    @Override
    public Fragment getItem(int i) {
        return wxArticleListFragments.get(i);
    }

    @Override
    public int getCount() {
        return wxArticleListFragments.size();
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
