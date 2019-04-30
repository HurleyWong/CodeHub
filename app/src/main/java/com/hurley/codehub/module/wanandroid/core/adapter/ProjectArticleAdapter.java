package com.hurley.codehub.module.wanandroid.core.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.app.App;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.util.ReplaceUtils;
import com.hurley.codehub.widget.GlideImageLoader;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-30 10:03
 *      github  : https://github.com/HurleyJames
 *      desc    : 项目文章列表适配器
 * </pre>
 */
public class ProjectArticleAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    /**
     * 是否已收藏，默认未收藏
     */
    private boolean isCollect = false;

    @Inject
    public ProjectArticleAdapter() {
        super(R.layout.item_project_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.setText(R.id.tv_project_title, ReplaceUtils.replace(item.getTitle()));
        helper.setText(R.id.tv_project_date, item.getNiceDate());
        helper.setText(R.id.tv_project_author, item.getAuthor());
        helper.setText(R.id.tv_project_desc, item.getDesc());
        ImageView imageView = helper.getView(R.id.iv_project);
        Glide.with(App.getAppContext()).load(item.getEnvelopePic()).into(imageView);
        if (isCollect) {
            //设置是否收藏
            item.setCollect(isCollect);
        }
        //更换收藏图标显示
        helper.setImageResource(R.id.iv_project_collect, item.isCollect() ? R.mipmap.ic_collect_fill : R.mipmap.ic_collect);
        //添加收藏按钮的点击事件
        helper.addOnClickListener(R.id.iv_project_collect);
    }

    public void isCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }
}
