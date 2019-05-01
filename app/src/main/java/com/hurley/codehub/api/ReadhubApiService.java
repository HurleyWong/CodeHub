package com.hurley.codehub.api;

import com.hurley.codehub.bean.readhub.BaseBean;
import com.hurley.codehub.bean.readhub.TopicBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<BaseBean<TopicBean>> getTopic(@Query("lastCursor") String lastCursor, @Query("pageSize") int pageSize);

    /**
     * 获得热门话题细节
     *
     * @param topicId
     * @return
     */
    @GET(ReadhubUrlContainer.TOPIC_DETAIL)
    Observable<TopicBean> getTopicDetail(@Path("topic_id") String topicId);

}
