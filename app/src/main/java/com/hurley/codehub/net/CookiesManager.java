package com.hurley.codehub.net;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/8 12:53 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Cookies管理类
 * </pre>
 */
public class CookiesManager implements CookieJar {

    private static final PersistentCookieStore cookieStore = new PersistentCookieStore();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    /**
     * 清除所有cookie
     */
    public static void clearAllCookies() {
        cookieStore.removeAll();
    }

    /**
     * 清除指定cookie
     * @param url
     * @param cookie
     * @return
     */
    public static boolean clearCookies(HttpUrl url, Cookie cookie) {
        return cookieStore.remove(url, cookie);
    }

    /**
     * 获取cookies
     * @return
     */
    public static List<Cookie> getCookies() {
        return cookieStore.getCookies();
    }
}
