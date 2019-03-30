package com.hurley.codehub.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19
 *      github : https://github.com/HurleyJames
 *      desc   : FragmentPagerAdapter基类
 * </pre>
 */
public abstract class BaseFragmentPageAdapter<T extends Fragment> extends FragmentPagerAdapter {

    /**
     * Fragment集合
     */
    private List<T> mFragments = new ArrayList<>();

    /**
     * 当前正在显示的Fragment
     */
    private T mCurrentFragment;

    public BaseFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        init(fm, mFragments);
    }

    /**
     * 在Activity中使用ViewPager适配器
     * @param activity
     */
    public BaseFragmentPageAdapter(FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    /**
     * 在Fragment中使用ViewPager适配器
     * @param fragment
     */
    public BaseFragmentPageAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    /**
     * 初始化Fragment
     * @param fm
     * @param list
     */
    protected abstract void init(FragmentManager fm, List<T> list);

    @Override
    public T getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * 设置主要item
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            //记录当前的Fragment对象
            mCurrentFragment = (T) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 获取Fragment集合
     * @return
     */
    public List<T> getAllFragment() {
        return mFragments;
    }

    /**
     * 获取当前Fragment
     * @return
     */
    public T getCurrentFragment() {
        return mCurrentFragment;
    }
}















