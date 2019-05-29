package com.hurley.codehub.module.readhub.core.block;

import android.os.Bundle;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:03
 *      github  : https://github.com/HurleyJames
 *      desc    : 区块链页面
 * </pre>
 */
public class BlockFragment extends BaseFragment {

    public static BlockFragment newInstance() {
        Bundle args = new Bundle();
        BlockFragment fragment = new BlockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.block_fragment;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }
}
