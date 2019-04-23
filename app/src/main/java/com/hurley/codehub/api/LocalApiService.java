package com.hurley.codehub.api;

import com.hurley.codehub.bean.local.Article;
import com.hurley.codehub.bean.wanandroid.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
     * 保存点击过的文章到本地数据库
     *
     * @param article
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(LocalUrlContainer.ARTICLE)
    Call<BaseBean<Article>> saveArticle(@Body RequestBody article);
}
