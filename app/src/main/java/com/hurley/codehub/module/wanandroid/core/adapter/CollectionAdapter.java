package com.hurley.codehub.module.wanandroid.core.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.ArticleBean;

import java.util.List;

import javax.annotation.Nullable;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/22 2:02 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 收藏文章列表适配器类
 * </pre>
 */
public class CollectionAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    public CollectionAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> articleBean) {
        super(layoutResId, articleBean);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_article_author, item.getAuthor());
        helper.setText(R.id.tv_article_date, item.getNiceDate());
        helper.setText(R.id.tv_article_title, item.getTitle());
        if (StringUtils.isEmpty(item.getsuperChapterName())) {
            helper.setText(R.id.tv_article_chapter, item.getChapterName());
        } else {
            helper.setText(R.id.tv_article_chapter,  item.getsuperChapterName() + " / "+ item.getChapterName());
        }
        //添加文章所属体系的点击事件
        helper.addOnClickListener(R.id.tv_article_chapter);

    }
}
