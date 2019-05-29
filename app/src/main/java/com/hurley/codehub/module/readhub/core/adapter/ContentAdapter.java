package com.hurley.codehub.module.readhub.core.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.readhub.TopicBean;
import com.hurley.codehub.util.ReplaceUtils;

import java.text.ParseException;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:59
 *      github  : https://github.com/HurleyJames
 *      desc    : 内容适配器
 * </pre>
 */
public class ContentAdapter extends BaseQuickAdapter<TopicBean, BaseViewHolder> {

    @Inject
    public ContentAdapter() {
        super(R.layout.content_recycle_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBean item) {
        try {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_summary, item.getSummary())
                    .setText(R.id.tv_time, TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.date2String(ReplaceUtils.replaceDate(item.getUpdatedAt()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
