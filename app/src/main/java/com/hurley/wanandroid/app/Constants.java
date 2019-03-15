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
    public static final String CONTENT_ID_KEY = "id";

    public static final String CONTENT_CID_KEY = "cid";

    public static final String CONTENT_URL_KEY = "url";

    public static final String CONTENT_TITLE_KEY = "title";

    public static final String CONTENT_AUTHOR_KEY = "author";

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
     * 每页数量
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 避免多次点击区域
     */
    public static final long CLICK_TIME_AREA = 1000;
    public static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * 动画时间
     */
    public static final int ANIM_TIME = 1000;

    /**
     * 退出间隔时间（ms）
     */
    public static final int INTERVAL_TIME = 2000;

}
