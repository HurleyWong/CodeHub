package com.hurley.codehub.module.wanandroid.core.user.tag.all;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.local.UserTag;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-30 15:49
 *      github  : https://github.com/HurleyJames
 *      desc    : 全部标签 Contract类
 * </pre>
 */
public interface AllTagContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示该用户已关注的标签
         *
         * @param userTag
         */
        void setFollowedTag(List<UserTag> userTag);
    }

    interface Presenter extends BaseContract.BasePresenter<AllTagContract.View> {

        /**
         * 加载该用户已关注的标签
         *
         * @param userId
         */
        void loadFollowedTag(int userId);

        /**
         * 将用户点击关注的Tag发送到数据库
         *
         * @param userTag
         */
        void saveTag(UserTag userTag);

        /**
         * 将用户取消关注的Tag发送到数据库
         *
         * @param userTag
         */
        void deleteTag(UserTag userTag);
    }
}
