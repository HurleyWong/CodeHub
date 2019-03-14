package com.hurley.wanandroid.app;

import java.io.File;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 4:26 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 静态常量类
 * </pre>
 */
public class Constants {

    public static final String MY_SHARED_PREFERENCE = "my_shared_preference";

    /**
     * 每页数量
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 事件Action
     */
    public static final String EVENT_ACTION_REFRESH_DATA = "refresh_list_item";


    /**
     * 网络相关常量
     */
    public static final String COOKIE = "Cookie";

    /**
     * 路径
     */
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * Fragment有关常量
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_SYSTEM = 1;

    public static final int TYPE_WX_ARTICLE = 2;

    public static final int TYPE_PROJECT = 4;

    public static final int TYPE_COLLECT = 5;

    public static final int TYPE_SETTING = 6;


    /**
     * 主要功能常量
     */
    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECT = "is_collect";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String CURRENT_PAGE = "current_page";

    public static final String PROJECT_CURRENT_PAGE = "project_current_page";


    /**
     * 首选项存储相关常量
     */
    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String AUTO_CACHE = "auto_cache";

    public static final String NO_IMAGE = "no_image";

    public static final String NIGHT_MODE = "night_mode";

    /**
     * 避免多次点击区域
     */
    public static final long CLICK_TIME_AREA = 1000;

    public static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * 动画时间
     */
    public static final int ANIM_TIME = 1000;

}
