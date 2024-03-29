package com.hurley.codehub.net;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.hurley.codehub.api.LocalUrlContainer;
import com.hurley.codehub.api.ReadhubUrlContainer;
import com.hurley.codehub.api.WanAndroidUrlContainer;
import com.hurley.codehub.app.App;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/8 12:50 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Retrofit封装管理类
 * </pre>
 */

public class RetrofitManager {
    private static long CONNECT_TIMEOUT = 60L;
    private static long READ_TIMEOUT = 10L;
    private static long WRITE_TIMEOUT = 10L;

    /**
     * 设缓存有效期为1天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     */
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
     */
    public static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=10";
    /**
     * 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
     */
    private static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    private static volatile OkHttpClient mOkHttpClient;

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor mRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            // 有网的时候读接口上的@Headers里的配置，可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    /**
     * 日志拦截器
     */
    private static final Interceptor mLoggingInterceptor = chain -> {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response;
    };

    /**
     * 添加Header拦截器
     */
    private static final Interceptor mHeaderInterceptor = chain -> {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .build();
        return chain.proceed(request);
    };

    /**
     * 对OkHttpClient进行配置
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getAppContext()));
                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            // 链接超时
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            // 读取超时
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            // 添加Cookie拦截器
                            // .addInterceptor(new SaveCookieInterceptor())
                            // .addInterceptor(new LoadCookieInterceptor())
                            .cookieJar(cookieJar)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }


    /**
     * 创建WanAndroid的Retrofit
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> clazz) {
        //指定baseUrl
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WanAndroidUrlContainer.baseUrl)
                .client(getOkHttpClient())
                //存储转化数据对象，设置返回的数据支持转换为Gson对象
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 创建ReadHub的Retrofit
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createReadHub(Class<T> clazz) {
        // 指定baseUrl
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ReadhubUrlContainer.baseUrl)
                .client(getOkHttpClient())
                // 存储转化数据对象，设置返回的数据支持转换为Gson对象
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);

    }

    /**
     * 创建本地的Retrofit
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createLocal(Class<T> clazz) {
        //指定baseUrl
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LocalUrlContainer.baseUrl)
                .client(getOkHttpClient())
                //存储转化数据对象，设置返回的数据支持转换为Gson对象
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
