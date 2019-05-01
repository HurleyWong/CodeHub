package com.hurley.codehub.module.readhub.core.topic;

import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.base.BaseContract;
import com.hurley.codehub.bean.readhub.TopicBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-01 13:29
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门话题 Contract类
 * </pre>
 */
public interface TopicContract {

    interface View extends BaseContract.BaseView {

        void setTopicContent(List<TopicBean> topicBeans, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<TopicContract.View> {

        /**
         * 加载热门话题
         */
        void loadTopicContent();

        /**
         * 加载更多热门话题
         * @param lastCursor
         */
        void loadMore(String lastCursor);

        /**
         * 刷新
         */
        void refresh();
    }
}
