package com.hurley.codehub.module.wanandroid.core.index;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;


import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hurley.codehub.R;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;
import com.hurley.codehub.module.wanandroid.event.LoginEvent;
import com.hurley.codehub.module.wanandroid.core.adapter.ArticleAdapter;
import com.hurley.codehub.module.wanandroid.core.web.WebActivity;
import com.hurley.codehub.net.callback.RxBus;
import com.hurley.codehub.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.net.ConnectException;
import java.util.ArrayList;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午7:14
 *      github : https://github.com/HurleyJames
 *      desc   : 首页界面
 * </pre>
 */
public class IndexFragment extends BaseFragment<IndexPresenter>
        implements IndexContract.View, ArticleAdapter.OnItemClickListener, ArticleAdapter.OnItemChildClickListener,
        ArticleAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_index)
    SwipeRefreshLayout mSrlIndex;
    @BindView(R.id.rv_index)
    RecyclerView mRvIndex;

    @Inject
    ArticleAdapter mArticleAdapter;

    private Banner mBanner;
    private View mBannerView;

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(View view) {
        mRvIndex.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvIndex.setAdapter(mArticleAdapter);

        //设置Banner
        mBannerView = LayoutInflater.from(getContext()).inflate(R.layout.banner_index, null);
        mBanner = mBannerView.findViewById(R.id.banner_index);
        mArticleAdapter.addHeaderView(mBannerView);

        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        //TODO BaseQuickAdapter方法过时
        mArticleAdapter.setOnLoadMoreListener(this);
        mSrlIndex.setOnRefreshListener(this);

        //加载数据
        //mPresenter.loadData();
        onRefresh();

        //登录成功后刷新
        RxBus.getInstance().toFlowable(LoginEvent.class)
                .subscribe(loginEvent -> mPresenter.refresh());
    }

    @Override
    public void setBanners(List<BannerBean> banners) {
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (BannerBean banner : banners) {
            images.add(banner.getImagePath());
            titles.add(banner.getTitle());
        }

        mBanner.setImages(images)
                .setBannerTitles(titles)
                //设置Banner样式
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                //设置图片加载器
                .setImageLoader(new GlideImageLoader())
                .start();

        //设置Banner的点击事件
        mBanner.setOnBannerListener(position -> {
            WebActivity.startWeb(banners.get(position).getId(),
                    banners.get(position).getUrl(),
                    banners.get(position).getTitle(),
                    null);
        });
    }

    @Override
    public void setArticles(ArticleBean articleBean, int loadType) {
        setLoadDataResult(mArticleAdapter, mSrlIndex, articleBean.getDatas(), loadType);
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.TOTAL_PAGE, articleBean.getPageCount());
    }

    @Override
    public void collectArticleSuccess(int position, ArticleBean.DatasBean articleBean) {

    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void showLoading() {
        mSrlIndex.setRefreshing(true);
    }

    @Override
    public void showFailed(String errorMsg) {
        mSrlIndex.setRefreshing(false);
    }

    /**
     * 点击item每条文章
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        WebActivity.startWeb(mArticleAdapter.getItem(position).getId(),
                mArticleAdapter.getItem(position).getLink(),
                mArticleAdapter.getItem(position).getTitle(),
                mArticleAdapter.getItem(position).getAuthor(),
                mArticleAdapter.getItem(position).getChapterId(),
                mArticleAdapter.getItem(position).getChapterName(),
                mArticleAdapter.getItem(position).getsuperChapterName(),
                mArticleAdapter.getItem(position).getNiceDate());

        //TODO 将文章实体类ArticleBean中的属性（userId title link chapterName superChapterName）传给自己的后台
    }

    /**
     * 点击item的子元素
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_article_collect:
                //点击收藏
                LogUtils.e("点击收藏");
                mPresenter.collectArticle(position, mArticleAdapter.getItem(position));
                break;
            case R.id.tv_article_chapter:
                //点击所属体系

                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多内容
        mPresenter.loadMore();
    }

}
