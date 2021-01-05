package com.hurley.codehub.net.interceptor;

import android.text.TextUtils;

import com.hurley.codehub.util.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-28 23:10
 *      github  : https://github.com/HurleyJames
 *      desc    : 添加Cookie
 * </pre>
 */
public class LoadCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String mCookieStr = (String) SharedPreferencesUtils.get(chain.request().url().host(), "");
        if (!TextUtils.isEmpty(mCookieStr)) {
            // 长度减1为了去除最后的逗号
            builder.addHeader("Cookie", mCookieStr.substring(0, mCookieStr.length() - 1));
        }
        return chain.proceed(builder.build());
    }
}
