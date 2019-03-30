package com.hurley.codehub.module.wanandroid.core.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.wanandroid.ArticleBean;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/16 2:36 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 文章列表适配器
 * </pre>
 */
public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    /**
     * 文章所属体系是否可见，默认可见
     */
    private boolean isChapterNameVisible = true;

    /**
     * 是否已收藏，默认未收藏
     */
    private boolean isCollect = false;

    @Inject
    public ArticleAdapter() {
        super(R.layout.item_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_article_author, item.getAuthor());
        helper.setText(R.id.tv_article_date, item.getNiceDate());
        //TODO Html.fromHtml
        helper.setText(R.id.tv_article_title, item.getTitle());
        helper.setText(R.id.tv_article_chapter, item.getChapterName());
        if (isCollect) {
            //设置是否收藏
            item.setCollect(isCollect);
        }
        //更换收藏图标显示
        helper.setImageResource(R.id.iv_article_collect, item.isCollect() ?
                R.mipmap.ic_collect_fill : R.mipmap.ic_collect);
        //添加文章所属体系的点击事件
        helper.addOnClickListener(R.id.tv_article_chapter);
        //添加收藏的点击事件
        helper.addOnClickListener(R.id.iv_article_collect);
        //设置文章所属体系是否可见
        helper.setVisible(R.id.tv_article_chapter, isChapterNameVisible);
    }

    public void setChapterNameVisible(boolean isChapterNameVisible) {
        this.isChapterNameVisible = isChapterNameVisible;
    }

    public void isCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }
}
