package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.deadline.statebutton.StateButton;
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
 *      desc    : 所有标签页面
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
        mTagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mTagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                StateButton button = view.findViewById(R.id.btn_tag_status);
                if (button.getText().toString().equals(getString(R.string.followed))) {
                    //已关注，点击后变为未关注
                    button.setText(getString(R.string.follow));
                    button.setNormalTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    button.setNormalStrokeWidth(ConvertUtils.dp2px(2));
                    button.setNormalStrokeColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    button.setNormalBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    button.setPressedTextColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));
                    button.setPressedStrokeWidth(ConvertUtils.dp2px(2));
                    button.setPressedStrokeColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));
                    button.setPressedBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonDisable));
                } else {
                    //未关注，点击后变为已关注
                    button.setText(getString(R.string.followed));
                    button.setNormalTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    button.setNormalStrokeWidth(ConvertUtils.dp2px(2));
                    button.setNormalStrokeColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    button.setNormalBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    button.setPressedTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    button.setPressedStrokeWidth(ConvertUtils.dp2px(2));
                    button.setPressedStrokeColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));
                    button.setPressedBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));
                }
            }
        });
    }

    private List<UserTag> setListData(List<UserTag> list) {
        int userId = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID);
        list.add(new UserTag(userId, getString(R.string.tag_1), getString(R.string.tag_1_detail), R.mipmap.ic_knowledge));
        list.add(new UserTag(userId, getString(R.string.tag_2), getString(R.string.tag_2_detail), R.mipmap.ic_net));
        list.add(new UserTag(userId, getString(R.string.tag_3), getString(R.string.tag_3_detail), R.mipmap.ic_database));
        list.add(new UserTag(userId, getString(R.string.tag_4), getString(R.string.tag_4_detail), R.mipmap.ic_view));
        list.add(new UserTag(userId, getString(R.string.tag_5), getString(R.string.tag_5_detail), R.mipmap.ic_media));
        list.add(new UserTag(userId, getString(R.string.tag_6), getString(R.string.tag_6_detail), R.mipmap.ic_project_tag));
        list.add(new UserTag(userId, getString(R.string.tag_7), getString(R.string.tag_7_detail), R.mipmap.ic_tech));
        list.add(new UserTag(userId, getString(R.string.tag_8), getString(R.string.tag_8_detail), R.mipmap.ic_resource));
        list.add(new UserTag(userId, getString(R.string.tag_9), getString(R.string.tag_9_detail), R.mipmap.ic_java));
        return list;
    }
}
