package com.hurley.codehub.net.interceptor;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.hurley.codehub.api.WanAndroidUrlContainer;
import com.hurley.codehub.util.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-28 22:45
 *      github  : https://github.com/HurleyJames
 *      desc    : 保存Cookie
 * </pre>
 */
public class SaveCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> mCookieList = response.headers("Set-Cookie");
        // 保存Cookie
        if (!mCookieList.isEmpty() && request.url().toString().endsWith(WanAndroidUrlContainer.LOGIN)) {
            StringBuilder sb = new StringBuilder();
            for (String cookie : mCookieList) {
                // 注意Cookie请求头字段中的每个Cookie之间用逗号或分号分隔
                sb.append(cookie).append(",");
            }
            SharedPreferencesUtils.put(response.request().url().host(), sb.toString());
            Log.e(SaveCookieInterceptor.class.getSimpleName(), "intercept: url : " + request.url());
        }
        return response;
    }
}
