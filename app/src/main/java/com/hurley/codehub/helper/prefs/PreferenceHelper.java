package com.hurley.codehub.helper.prefs;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 3:57 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首选项储存本地数据
 * </pre>
 */
public interface PreferenceHelper {

    void setUsername(String username);

    void setPassword(String password);

    String getUsername();

    String getPassword();

    /**
     * 设置登录状态
     * @param isLogin
     */
    void setLoginStatus(boolean isLogin);

    /**
     * 获取登录状态
     * @return
     */
    boolean getLoginStatus();

    /**
     * 设置缓存
     * @param domain        域名
     * @param cookie        缓存
     */
    void setCookie(String domain, String cookie);

    /**
     * 获得缓存
     * @param domain
     * @return
     */
    String getCookie(String domain);

    void setCurrentPage(int position);

    /**
     * 获取当前页码
     * @return
     */
    int getCurrentPage();

    void setProjectCurrentPage(int position);

    /**
     * 获取当前项目的页码
     * @return
     */
    int getProjectCurrentPage();

    /**
     * 是否开启自动缓存
     * @return
     */
    boolean getAutoCache();

    void setAutoCache(boolean b);

    /**
     * 是否开启无图模式
     * @return
     */
    boolean getNoImage();

    void setNoImage(boolean b);

    /**
     * 是否开启夜间模式
     * @return
     */
    boolean getNightMode();

    void setNightMode(boolean b);

}
