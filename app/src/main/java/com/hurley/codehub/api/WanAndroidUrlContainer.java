package com.hurley.codehub.api;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/26 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : WanAndroid Api接口地址类
 * </pre>
 */
public class WanAndroidUrlContainer {

    private static final String TAG = "WanAndroidUrlContainer";

    public static final String baseUrl = "https://www.wanandroid.com/";


    //1.登录与注册

    /**
     * 登录
     */
    public static final String LOGIN = "user/login";

    /**
     * 注册
     */
    public static final String REGISTER = "user/register";

    /**
     * 退出
     */
    public static final String LOGOUT = "user/logout/json";


    //2.首页相关

    /**
     * 首页文章列表
     */
    public static final String INDEX_ARTICLE_LIST = "article/list/{page}/json";

    /**
     * 首页Banner
     */
    public static final String INDEX_BANNER = "banner/json";

    /**
     * 常用网站
     */
    public static final String WEBSITE = "friend/json";

    /**
     * 搜索热词
     */
    public static final String HOT_KEY = "/hotkey/json";

    /**
     * 置顶文章
     */
    public static final String TOP = "article/top/json";


    //3.体系

    /**
     * 体系数据
     */
    public static final String TREE = "tree/json";

    /**
     * 知识体系下的文章
     */
    public static final String TREE_ARTICLE_LIST = "article/list/{page}/json?";


    //4.导航

    /**
     * 导航数据
     */
    public static final String NAVI = "navi/json";


    //5.项目

    /**
     * 项目分类
     */
    public static final String PROJECT_TREE = "project/tree/json";

    /**
     * 项目列表数据
     */
    public static final String PROJECT_LIST = "project/list/{page}/json";

    /**
     * 最新项目
     */
    public static final String LATEST_PROJECT = "article/listproject/{page}/json";


    //6.收藏

    /**
     * 收藏文章列表
     */
    public static final String COLLECT_ARTICLE_LIST = "lg/collect/list/{page}/json";

    /**
     * 收藏站内文章
     */
    public static final String COLLECT_INSIDE_ARTICLE = "lg/collect/{id}/json";

    /**
     * 收藏站外文章
     */
    public static final String COLLECT_OUTSIDE_ARTICLE = "lg/collect/add/json";

    /**
     * 取消收藏1
     * 从文章列表触发
     */
    public static final String UNCOLLECT_ARTICLE_1 = "lg/uncollect_originId/{id}/json";

    /**
     * 取消收藏2
     * 从我的收藏页面（该页面包含自己录入的内容）触发
     */
    public static final String UNCOLLECT_ARTICLE_2 = "lg/uncollect/{id}/json";

    /**
     * 收藏网站列表
     */
    public static final String COLLECT_WEBSITE_LIST = "lg/collect/usertools/json";

    /**
     * 收藏网址
     */
    public static final String COLLECT_WEBSITE = "lg/collect/addtool/json";

    /**
     * 编辑收藏网站
     */
    public static final String EDIT_COLLECT_WEBSITE = "lg/collect/updatetool/json";

    /**
     * 删除收藏网站
     */
    public static final String DEL_COLLECT_WEBSITE = "lg/collect/deletetool/json";


    //7.搜索

    /**
     * 搜索
     */
    public static final String SEARCH = "article/query/{page}/json";


    //8.TODO工具（v2版本）

    /**
     * 新增一条Todo
     */
    public static final String ADD_TODO = "lg/todo/add/json";

    /**
     * 更新一条Todo内容
     */
    public static final String UPDATE_TODO = "lg/todo/update/{id}/json";

    /**
     * 删除一条Todo
     */
    public static final String DEL_TODO = "lg/todo/delete/{id}/json";

    /**
     * 仅更新完成状态Todo
     */
    public static final String UPDATE_DONE_TODO = "lg/todo/done/{id}/json";

    /**
     * TODO列表
     */
    public static final String TODO_LIST = "lg/todo/v2/list/{page}/json";

    /**
     * 未完成Todo列表
     */
    public static final String NOT_DO_LIST = "lg/todo/listnotdo/{type}/json/{page}";

    /**
     * 已完成Todo列表
     */
    public static final String DONE_LIST = "lg/todo/listdone/{type}/json/{page}";


    //9.公众号

    /**
     * 获取公众号列表
     */
    public static final String WXARTICLE_LIST = "wxarticle/chapters/json";

    /**
     * 查看某个公众号历史数据
     */
    public static final String WXARTICLE_HISTORY = "wxarticle/list/{id}/{page}/json";

    /**
     * 在某个公众号中搜索历史文章
     */
    public static final String WXARTICLE_SEARCH = "wxarticle/list/{id}/{page}/json?";

}
