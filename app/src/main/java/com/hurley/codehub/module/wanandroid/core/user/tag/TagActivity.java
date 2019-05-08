package com.hurley.codehub.module.wanandroid.core.user.tag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.deadline.statebutton.StateButton;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.module.wanandroid.core.adapter.TagAdapter;
import com.hurley.codehub.module.wanandroid.event.LoginEvent;
import com.hurley.codehub.module.wanandroid.event.TagEvent;
import com.hurley.codehub.net.callback.RxBus;
import com.hurley.codehub.util.ButtonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 10:57
 *      github  : https://github.com/HurleyJames
 *      desc    : 标签界面
 * </pre>
 */
@Route(path = PathContainer.TAG)
public class TagActivity extends BaseActivity<TagPresenter> implements TagContract.View {

    @BindView(R.id.rv_all_tag)
    RecyclerView mRvTag;

    private StateButton button;

    private TagAdapter mTagAdapter;
    private List<UserTag> mList;

    private int userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tag;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mRvTag.setLayoutManager(new LinearLayoutManager(this));
        mTagAdapter = new TagAdapter(R.layout.item_tag, mList);
        mRvTag.setAdapter(mTagAdapter);
        mTagAdapter.setNewData(setListData(mList));

        userId = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.USER_ID);
        mPresenter.loadFollowedTag(userId);

        mTagAdapter.setOnItemClickListener((adapter, view1, position) -> {

        });

        mTagAdapter.setOnItemChildClickListener((adapter, view2, position) -> {
            button = view2.findViewById(R.id.btn_tag_status);
            //已登录
            if (userId != -1) {
                if (button.getText().toString().equals(getString(R.string.followed))) {
                    //已关注，点击后变为未关注
                    ButtonUtils.setButtonStyle1(button);
                    delUserTag(userId, mList.get(position).getTitle());
                } else {
                    //未关注，点击后变为已关注
                    ButtonUtils.setButtonStyle2(button);
                    saveUserTag(userId, mList.get(position).getTitle());
                }
                //关注或取消关注标签后通知其他界面
                RxBus.getInstance().post(new TagEvent());
            } else {
                //未登录
                toast(R.string.login_please);
            }

        });
    }

    /**
     * 显示返回键
     *
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
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
        list.add(new UserTag(userId, getString(R.string.tag_10), getString(R.string.tag_10_detail), R.mipmap.ic_hot));
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

    /**
     * 删除标签
     * @param userId
     * @param title
     */
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
