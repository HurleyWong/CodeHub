package com.hurley.codehub.api;

import android.nfc.Tag;

import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.local.Chapter;
import com.hurley.codehub.bean.local.UserTag;
import com.hurley.codehub.bean.wanandroid.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-23 10:15
 *      github  : https://github.com/HurleyJames
 *      desc    : 本地服务器接口类
 * </pre>
 */
public interface LocalApiService {

    /**
     * 保存点击过的文章到自己的数据库
     *
     * @param article
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(LocalUrlContainer.ARTICLE)
    Call<BaseBean<Article>> saveArticle(@Body RequestBody article);

    /**
     * 获得后台推荐的文章体系id
     *
     * @param userid
     * @return
     */
    @GET(LocalUrlContainer.CHAPTER)
    Observable<Chapter> getChapterId(@Query("userid") int userid);

    /**
     * 获得当前用户关注的Tag
     *
     * @param userid
     * @return
     */
    @GET(LocalUrlContainer.TAG)
    Observable<BaseBean<List<UserTag>>> getFollowedTag(@Query("userid") int userid);

    /**
     * 发送用户关注的标签到后台
     *
     * @param userTag
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(LocalUrlContainer.SAVE_TAG)
    Call<BaseBean<UserTag>> saveTag(@Body RequestBody userTag);

    /**
     * 发送用户取消关注的标签到后台
     *
     * @param userTag
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(LocalUrlContainer.DEL_TAG)
    Call<BaseBean<UserTag>> delTag(@Body RequestBody userTag);
}
