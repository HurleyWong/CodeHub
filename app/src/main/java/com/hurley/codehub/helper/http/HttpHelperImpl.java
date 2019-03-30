package com.hurley.codehub.helper.http;



import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.base.BaseBean;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;
import com.hurley.codehub.bean.wanandroid.CollectArticleBean;
import com.hurley.codehub.bean.wanandroid.HotKeyBean;
import com.hurley.codehub.bean.wanandroid.NaviBean;
import com.hurley.codehub.bean.wanandroid.PageBean;
import com.hurley.codehub.bean.wanandroid.ProjectBean;
import com.hurley.codehub.bean.wanandroid.SystemBean;
import com.hurley.codehub.bean.wanandroid.UserBean;
import com.hurley.codehub.bean.wanandroid.WebsiteBean;
import com.hurley.codehub.bean.wanandroid.WxAccountBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 2:26 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 网络请求的实现类
 * </pre>
 */
public class HttpHelperImpl implements HttpHelper{

    private WanAndroidApiService mWanAndroidApiService;

    @Inject
    HttpHelperImpl(WanAndroidApiService wanAndroidApiService) {
        mWanAndroidApiService = wanAndroidApiService;
    }

    /**
     * 登录
     * @param username      用户名
     * @param password      密码
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> login(String username, String password) {
        return mWanAndroidApiService.login(username, password);
    }

    /**
     * 注册
     * @param username      用户名
     * @param password      密码
     * @param repassword    重复密码
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> register(String username, String password, String repassword) {
        return mWanAndroidApiService.register(username, password, repassword);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> logout() {
        return mWanAndroidApiService.logout();
    }

    /**
     * 首页文章列表
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getIndexArticles(int page) {
        return mWanAndroidApiService.getIndexArticles(page);
    }

    /**
     * 首页Banner
     * @return
     */
    @Override
    public Observable<BaseBean<List<BannerBean>>> getIndexBanners() {
        return mWanAndroidApiService.getIndexBanners();
    }

    /**
     * 常用网站
     * @return
     */
    @Override
    public Observable<BaseBean<List<WebsiteBean>>> getWebsites() {
        return mWanAndroidApiService.getWebsites();
    }

    /**
     * 搜索热词
     * @return
     */
    @Override
    public Observable<BaseBean<List<HotKeyBean>>> getHotKeys() {
        return mWanAndroidApiService.getHotKeys();
    }

    /**
     * 体系数据
     * @return
     */
    @Override
    public Observable<BaseBean<List<SystemBean>>> getSystem() {
        return mWanAndroidApiService.getSystem();
    }

    /**
     * 知识体系下的文章
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getSystemArticles(int page, int cid) {
        return mWanAndroidApiService.getSystemArticles(page, cid);
    }

    /**
     * 导航数据
     * @return
     */
    @Override
    public Observable<BaseBean<List<NaviBean>>> getNavigation() {
        return mWanAndroidApiService.getNavigation();
    }

    /**
     * 项目分类
     * @return
     */
    @Override
    public Observable<BaseBean<List<ProjectBean>>> getProject() {
        return mWanAndroidApiService.getProject();
    }

    /**
     * 项目列表数据（文章）
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getProjectArticles(int page, int cid) {
        return mWanAndroidApiService.getProjectArticles(page, cid);
    }

    /**
     * 最新项目tab
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getLatestProjectArticles(int page) {
        return mWanAndroidApiService.getLatestProjectArticles(page);
    }

    /**
     * 收藏文章列表
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getCollectArticles(int page) {
        return mWanAndroidApiService.getCollectArticles(page);
    }

    /**
     * 收藏站内文章
     * @param id            文章id
     * @return
     */
    @Override
    public Observable<BaseBean> collectInsideArticle(int id) {
        return mWanAndroidApiService.collectInsideArticle(id);
    }

    /**
     * 收藏站外文章
     * @param title         标题
     * @param author        作者
     * @param link          链接
     * @return
     */
    @Override
    public Observable<BaseBean<CollectArticleBean>> collectOutsideArticle(String title, String author, String link) {
        return mWanAndroidApiService.collectOutsideArticle(title, author, link);
    }

    /**
     * 从文章列表取消收藏
     * @param id
     * @param originId
     * @return
     */
    @Override
    public Observable<BaseBean<String>> unCollectArticle1(int id, int originId) {
        return mWanAndroidApiService.unCollectArticle1(id, originId);
    }

    /**
     * 从我的收藏页面取消收藏
     * @param id
     * @param originId
     * @return
     */
    @Override
    public Observable<BaseBean> unCollectArticle2(int id, int originId) {
        return mWanAndroidApiService.unCollectArticle2(id, originId);
    }

    /**
     * 搜索
     * @param page          页码
     * @param k             关键字
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getSearchArticles(int page, String k) {
        return mWanAndroidApiService.getSearchArticles(page, k);
    }

    /**
     * 获取公众号列表
     * @return
     */
    @Override
    public Observable<BaseBean<List<WxAccountBean>>> getWxAccounts() {
        return mWanAndroidApiService.getWxAccounts();
    }

    /**
     * 查看某个公众号历史数据
     * @param id            公众号id
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<ArticleBean>> getWxAccountsHistory(int id, int page) {
        return mWanAndroidApiService.getWxAccountsHistory(id, page);
    }

    /**
     * 在某个公众号中搜索历史文章
     * @param id            公众号id
     * @param page          页码
     * @param k             关键字
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsBySearch(int id, int page, String k) {
        return mWanAndroidApiService.getWxAccountsBySearch(id, page, k);
    }
}
