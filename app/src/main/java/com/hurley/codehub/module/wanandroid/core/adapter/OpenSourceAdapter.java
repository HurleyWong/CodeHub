package com.hurley.codehub.module.wanandroid.core.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.common.OpenSourceBean;


import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/21 下午8:11
 *      github : https://github.com/HurleyJames
 *      desc   : 开源协议适配器类
 * </pre>
 */
public class OpenSourceAdapter extends BaseQuickAdapter<OpenSourceBean, BaseViewHolder> {

    public OpenSourceAdapter(int layoutResId, @Nullable List<OpenSourceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenSourceBean item) {
        //可链式调用赋值
        helper.setText(R.id.tv_open_source_name, item.getName())
                .setText(R.id.tv_open_source_author, item.getAuthor())
                .setText(R.id.tv_open_source_detail, item.getDetail());

        //获取当前item的position
        int position = helper.getLayoutPosition();
    }
}
