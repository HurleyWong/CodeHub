package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.common.OpenSourceBean;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.module.wanandroid.core.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 17:56
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class AllTagFragment extends BaseFragment {

    @BindView(R.id.rv_all_tag)
    RecyclerView mRvTag;

    private TagAdapter mTagAdapter;
    private List<UserTag> mList;

    public static AllTagFragment newInstance() {
        Bundle args = new Bundle();
        AllTagFragment fragment = new AllTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_tag;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {
        mList = new ArrayList<>();
        mRvTag.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTagAdapter = new TagAdapter(R.layout.item_tag, mList);
        mRvTag.setAdapter(mTagAdapter);
        mTagAdapter.setNewData(setListData(mList));
    }

    private List<UserTag> setListData(List<UserTag> list) {
        int userId = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID);
        list.add(new UserTag(userId, getString(R.string.tag_1), getString(R.string.tag_1_detail), R.mipmap.ic_knowledge));
        list.add(new UserTag(userId, getString(R.string.tag_2), getString(R.string.tag_2_detail), R.mipmap.ic_net));
        list.add(new UserTag(userId, getString(R.string.tag_3), getString(R.string.tag_3_detail), R.mipmap.ic_database));
        list.add(new UserTag(userId, getString(R.string.tag_4), getString(R.string.tag_4_detail), R.mipmap.ic_view));
        list.add(new UserTag(userId, getString(R.string.tag_5), getString(R.string.tag_5_detail), R.mipmap.ic_media));
        list.add(new UserTag(userId, getString(R.string.tag_6), getString(R.string.tag_6_detail), R.mipmap.ic_media));
        return list;
    }
}
