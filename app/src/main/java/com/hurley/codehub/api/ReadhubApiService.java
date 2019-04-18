package com.hurley.codehub.api;

import com.hurley.codehub.bean.readhub.BaseBean;
import com.hurley.codehub.bean.readhub.TopicBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 12:45 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Readhub服务器接口类
 * </pre>
 */
public interface ReadhubApiService {

    /**
     * 获得热门话题
     *
     * @return
     */
    @GET(ReadhubUrlContainer.TOPIC)
    Observable<BaseBean<TopicBean>> getTopic();


    /**
     * 获得热门话题细节
     *
     * @param topicId
     * @return
     */
    @GET(ReadhubUrlContainer.TOPIC_DETAIL)
    Observable<TopicBean> getTopicDetail(@Path("topic_id") String topicId);

}
