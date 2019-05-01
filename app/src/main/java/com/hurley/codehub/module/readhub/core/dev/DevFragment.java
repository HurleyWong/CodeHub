package com.hurley.codehub.module.readhub.core.dev;

import android.os.Bundle;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:02
 *      github  : https://github.com/HurleyJames
 *      desc    : 开发者资讯页面
 * </pre>
 */
public class DevFragment extends BaseFragment {

    public static DevFragment newInstance() {
        Bundle args = new Bundle();
        DevFragment fragment = new DevFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dev;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }
}
