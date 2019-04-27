package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.os.Bundle;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 17:58
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class FollowedTagFragment extends BaseFragment {

    public static FollowedTagFragment newInstance() {
        Bundle args = new Bundle();
        FollowedTagFragment fragment = new FollowedTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_followed_tag;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }
}
