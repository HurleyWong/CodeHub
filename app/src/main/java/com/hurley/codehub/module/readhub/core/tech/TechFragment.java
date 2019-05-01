package com.hurley.codehub.module.readhub.core.tech;

import android.os.Bundle;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:02
 *      github  : https://github.com/HurleyJames
 *      desc    : 科技动态页面
 * </pre>
 */
public class TechFragment extends BaseFragment {

    public static TechFragment newInstance() {
        Bundle args = new Bundle();
        TechFragment fragment = new TechFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tech;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }
}
