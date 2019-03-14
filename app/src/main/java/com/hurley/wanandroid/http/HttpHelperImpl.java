package com.hurley.wanandroid.http;



import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.BannerBean;
import com.hurley.wanandroid.bean.CollectArticleBean;
import com.hurley.wanandroid.bean.HotKeyBean;
import com.hurley.wanandroid.bean.NaviBean;
import com.hurley.wanandroid.bean.PageBean;
import com.hurley.wanandroid.bean.ProjectBean;
import com.hurley.wanandroid.bean.SystemBean;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.bean.WebsiteBean;
import com.hurley.wanandroid.bean.WxAccountBean;

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

    private ApiService mApiService;

    @Inject
    HttpHelperImpl(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 登录
     * @param username      用户名
     * @param password      密码
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> login(String username, String password) {
        return mApiService.login(username, password);
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
        return mApiService.register(username, password, repassword);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> logout() {
        return mApiService.logout();
    }

    /**
     * 首页文章列表
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getIndexArticles(int page) {
        return mApiService.getIndexArticles(page);
    }

    /**
     * 首页Banner
     * @return
     */
    @Override
    public Observable<BaseBean<List<BannerBean>>> getIndexBanners() {
        return mApiService.getIndexBanners();
    }

    /**
     * 常用网站
     * @return
     */
    @Override
    public Observable<BaseBean<List<WebsiteBean>>> getWebsites() {
        return mApiService.getWebsites();
    }

    /**
     * 搜索热词
     * @return
     */
    @Override
    public Observable<BaseBean<List<HotKeyBean>>> getHotKeys() {
        return mApiService.getHotKeys();
    }

    /**
     * 体系数据
     * @return
     */
    @Override
    public Observable<BaseBean<List<SystemBean>>> getSystem() {
        return mApiService.getSystem();
    }

    /**
     * 知识体系下的文章
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getSystemArticles(int page, int cid) {
        return mApiService.getSystemArticles(page, cid);
    }

    /**
     * 导航数据
     * @return
     */
    @Override
    public Observable<BaseBean<List<NaviBean>>> getNavigation() {
        return mApiService.getNavigation();
    }

    /**
     * 项目分类
     * @return
     */
    @Override
    public Observable<BaseBean<List<ProjectBean>>> getProject() {
        return mApiService.getProject();
    }

    /**
     * 项目列表数据（文章）
     * @param page          页码
     * @param cid           分类的id
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getProjectArticles(int page, int cid) {
        return mApiService.getProjectArticles(page, cid);
    }

    /**
     * 最新项目tab
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getLatestProjectArticles(int page) {
        return mApiService.getLatestProjectArticles(page);
    }

    /**
     * 收藏文章列表
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<CollectArticleBean>>> getCollectArticles(int page) {
        return mApiService.getCollectArticles(page);
    }

    /**
     * 收藏站内文章
     * @param id            文章id
     * @return
     */
    @Override
    public Observable<BaseBean<String>> collectInsideArticle(int id) {
        return mApiService.collectInsideArticle(id);
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
        return mApiService.collectOutsideArticle(title, author, link);
    }

    /**
     * 从文章列表取消收藏
     * @param id
     * @param originId
     * @return
     */
    @Override
    public Observable<BaseBean<String>> unCollectArticle1(int id, int originId) {
        return mApiService.unCollectArticle1(id, originId);
    }

    /**
     * 从我的收藏页面取消收藏
     * @param id
     * @param originId
     * @return
     */
    @Override
    public Observable<BaseBean<String>> unCollectArticle2(int id, int originId) {
        return mApiService.unCollectArticle2(id, originId);
    }

    /**
     * 搜索
     * @param page          页码
     * @param k             关键字
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getSearchArticles(int page, String k) {
        return mApiService.getSearchArticles(page, k);
    }

    /**
     * 获取公众号列表
     * @return
     */
    @Override
    public Observable<BaseBean<List<WxAccountBean>>> getWxAccounts() {
        return mApiService.getWxAccounts();
    }

    /**
     * 查看某个公众号历史数据
     * @param id            公众号id
     * @param page          页码
     * @return
     */
    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsHistory(int id, int page) {
        return mApiService.getWxAccountsHistory(id, page);
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
        return mApiService.getWxAccountsBySearch(id, page, k);
    }
}
