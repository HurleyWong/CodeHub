package com.hurley.codehub.module.wanandroid.core.user.tag.followed;

import com.hurley.codehub.base.BaseContract;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-30 15:14
 *      github  : https://github.com/HurleyJames
 *      desc    : 已关注标签 Contract类
 * </pre>
 */
public interface FollowedTagContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示该用户已关注的标签
         */
        void setFollowedTag();
    }

    interface Presenter extends BaseContract.BasePresenter<FollowedTagContract.View> {

        /**
         * 加载该用户已关注的标签
         * @param userId
         */
        void loadFollowedTag(int userId);
    }
}
