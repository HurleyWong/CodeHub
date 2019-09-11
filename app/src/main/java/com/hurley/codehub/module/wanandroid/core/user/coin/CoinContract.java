package com.hurley.codehub.module.wanandroid.core.user.coin;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.wanandroid.CoinBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-09-11 14:24
 *      github  : https://github.com/HurleyJames
 *      desc    : 积分 Contract类
 * </pre>
 */
public interface CoinContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示当前用户的积分
         */
        void showUserCoin();

        /**
         * 显示用户积分排行榜
         *
         * @param datasBeans
         */
        void showCoinRank(List<CoinBean.DatasBean> datasBeans);
    }

    interface Presenter extends BaseContract.BasePresenter<CoinContract.View> {

        /**
         * 获取当前用户的积分
         */
        void getUserCoin();

        /**
         * 获取用户积分排行榜
         */
        void getCoinRank();
    }
}
