package com.hurley.codehub.module.wanandroid.core.user.tag.all;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.deadline.statebutton.StateButton;
import com.hurley.codehub.R;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.module.wanandroid.core.adapter.TagAdapter;
import com.hurley.codehub.util.ButtonUtils;

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
public class AllTagFragment extends BaseFragment<AllTagPresenter> implements AllTagContract.View {

    @BindView(R.id.rv_all_tag)
    RecyclerView mRvTag;

    private StateButton button;

    private TagAdapter mTagAdapter;
    private List<UserTag> mList;

    private int userId;

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
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mList = new ArrayList<>();
        mRvTag.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTagAdapter = new TagAdapter(R.layout.item_tag, mList);
        mRvTag.setAdapter(mTagAdapter);
        mTagAdapter.setNewData(setListData(mList));

        userId = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID);
        mPresenter.loadFollowedTag(userId);

        mTagAdapter.setOnItemClickListener((adapter, view1, position) -> {

        });

        mTagAdapter.setOnItemChildClickListener((adapter, view2, position) -> {
            button = view2.findViewById(R.id.btn_tag_status);
            if (button.getText().toString().equals(getString(R.string.followed))) {
                //已关注，点击后变为未关注
                ButtonUtils.setButtonStyle1(button);
                delUserTag(userId, mList.get(position).getTitle());
            } else {
                //未关注，点击后变为已关注
                ButtonUtils.setButtonStyle2(button);
                saveUserTag(userId, mList.get(position).getTitle());
            }
        });
    }

    /**
     * 初始化全部标签列表
     *
     * @param list
     * @return
     */
    private List<UserTag> setListData(List<UserTag> list) {
        userId = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID);
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

    /**
     * 保存标签
     *
     * @param userId
     * @param title
     */
    private void saveUserTag(int userId, String title) {
        UserTag userTag = new UserTag();
        userTag.setUserid(userId);
        userTag.setTitle(title);
        mPresenter.saveTag(userTag);
    }

    private void delUserTag(int userId, String title) {
        UserTag userTag = new UserTag();
        userTag.setUserid(userId);
        userTag.setTitle(title);
        mPresenter.deleteTag(userTag);
    }

    @Override
    public void setFollowedTag(List<UserTag> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < mList.size(); j++) {
                if (list.get(i).getTitle().equals(mList.get(j).getTitle())) {
                    mList.get(j).setFollowed(true);
                }
            }
        }
        mTagAdapter.notifyDataSetChanged();
    }
}
