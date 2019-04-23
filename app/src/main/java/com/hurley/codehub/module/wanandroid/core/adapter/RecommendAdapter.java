package com.hurley.codehub.module.wanandroid.core.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.wanandroid.ArticleBean;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-23 15:56
 *      github  : https://github.com/HurleyJames
 *      desc    : 推荐文章适配器
 * </pre>
 */
public class RecommendAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    @Inject
    public RecommendAdapter() {
        super(R.layout.item_recommend_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_recommend_title, item.getTitle());
        helper.setText(R.id.tv_recommend_detail, item.getsuperChapterName() + "·" + item.getAuthor() + "·" + item.getNiceDate());
    }

    /**
     * 重写getItemCount()方法，设置只能推荐3篇文章
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
