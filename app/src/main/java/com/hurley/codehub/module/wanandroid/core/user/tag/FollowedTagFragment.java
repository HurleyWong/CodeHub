package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.module.wanandroid.core.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 17:58
 *      github  : https://github.com/HurleyJames
 *      desc    : 已关注标签页面
 * </pre>
 */
public class FollowedTagFragment extends BaseFragment {

    @BindView(R.id.rv_followed_tag)
    RecyclerView mRvFollowedTag;

    private TagAdapter mTagAdapter;
    private List<UserTag> mList;

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
        mList = new ArrayList<>();
        mRvFollowedTag.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTagAdapter = new TagAdapter(R.layout.item_tag, mList);
    }
}
