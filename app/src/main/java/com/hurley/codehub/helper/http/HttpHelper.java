package com.hurley.codehub.helper.http;


import com.hurley.codehub.base.BaseBean;
import com.hurley.codehub.bean.ArticleBean;
import com.hurley.codehub.bean.BannerBean;
import com.hurley.codehub.bean.CollectArticleBean;
import com.hurley.codehub.bean.HotKeyBean;
import com.hurley.codehub.bean.NaviBean;
import com.hurley.codehub.bean.PageBean;
import com.hurley.codehub.bean.ProjectBean;
import com.hurley.codehub.bean.SystemBean;
import com.hurley.codehub.bean.UserBean;
import com.hurley.codehub.bean.WebsiteBean;
import com.hurley.codehub.bean.WxAccountBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 1:50 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 实现网络请求的接口
 * </pre>
 */
public interface HttpHelper {

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username      用户名
     * @param password      密码
     * @return              登录数据
     */
    Observable<BaseBean<UserBean>> login(String username, String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     * @param username      用户名
     * @param password      密码
     * @param repassword    重复密码
     * @return              注册数据
     */
    Observable<BaseBean<UserBean>> register(String username, String password, String repassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     * @return
     */
    Observable<BaseBean<UserBean>> logout();

    /**
     * 首页文章列表
     *http://www.wanandroid.com/article/list/{page}/json
     * @param page          页码
     * @return              文章列表数据
     */
    Observable<BaseBean<ArticleBean>> getIndexArticles(int page);

    /**
     * 首页Banner
     * http://www.wanandroid.com/banner/json
     * @return              Banner数据
     */
    Observable<BaseBean<List<BannerBean>>> getIndexBanners();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     * @return              常用网站数据
     */
    Observable<BaseBean<List<WebsiteBean>>> getWebsites();

    /**
     * 搜索热词
     * http://www.wanandroid.com//hotkey/json
     * @return              热门搜索数据
     */
    Observable<BaseBean<List<HotKeyBean>>> getHotKeys();

    /**
     * 体系数据
     * http://www.wanandroid.com/tree/json
     * @return              知识体系数据
     */
    Observable<BaseBean<List<SystemBean>>> getSystem();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/{page}/json?cid={cid}
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    Observable<BaseBean<ArticleBean>> getSystemArticles(int page, int cid);

    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     * @return              导航数据
     */
    Observable<BaseBean<List<NaviBean>>> getNavigation();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     * @return              项目分类数据
     */
    Observable<BaseBean<List<ProjectBean>>> getProject();

    /**
     * 项目列表数据（文章）
     * http://www.wanandroid.com/project/list/{page}/json?cid={cid}
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    Observable<BaseBean<ArticleBean>> getProjectArticles(int page, int cid);

    /**
     * 最新项目tab
     * http://wanandroid.com/article/listproject/0/json
     * @param page          页码
     * @return              最新项目数据
     */
    Observable<BaseBean<PageBean<ArticleBean>>> getLatestProjectArticles(int page);

    /**
     * 收藏文章列表
     * http://www.wanandroid.com/lg/collect/list/{page}/json
     * @param page          页码
     * @return              收藏文章数据
     */
    Observable<BaseBean<ArticleBean>> getCollectArticles(int page);

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/{id}/json
     * @param id            文章id
     * @return              收藏站内文章数据
     */
    Observable<BaseBean> collectInsideArticle(int id);

    /**
     * 收藏站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     * @param title         标题
     * @param author        作者
     * @param link          链接
     * @return              收藏站外文章数据
     */
    Observable<BaseBean<CollectArticleBean>> collectOutsideArticle(String title, String author, String link);

    /**
     * 从文章列表取消收藏
     * http://www.wanandroid.com/lg/uncollect_originId/{originId}/json
     * @param id
     * @param originId
     * @return
     */
    Observable<BaseBean<String>> unCollectArticle1(int id, int originId);

    /**
     * 从我的收藏页面取消收藏
     * http://www.wanandroid.com/lg/uncollect/{id}/json?originId={originId}
     * @param id
     * @param originId
     * @return
     */
    Observable<BaseBean> unCollectArticle2(int id, int originId);

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/{page}/json?k={k}
     * @param page          页码
     * @param k             关键字
     * @return              搜索的文章数据
     */
    Observable<BaseBean<ArticleBean>> getSearchArticles(int page, String k);

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     * @return              公众号列表数据
     */
    Observable<BaseBean<List<WxAccountBean>>> getWxAccounts();

    /**
     * 查看某个公众号历史数据
     * http://wanandroid.com/wxarticle/list/{id}/{page}/json
     * @param id            公众号id
     * @param page          页码
     * @return              公众号当前页的历史数据
     */
    Observable<BaseBean<ArticleBean>> getWxAccountsHistory(int id, int page);

    /**
     * 在某个公众号中搜索历史文章
     * http://wanandroid.com/wxarticle/list/{id}/{page}/json?k={k}
     * @param id            公众号id
     * @param page          页码
     * @param k             关键字
     * @return
     */
    Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsBySearch(int id, int page, String k);

}
