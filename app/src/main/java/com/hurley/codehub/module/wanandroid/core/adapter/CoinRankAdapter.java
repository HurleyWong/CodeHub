package com.hurley.codehub.module.wanandroid.core.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.wanandroid.CoinBean;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-09-11 21:53
 *      github  : https://github.com/HurleyJames
 *      desc    : 积分榜适配器类
 * </pre>
 */
public class CoinRankAdapter extends BaseQuickAdapter<CoinBean.DatasBean, BaseViewHolder> {

    @Inject
    public CoinRankAdapter() {
        super(R.layout.coin_rank_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinBean.DatasBean item) {
        helper.setText(R.id.tv_coin_rank, String.valueOf(item.getRank()));
        helper.setText(R.id.tv_coin_username, item.getUsername());
        helper.setText(R.id.tv_coin_count, String.valueOf(item.getCoinCount()));
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

