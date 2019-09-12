package com.hurley.codehub.module.wanandroid.core.user.coin;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.bean.wanandroid.CoinBean;
import com.hurley.codehub.module.wanandroid.core.adapter.CoinRankAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-09-11 14:24
 *      github  : https://github.com/HurleyJames
 *      desc    : 积分界面
 * </pre>
 */
@Route(path = PathContainer.COIN)
public class CoinActivity extends BaseActivity<CoinPresenter> implements CoinContract.View {

    @BindView(R.id.cv_user_coin)
    CardView mCvUserCoin;
    @BindView(R.id.tv_coin_user_rank_value)
    TextView mTvUserRank;
    @BindView(R.id.tv_coin_value)
    TextView mTvCoinValue;
    @BindView(R.id.rv_coin_rank)
    RecyclerView mRvCoinRank;

    @Inject
    CoinRankAdapter mAdapter;

    /**
     * 是否登录
     */
    private boolean isLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.coin_activity;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        isLogin = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.LOGIN_STATUS);
        if (isLogin) {
            // 已登录
            mCvUserCoin.setVisibility(View.VISIBLE);
        } else {
            // 未登录
            mCvUserCoin.setVisibility(View.GONE);
        }

        mRvCoinRank.setLayoutManager(new LinearLayoutManager(this));
        mRvCoinRank.setAdapter(mAdapter);

        assert mPresenter != null;
        mPresenter.getUserCoin();
        mPresenter.getCoinRank();
    }

    /**
     * 显示返回键
     *
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void showUserCoin(int count, int rank) {
        mTvCoinValue.setText(String.valueOf(count));
        mTvUserRank.setText(String.valueOf(rank));
    }

    @Override
    public void showCoinRank(List<CoinBean.DatasBean> datasBeans) {
        mAdapter.setNewData(datasBeans);
    }
}
