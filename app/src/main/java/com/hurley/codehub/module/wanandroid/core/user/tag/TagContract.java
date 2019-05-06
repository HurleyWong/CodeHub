package com.hurley.codehub.module.wanandroid.core.user.tag;

import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.local.UserTag;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-05 09:40
 *      github  : https://github.com/HurleyJames
 *      desc    : 标签 Contract类
 * </pre>
 */
public interface TagContract {

    interface View extends BaseContract.BaseView {

        /**
         * 显示该用户已关注的标签
         *
         * @param userTag
         */
        void setFollowedTag(List<UserTag> userTag);
    }

    interface Presenter extends BaseContract.BasePresenter<TagContract.View> {

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
