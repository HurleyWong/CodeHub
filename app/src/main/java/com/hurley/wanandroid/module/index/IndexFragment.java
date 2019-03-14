package com.hurley.wanandroid.module.index;

import android.view.View;


import com.hjq.bar.TitleBar;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.BannerBean;
import com.hurley.wanandroid.bean.PageBean;

import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午7:14
 *      github : https://github.com/HurleyJames
 *      desc   : 首页界面
 * </pre>
 */
public class IndexFragment extends BaseFragment<IndexPresenter> implements IndexContract.View {

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setBanners(List<BannerBean> banners) {

    }

    @Override
    public void setArticles(PageBean pageBean, int loadType) {

    }

    @Override
    public void collectArticleSuccess(int position, ArticleBean articleBean) {

    }
}
