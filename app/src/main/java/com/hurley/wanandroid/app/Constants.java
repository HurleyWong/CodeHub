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
    public static final int TYPE_INDEX = 0;

    public static final int TYPE_SYSTEM = 1;

    public static final int TYPE_WECHAT = 2;

    public static final int TYPE_PROJECT = 3;

    public static final int TYPE_USER = 4;


    /**
     * 主要功能常量
     */
    public static final String CONTENT_ID_KEY = "id";

    public static final String CONTENT_CID_KEY = "cid";

    public static final String CONTENT_URL_KEY = "url";

    public static final String CONTENT_TITLE_KEY = "title";

    public static final String CONTENT_AUTHOR_KEY = "author";

    public static final String CONTENT_CHILDREN_KEY = "children";

    public static final String CONTENT_TYPE_KEY = "type";

    public static final String USER_KEY = "user";

    public static final String BANNER_KEY = "banner";

    public static final String ARTICLE_KEY = "article";

    public static final String IS_COLLECT = "is_collect";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String CURRENT_PAGE = "current_page";

    public static final String PROJECT_CURRENT_PAGE = "project_current_page";


    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";

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
     * 每页显示文章数量
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 避免多次点击区域
     */
    public static final long CLICK_TIME_AREA = 1000;
    public static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * 启动页时间
     */
    public static final int SPLASH_TIME = 2000;

    /**
     * 动画时间
     */
    public static final int ANIM_TIME = 1000;

    /**
     * 退出间隔时间（ms）
     */
    public static final int INTERVAL_TIME = 2000;

}
