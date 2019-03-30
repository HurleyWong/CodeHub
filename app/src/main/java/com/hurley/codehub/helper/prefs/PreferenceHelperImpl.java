package com.hurley.codehub.helper.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 4:12 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首选项存储操作实现类
 * </pre>
 */
public class PreferenceHelperImpl implements PreferenceHelper {

    private final SharedPreferences mPreferences;

    @Inject
    PreferenceHelperImpl() {
        mPreferences = App.getInstance().getSharedPreferences(Constants.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setUsername(String username) {
        mPreferences.edit().putString(Constants.USERNAME, username).apply();
    }

    @Override
    public void setPassword(String password) {
        mPreferences.edit().putString(Constants.PASSWORD, password).apply();
    }

    @Override
    public String getUsername() {
        return mPreferences.getString(Constants.USERNAME, "");
    }

    @Override
    public String getPassword() {
        return mPreferences.getString(Constants.PASSWORD, "");
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferences.edit().putBoolean(Constants.LOGIN_STATUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(Constants.LOGIN_STATUS, false);
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mPreferences.edit().putString(domain, cookie).apply();
    }

    @Override
    public String getCookie(String domain) {
        return mPreferences.getString(Constants.COOKIE, "");
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(Constants.CURRENT_PAGE, position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(Constants.CURRENT_PAGE, 0);
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferences.edit().putInt(Constants.PROJECT_CURRENT_PAGE, position).apply();
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferences.getInt(Constants.PROJECT_CURRENT_PAGE, 0);
    }

    @Override
    public boolean getAutoCache() {
        return mPreferences.getBoolean(Constants.AUTO_CACHE, true);
    }

    @Override
    public void setAutoCache(boolean b) {
        mPreferences.edit().putBoolean(Constants.AUTO_CACHE, b).apply();
    }

    @Override
    public boolean getNoImage() {
        return mPreferences.getBoolean(Constants.NO_IMAGE, false);
    }

    @Override
    public void setNoImage(boolean b) {
        mPreferences.edit().putBoolean(Constants.NO_IMAGE, b).apply();
    }

    @Override
    public boolean getNightMode() {
        return mPreferences.getBoolean(Constants.NIGHT_MODE, false);
    }

    @Override
    public void setNightMode(boolean b) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE, b).apply();
    }
}
