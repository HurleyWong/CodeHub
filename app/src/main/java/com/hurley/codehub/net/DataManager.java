package com.hurley.codehub.net;


import com.hurley.codehub.bean.wanandroid.BaseBean;
import com.hurley.codehub.bean.wanandroid.ArticleBean;
import com.hurley.codehub.bean.wanandroid.BannerBean;
import com.hurley.codehub.bean.wanandroid.CollectArticleBean;
import com.hurley.codehub.bean.HistoryBean;
import com.hurley.codehub.bean.wanandroid.HotKeyBean;
import com.hurley.codehub.bean.wanandroid.NaviBean;
import com.hurley.codehub.bean.wanandroid.PageBean;
import com.hurley.codehub.bean.wanandroid.ProjectBean;
import com.hurley.codehub.bean.wanandroid.SystemBean;
import com.hurley.codehub.bean.wanandroid.UserBean;
import com.hurley.codehub.bean.wanandroid.WebsiteBean;
import com.hurley.codehub.bean.wanandroid.WxAccountBean;
import com.hurley.codehub.helper.db.DbHelper;
import com.hurley.codehub.helper.http.HttpHelper;
import com.hurley.codehub.helper.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/2 10:26 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 包括获取网络数据、储存本地设置、历史记录的总的数据操作管理类
 * </pre>
 */
public class DataManager implements HttpHelper, DbHelper, PreferenceHelper {

    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager (HttpHelper httpHelper, DbHelper dbHelper, PreferenceHelper preferenceHelper) {
        this.mHttpHelper = httpHelper;
        this.mDbHelper = dbHelper;
        this.mPreferenceHelper = preferenceHelper;
    }

    @Override
    public List<HistoryBean> addHistory(String data) {
        return mDbHelper.addHistory(data);
    }

    @Override
    public void clearHistory() {
        mDbHelper.clearHistory();
    }

    @Override
    public List<HistoryBean> loadAllHistory() {
        return mDbHelper.loadAllHistory();
    }

    @Override
    public Observable<BaseBean<UserBean>> login(String username, String password) {
        return mHttpHelper.login(username, password);
    }

    @Override
    public Observable<BaseBean<UserBean>> register(String username, String password, String repassword) {
        return mHttpHelper.register(username, password, repassword);
    }

    @Override
    public Observable<BaseBean<UserBean>> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getIndexArticles(int page) {
        return mHttpHelper.getIndexArticles(page);
    }

    @Override
    public Observable<BaseBean<List<BannerBean>>> getIndexBanners() {
        return mHttpHelper.getIndexBanners();
    }

    @Override
    public Observable<BaseBean<List<WebsiteBean>>> getWebsites() {
        return mHttpHelper.getWebsites();
    }

    @Override
    public Observable<BaseBean<List<HotKeyBean>>> getHotKeys() {
        return mHttpHelper.getHotKeys();
    }

    @Override
    public Observable<BaseBean<List<SystemBean>>> getSystem() {
        return mHttpHelper.getSystem();
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getSystemArticles(int page, int cid) {
        return mHttpHelper.getSystemArticles(page, cid);
    }

    @Override
    public Observable<BaseBean<List<NaviBean>>> getNavigation() {
        return mHttpHelper.getNavigation();
    }

    @Override
    public Observable<BaseBean<List<ProjectBean>>> getProject() {
        return mHttpHelper.getProject();
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getProjectArticles(int page, int cid) {
        return mHttpHelper.getProjectArticles(page, cid);
    }

    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getLatestProjectArticles(int page) {
        return mHttpHelper.getLatestProjectArticles(page);
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getCollectArticles(int page) {
        return mHttpHelper.getCollectArticles(page);
    }

    @Override
    public Observable<BaseBean> collectInsideArticle(int id) {
        return mHttpHelper.collectInsideArticle(id);
    }

    @Override
    public Observable<BaseBean<CollectArticleBean>> collectOutsideArticle(String title, String author, String link) {
        return mHttpHelper.collectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseBean<String>> unCollectArticle1(int id, int originId) {
        return mHttpHelper.unCollectArticle1(id, originId);
    }

    @Override
    public Observable<BaseBean> unCollectArticle2(int id, int originId) {
        return mHttpHelper.unCollectArticle2(id, originId);
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getSearchArticles(int page, String k) {
        return mHttpHelper.getSearchArticles(page, k);
    }

    @Override
    public Observable<BaseBean<List<WxAccountBean>>> getWxAccounts() {
        return mHttpHelper.getWxAccounts();
    }

    @Override
    public Observable<BaseBean<ArticleBean>> getWxAccountsHistory(int id, int page) {
        return mHttpHelper.getWxAccountsHistory(id, page);
    }

    @Override
    public Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsBySearch(int id, int page, String k) {
        return mHttpHelper.getWxAccountsBySearch(id, page, k);
    }


    @Override
    public void setUsername(String username) {
        mPreferenceHelper.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        mPreferenceHelper.setPassword(password);
    }

    @Override
    public String getUsername() {
        return mPreferenceHelper.getUsername();
    }

    @Override
    public String getPassword() {
        return mPreferenceHelper.getPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mPreferenceHelper.setCookie(domain, cookie);
    }

    @Override
    public String getCookie(String domain) {
        return mPreferenceHelper.getCookie(domain);
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferenceHelper.setProjectCurrentPage(position);
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferenceHelper.getProjectCurrentPage();
    }

    @Override
    public boolean getAutoCache() {
        return mPreferenceHelper.getAutoCache();
    }

    @Override
    public void setAutoCache(boolean b) {
        mPreferenceHelper.setAutoCache(b);
    }

    @Override
    public boolean getNoImage() {
        return mPreferenceHelper.getNoImage();
    }

    @Override
    public void setNoImage(boolean b) {
        mPreferenceHelper.setNoImage(b);
    }

    @Override
    public boolean getNightMode() {
        return mPreferenceHelper.getNightMode();
    }

    @Override
    public void setNightMode(boolean b) {
        mPreferenceHelper.setNightMode(b);
    }
}
