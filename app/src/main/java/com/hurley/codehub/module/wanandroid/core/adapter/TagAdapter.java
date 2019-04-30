package com.hurley.codehub.module.wanandroid.core.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.deadline.statebutton.StateButton;
import com.hurley.codehub.R;
import com.hurley.codehub.app.App;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.util.ButtonUtils;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 18:17
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class TagAdapter extends BaseQuickAdapter<UserTag, BaseViewHolder> {

    public TagAdapter(int layoutResId, @Nullable List<UserTag> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserTag item) {
        helper.setText(R.id.tv_tag, item.getTitle())
                .setText(R.id.tv_tag_detail, item.getSubTitle())
                .setText(R.id.btn_tag_status, item.isFollowed() ? App.getAppContext().getString(R.string.followed) : App.getAppContext().getString(R.string.follow))
                .setImageResource(R.id.iv_tag, item.getImage())
                .addOnClickListener(R.id.btn_tag_status);
        StateButton button = helper.getView(R.id.btn_tag_status);
        if (item.isFollowed()) {
            //如果已关注
            ButtonUtils.setButtonStyle2(button);
        } else {
            //如果未关注
            ButtonUtils.setButtonStyle1(button);
        }
    }
}
