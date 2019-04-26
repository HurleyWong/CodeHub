package com.hurley.codehub.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/19 11:28 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首选项工具类
 * </pre>
 */
public class SharedPreferencesUtils {
    private static final String WELCOME_PAGE = "welcome_page";
    public static final String FIRST_OPEN = "first_open";

    public static Boolean getBoolean(Context context, String strKey,
                                     Boolean strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                WELCOME_PAGE, Context.MODE_PRIVATE);
        return setPreferences.getBoolean(strKey, strDefault);
    }

    public static void putBoolean(Context context, String strKey,
                                  Boolean strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                WELCOME_PAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }
}
