package com.hurley.codehub.module.wanandroid.core.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.local.UserTag;

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
        helper.setText(R.id.tv_tag, item.getTitle());
        helper.setText(R.id.tv_tag_detail, item.getSubTitle());
        helper.setImageResource(R.id.iv_tag, item.getImage());
    }
}
